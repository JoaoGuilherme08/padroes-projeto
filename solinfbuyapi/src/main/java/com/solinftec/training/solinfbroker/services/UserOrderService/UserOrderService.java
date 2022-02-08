package com.solinftec.training.solinfbroker.services.UserOrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.solinftec.training.solinfbroker.bean.compra;
import com.solinftec.training.solinfbroker.bean.venda;
import com.solinftec.training.solinfbroker.model.UserOrders;
import com.solinftec.training.solinfbroker.model.UserStockBalances;
import com.solinftec.training.solinfbroker.model.Users;
import com.solinftec.training.solinfbroker.repository.UserOrderRepository;
import com.solinftec.training.solinfbroker.services.UserOrderBalance.iUserStockBalanceService;
import com.solinftec.training.solinfbroker.services.UserService.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import net.minidev.json.JSONObject;

@Service
public class UserOrderService implements IUserOrderService {

    Users user;

    Users order_user;

    public UserOrderService(IUserService userService, UserOrderRepository userOrderRepository,
            iUserStockBalanceService iUserStockBalanceService) {
        super();
        this.userService = userService;
        this.userOrderRepository = userOrderRepository;
        this.iUserStockBalanceService = iUserStockBalanceService;
    }

    IUserService userService;
    iUserStockBalanceService iUserStockBalanceService;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Override
    public ResponseEntity<?> save(UserOrders userOrders, String userToken) {

        if (userOrders.getType() == 2 && validaBalance(userOrders)) { // venda
            userOrderRepository.save(userOrders);
            atualiza(userOrders);
            remove(userOrders);
        } else if (userOrders.getType() == 1) {// compra
            userOrderRepository.save(userOrders);
            remove(userOrders);
        }

        // region Comunicação API Stock
        var venda = new venda();
        var compra = new compra();

        RestTemplate template = new RestTemplate();

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost:8084")
                .path("stock/{id}")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", userToken);

        Map<String, Long> param = new HashMap<String, Long>();
        param.put("id", userOrders.getidStock());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", userOrders.getidStock());
        jsonObject.put("ask_min",
                venda.getMinPriceVenda(userOrders.getPrice(), userOrders.getidStock(), userOrderRepository));
        jsonObject.put("ask_max",
                venda.getMaxPriceVenda(userOrders.getPrice(), userOrders.getidStock(), userOrderRepository));
        jsonObject.put("bid_min",
                compra.getMinPriceCompra(userOrders.getPrice(), userOrders.getidStock(), userOrderRepository));
        jsonObject.put("bid_max",
                compra.getMaxPriceCompra(userOrders.getPrice(), userOrders.getidStock(), userOrderRepository));

        HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);

        template.exchange(uri.toString(), HttpMethod.PUT, request, String.class, param);

        // endregion

        return ResponseEntity.ok().body(userOrders);
    }

    private void atualiza(UserOrders userOrders) {
        long SaldoAtualUser = iUserStockBalanceService
                .finByUserAndStockOrder(userOrders).getVolume();

        UserStockBalances stocks = iUserStockBalanceService
                .finByUserAndStockOrder(userOrders);

        if (userOrders.getType() == 2) {
            iUserStockBalanceService.updateStockUser(userOrders.getidUser(), userOrders.getidStock(),
                    SaldoAtualUser - userOrders.getVolume());
        } else {
            stocks.setVolume(stocks.getVolume() + 1);
            iUserStockBalanceService.save(stocks);
        }
    }

    private ResponseEntity<?> remove(UserOrders orderRecebida) {

        List<UserOrders> ordersList = userOrderRepository.findByTypeAndStockNotId(orderRecebida.getType() == 1 ? 2 : 1,
                orderRecebida.getidStock(), orderRecebida.getidUser());

        for (UserOrders order : ordersList) {

            if (orderRecebida.getType() == 1 ? order.getPrice() <= orderRecebida.getPrice()
                    : order.getPrice() >= orderRecebida.getPrice()) {

                while (userOrderRepository.findId(order.getId()).getVolume() > 0
                        && userOrderRepository.findId(orderRecebida.getId()).getVolume() > 0) {

                    order = userOrderRepository.findId(order.getId());
                    user = userService.listar(order.getidUser());

                    orderRecebida = userOrderRepository.findId(orderRecebida.getId());
                    order_user = userService.listar(orderRecebida.getidUser());

                    if (orderRecebida.getType() == 1 ? order_user.getdollarBalance() >= order.getPrice()
                            : user.getdollarBalance() >= orderRecebida.getPrice()) {

                        orderRecebida.setVolume(orderRecebida.getVolume() - 1);
                        order.setVolume(order.getVolume() - 1);

                        if (orderRecebida.getType() == 1) {
                            order_user.setdollarBalance(order_user.getdollarBalance() - order.getPrice());
                            user.setdollarBalance(user.getdollarBalance() + order.getPrice());
                            atualiza(orderRecebida);
                        } else {
                            order_user.setdollarBalance(order_user.getdollarBalance() + orderRecebida.getPrice());
                            user.setdollarBalance(user.getdollarBalance() - orderRecebida.getPrice());
                            atualiza(order);
                        }

                        if (order.getVolume() == 0) {
                            order.setStatus(2);
                        }

                        if (orderRecebida.getVolume() == 0) {
                            orderRecebida.setStatus(2);
                        }

                        userOrderRepository.save(order);
                        userOrderRepository.save(orderRecebida);
                        userService.save(user);
                        userService.save(order_user);

                    } else {
                        break;
                    }

                }
            }
        }
        return ResponseEntity.ok().body(orderRecebida);
    }

    @Override
    public List<UserOrders> listaOrdens(int id, long type, long idStock) {

        return userOrderRepository.findByTypeAndStockNotId(id, type, idStock);
    }

    @Override
    public List<UserOrders> findbyUserIdAndType(int idUser, int type) {

        return userOrderRepository.findByUserIdAndType(idUser, type);
    }

    @Override
    public List<UserOrders> findByUserId(int idUser) {
        return userOrderRepository.findByUser(idUser);
    }

    public boolean validaBalance(UserOrders userOrders) {

        if (iUserStockBalanceService.finByUserAndStock(userOrders.getidUser(), userOrders.getidStock())
                .getVolume() >= userOrders.getVolume()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserOrders> findByType(int type) {

        return userOrderRepository.findByType(type);
    }
}
