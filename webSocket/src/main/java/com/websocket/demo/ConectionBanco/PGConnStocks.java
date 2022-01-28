package com.websocket.demo.ConectionBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PGConnStocks {

    private static Connection conn = null;

    public static Connection getConn() {
        try {
            if (conn == null) {

                String url = "jdbc:postgresql://localhost:5432/stockbanc?user=postgres&password=solinftec&ssl=false";
                conn = DriverManager.getConnection(url);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

}
