package com.websocket.demo.ConectionBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PGConnOrders {

    private static Connection conn = null;

    public static Connection getConn() {
        try {
            if (conn == null) {

                String url = "jdbc:postgresql://172.17.0.1:5432/postgres?user=postgres&password=solinftec&ssl=false";
                conn = DriverManager.getConnection(url);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

}
