package com.bot;

import java.util.Scanner;
import com.bot.service.Bot;

/*
    ATENÇÃO
Para ultilizar o bot,

    limpe as tabelas : 
        - User_Stock_Balance
        - User_Order
    #  delete from user_orders;
    #  delete from user_stock_balances;

    Zere os valores  :
        - bid_min,
        - bid_max,
        - ask_min,
        - ask_max.
   #  update stocks set ask_min = 0, ask_max = 0, bid_min = 0, bid_max = 0

    acrescente saldo na tabela:
        - Users
   #  update users set dollar_balance = 1000000;

INSERT USERS 
    insert into users (dollar_balance, enabled, username) values (1000, true, 'botTest1@gmail.com');
    insert into users (dollar_balance, enabled, username) values (1000, true, 'botTest2@gmail.com');
    insert into users (dollar_balance, enabled, username) values (1000, true, 'botTest3@gmail.com');
    insert into users (dollar_balance, enabled, username) values (1000, true, 'botTest4@gmail.com');
    insert into users (dollar_balance, enabled, username) values (1000, true, 'botTest5@gmail.com');
    insert into users (dollar_balance, enabled, username) values (1000, true, 'botTest6@gmail.com');
    insert into users (dollar_balance, enabled, username) values (1000, true, 'botTest7@gmail.com');
    insert into users (dollar_balance, enabled, username) values (1000, true, 'botTest8@gmail.com');
    insert into users (dollar_balance, enabled, username) values (1000, true, 'botTest9@gmail.com');
    insert into users (dollar_balance, enabled, username) values (1000, true, 'botTest10@gmail.com');
    insert into users (dollar_balance, enabled, username) values (1000, true, 'botTest11@gmail.com');

INSERT STOCK_BALANCES
    insert into user_stock_balances (id_stock, id_user, stock_name, stock_symbol, volume) values (1, 1, 'MINERVA', 'BEEF', 100);
    insert into user_stock_balances (id_stock, id_user, stock_name, stock_symbol, volume) values (2, 2, 'EMBRAER', 'EMBR', 100);
    insert into user_stock_balances (id_stock, id_user, stock_name, stock_symbol, volume) values (3, 3, 'DESKTOP', 'DESK', 100);
    insert into user_stock_balances (id_stock, id_user, stock_name, stock_symbol, volume) values (4, 4, 'MOSAICO', 'MOSI', 100);
    insert into user_stock_balances (id_stock, id_user, stock_name, stock_symbol, volume) values (5, 5, 'ETERNIT', 'ETER', 100);
    insert into user_stock_balances (id_stock, id_user, stock_name, stock_symbol, volume) values (6, 6, 'IMC S/A', 'MEAL', 100);
    insert into user_stock_balances (id_stock, id_user, stock_name, stock_symbol, volume) values (7, 7, 'DOTZ SA', 'DOTZ', 100);
    insert into user_stock_balances (id_stock, id_user, stock_name, stock_symbol, volume) values (8, 8, 'BRASKEM', 'BRKM', 100);
    insert into user_stock_balances (id_stock, id_user, stock_name, stock_symbol, volume) values (9, 9, 'UNICASA', 'UCAS', 100);
    insert into user_stock_balances (id_stock, id_user, stock_name, stock_symbol, volume) values (10, 10, 'HASHDEX', 'HASH', 100);
*/

public class App {
    public static void main(String[] args) throws Exception {
        System.out.print("Informe o TOKEN: ");
        String token = new Scanner(System.in).nextLine();
        Bot.executar(token);
    }
}
