package com.solinftec.training.solinfbroker.services.UserOrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.solinftec.training.solinfbroker.bean.compra;
import com.solinftec.training.solinfbroker.bean.venda;
import com.solinftec.training.solinfbroker.model.UserOrders;
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
            System.out.print(userOrders);
            userOrderRepository.save(userOrders);
            atualiza(userOrders);
            remove(userOrders);
        } else if (userOrders.getType() == 1) {// compra
            System.out.print(userOrders);
            userOrderRepository.save(userOrders);
            remove(userOrders);
        }

        // region Comunicação API Stock
        var venda = new venda();
        var compra = new compra();
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost:8084")
                .path("stock/{id}")
                .build();

        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", userToken);

        Map<String, Long> param = new HashMap<String, Long>();
        param.put("id", userOrders.getId_stock());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", userOrders.getId_stock());
        jsonObject.put("ask_min",
                venda.getMinPriceVenda(userOrders.getPrice(), userOrders.getId_stock(), userOrderRepository));
        jsonObject.put("ask_max",
                venda.getMaxPriceVenda(userOrders.getPrice(), userOrders.getId_stock(), userOrderRepository));
        jsonObject.put("bid_min",
                compra.getMinPriceCompra(userOrders.getPrice(), userOrders.getId_stock(), userOrderRepository));
        jsonObject.put("bid_max",
                compra.getMaxPriceCompra(userOrders.getPrice(), userOrders.getId_stock(), userOrderRepository));

        HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);

        System.out.println(jsonObject.toString());

        HttpEntity<String> response = template.exchange(uri.toString(), HttpMethod.PUT, request, String.class, param);

        // endregion

        return ResponseEntity.ok().body(userOrders);
    }

    private void atualiza(UserOrders userOrders) {
        long SaldoAtualUser = iUserStockBalanceService
                .finByUserAndStock(userOrders.getId_user(), userOrders.getId_stock(), userOrders).getVolume();

        if (userOrders.getType() == 2) {
            iUserStockBalanceService.updateStockUser(userOrders.getId_user(), userOrders.getId_stock(),
                    SaldoAtualUser - userOrders.getVolume());
        } else {
            iUserStockBalanceService.updateStockUser(userOrders.getId_user(), userOrders.getId_stock(),
                    SaldoAtualUser + 1);
        }

    }

    private ResponseEntity<?> remove(UserOrders order_recebidap) {

        UserOrders order_recebida = order_recebidap;

        List<UserOrders> ordersList = userOrderRepository.findByTypeAndStockNotId(order_recebida.getType() == 1 ? 2 : 1,
                order_recebida.getId_stock(), order_recebida.getId_user());
        for (UserOrders order : ordersList) {
            if (order_recebida.getType() == 1 ? order.getPrice() <= order_recebida.getPrice()
                    : order.getPrice() >= order_recebida.getPrice()) {
                while (userOrderRepository.findId(order.getId()).getVolume() > 0
                        && userOrderRepository.findId(order_recebida.getId()).getVolume() > 0) {

                    order = userOrderRepository.findId(order.getId());
                    user = userService.Listar(order.getId_user());
                    order_recebida = userOrderRepository.findId(order_recebida.getId());
                    order_user = userService.Listar(order_recebida.getId_user());

                    if (order_recebida.getType() == 1 ? order_user.getDollar_balance() >= order.getPrice()
                            : user.getDollar_balance() >= order_recebida.getPrice()) {

                        order_recebida.setVolume(order_recebida.getVolume() - 1);
                        order.setVolume(order.getVolume() - 1);

                        if (order_recebida.getType() == 1) {
                            order_user.setDollar_balance(order_user.getDollar_balance() - order.getPrice());
                            user.setDollar_balance(user.getDollar_balance() + order.getPrice());
                            atualiza(order_recebida);
                        } else {
                            order_user.setDollar_balance(order_user.getDollar_balance() + order_recebida.getPrice());
                            user.setDollar_balance(user.getDollar_balance() - order_recebida.getPrice());
                            atualiza(order);
                        }

                        if (order.getVolume() == 0) {
                            order.setStatus(2);
                        }

                        if (order_recebida.getVolume() == 0) {
                            order_recebida.setStatus(2);
                        }

                        userOrderRepository.save(order);
                        userOrderRepository.save(order_recebida);
                        userService.save(user);
                        userService.save(order_user);

                    } else {
                        break;
                    }

                }
            }
        }
        return ResponseEntity.ok().body(order_recebida);
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

        if (iUserStockBalanceService.finByUserAndStock(userOrders.getId_user(), userOrders.getId_stock())
                .getVolume() >= userOrders.getVolume()) {
            return true;
        } else {
            return false;
        }
    }
}
