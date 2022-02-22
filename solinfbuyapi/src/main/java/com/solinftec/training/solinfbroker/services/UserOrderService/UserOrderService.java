package com.solinftec.training.solinfbroker.services.UserOrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.OptimisticLockException;

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

    Users orderUser;

    Users orderRecebidaUser;

    public UserOrderService(IUserService userService, UserOrderRepository userOrderRepository,
            iUserStockBalanceService iUserStockBalanceService, BuscaListaorder buscaListaorder) {
        super();
        this.userService = userService;
        this.userOrderRepository = userOrderRepository;
        this.iUserStockBalanceService = iUserStockBalanceService;
        this.buscaListaorder = buscaListaorder;
    }

    IUserService userService;
    iUserStockBalanceService iUserStockBalanceService;
    BuscaListaorder buscaListaorder;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Override
    public ResponseEntity<?> save(UserOrders userOrders, String userToken) {

        if (userOrders.getType() == 2 && validaBalance(userOrders)) {
            userOrderRepository.save(userOrders);
            atualiza(userOrders);
            atualizaOrders(userOrders);
        } else if (userOrders.getType() == 1) {
            userOrderRepository.save(userOrders);
            atualizaOrders(userOrders);
        }

        atualizaApiStocks(userOrders, userToken);

        return ResponseEntity.ok().body(userOrders);
    }

    private ResponseEntity<?> atualizaApiStocks(UserOrders userOrders, String token) {
        var venda = new venda();
        var compra = new compra();

        RestTemplate template = new RestTemplate();

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("172.17.0.1:8084")
                .path("stock/{id}")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        Map<String, Long> param = new HashMap<>();
        param.put("id", userOrders.getidStock());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", userOrders.getidStock());
        jsonObject.put("askMin",
                venda.getMinPriceVenda(userOrders.getPrice(), userOrders.getidStock(), userOrderRepository));
        jsonObject.put("askMax",
                venda.getMaxPriceVenda(userOrders.getPrice(), userOrders.getidStock(), userOrderRepository));
        jsonObject.put("bidMin",
                compra.getMinPriceCompra(userOrders.getPrice(), userOrders.getidStock(), userOrderRepository));
        jsonObject.put("bidMax",
                compra.getMaxPriceCompra(userOrders.getPrice(), userOrders.getidStock(), userOrderRepository));

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);

        return template.exchange(uri.toString(), HttpMethod.PUT, request, String.class, param);
    }

    private void atualiza(UserOrders userOrders) {
        long saldoAtualUser = iUserStockBalanceService
                .finByUserAndStockOrder(userOrders).getVolume();

        UserStockBalances stocks = iUserStockBalanceService
                .finByUserAndStockOrder(userOrders);

        if (userOrders.getType() == 2) {
            iUserStockBalanceService.updateStockUser(userOrders.getidUser(), userOrders.getidStock(),
                    saldoAtualUser - userOrders.getVolume());
        } else {
            stocks.setVolume(stocks.getVolume() + 1);
            iUserStockBalanceService.save(stocks);
        }
    }

    public boolean validaBalance(UserOrders userOrders) {

        return iUserStockBalanceService.finByUserAndStock(userOrders.getidUser(), userOrders.getidStock())
                .getVolume() >= userOrders.getVolume();
    }

    /*
     * // private ResponseEntity<?> remove(UserOrders orderRecebida) {
     * 
     * // List<UserOrders> ordersList =
     * // userOrderRepository.findByTypeAndStockAndId(orderRecebida.getType() == 1 ?
     * 2
     * // : 1,
     * // orderRecebida.getidStock(), orderRecebida.getidUser());
     * 
     * // for (UserOrders order : ordersList) {
     * 
     * // if (orderRecebida.getType() == 1 ? order.getPrice() <=
     * // orderRecebida.getPrice()
     * // : order.getPrice() >= orderRecebida.getPrice()) {
     * 
     * // while (userOrderRepository.findId(order.getId()).getVolume() > 0
     * // && userOrderRepository.findId(orderRecebida.getId()).getVolume() > 0) {
     * 
     * // order = userOrderRepository.findId(order.getId());
     * // user = userService.listar(order.getidUser());
     * 
     * // orderRecebida = userOrderRepository.findId(orderRecebida.getId());
     * // orderRecebidaUser = userService.listar(orderRecebida.getidUser());
     * 
     * // if (orderRecebida.getType() == 1 ? orderRecebidaUser.getdollarBalance() >=
     * // order.getPrice()
     * // : user.getdollarBalance() >= orderRecebida.getPrice()) {
     * 
     * // orderRecebida.setVolume(orderRecebida.getVolume() - 1);
     * // order.setVolume(order.getVolume() - 1);
     * 
     * // if (orderRecebida.getType() == 1) {
     * // orderRecebidaUser.setdollarBalance(orderRecebidaUser.getdollarBalance() -
     * order.getPrice());
     * // user.setdollarBalance(user.getdollarBalance() + order.getPrice());
     * // atualiza(orderRecebida);
     * // } else {
     * // orderRecebidaUser.setdollarBalance(orderRecebidaUser.getdollarBalance() +
     * // orderRecebida.getPrice());
     * // user.setdollarBalance(user.getdollarBalance() - orderRecebida.getPrice());
     * // atualiza(order);
     * // }
     * 
     * // if (order.getVolume() == 0) {
     * // order.setStatus(2);
     * // }
     * 
     * // if (orderRecebida.getVolume() == 0) {
     * // orderRecebida.setStatus(2);
     * // }
     * 
     * // userOrderRepository.save(order);
     * // userOrderRepository.save(orderRecebida);
     * // userService.save(user);
     * // userService.save(orderRecebidaUser);
     * 
     * // } else {
     * // break;
     * // }
     * 
     * // }
     * // }
     * // }
     * // return ResponseEntity.ok().body(orderRecebida);
     * // }
     */

    private ResponseEntity<?> atualizaOrders(UserOrders orderRecebida) {

        for (UserOrders order : buscaListaorder.shareInverseOrder(orderRecebida)) {
            while (userOrderRepository.findId(order.getId()).getVolume() > 0
                    && userOrderRepository.findId(orderRecebida.getId()).getVolume() > 0) {

                orderUser = userService.listar(order.getidUser());
                orderRecebidaUser = userService.listar(orderRecebida.getidUser());

                if (orderRecebida.getType() == 1 ? orderRecebidaUser.getdollarBalance() >= order.getPrice()
                        : orderUser.getdollarBalance() >= orderRecebida.getPrice()) {

                    orderRecebida.setVolume(orderRecebida.getVolume() - 1);
                    order.setVolume(order.getVolume() - 1);

                    if (orderRecebida.getType() == 1) {
                        orderRecebidaUser.setdollarBalance(orderRecebidaUser.getdollarBalance() - order.getPrice());
                        orderUser.setdollarBalance(orderUser.getdollarBalance() + order.getPrice());
                        atualiza(orderRecebida);
                    } else {
                        orderRecebidaUser.setdollarBalance(orderRecebidaUser.getdollarBalance() +
                                orderRecebida.getPrice());
                        orderUser.setdollarBalance(orderUser.getdollarBalance() - orderRecebida.getPrice());
                        atualiza(order);
                    }

                    if (order.getVolume() == 0) {
                        order.setStatus(2);
                    }

                    if (orderRecebida.getVolume() == 0) {
                        orderRecebida.setStatus(2);
                    }

                    try {
                        userOrderRepository.save(order);
                        userOrderRepository.save(orderRecebida);
                        userService.save(orderUser);
                        userService.save(orderRecebidaUser);
                    } catch (OptimisticLockException ex) {
                        return ResponseEntity.badRequest().body(ex);
                    }

                } else {
                    break;
                }
            }
        }
        return ResponseEntity.ok().body(orderRecebida);
    }

    @Override
    public List<UserOrders> listaOrdens(int id, long type, long idStock) {

        return userOrderRepository.findByTypeAndStockAndId(id, type, idStock);
    }

    @Override
    public List<UserOrders> findbyUserIdAndType(int idUser, int type) {

        return userOrderRepository.findByUserIdAndType(idUser, type);
    }

    @Override
    public List<UserOrders> findByUserId(int idUser) {
        return userOrderRepository.findByUser(idUser);
    }

    @Override
    public List<UserOrders> findByType(int type) {

        return userOrderRepository.findByType(type);
    }
}
