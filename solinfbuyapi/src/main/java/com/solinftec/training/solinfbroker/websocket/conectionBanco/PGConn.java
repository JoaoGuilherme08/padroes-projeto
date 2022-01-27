package com.solinftec.training.solinfbroker.websocket.conectionBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PGConn {

    private static Connection conn = null;

    public static Connection getConn() {
        try {

            System.out.println("TENTANDO CONECTAR");

            if (conn == null) {

                String url = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=solinftec&ssl=false";
                conn = DriverManager.getConnection(url);

                System.out.println("FIZ CONEXAO");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

}
