package com.stockapi.training.notification;


import java.sql.*;
import java.text.SimpleDateFormat;

import com.stockapi.training.conectionBanco.PGConn;
import com.stockapi.training.service.ScheduledPushMessages;


public class Notification {

    public static void start() throws Exception {
        Connection lConn = PGConn.getConn();
        Listener listener = new Listener(lConn);
        listener.start();

    }
    
    public static void main(String[] args) throws Exception {
        start();
    }
}

class Listener extends Thread {

    private Connection conn;
    private org.postgresql.PGConnection pgconn;
    ScheduledPushMessages  scheduledPushMessages;

    Listener(Connection conn) throws SQLException {
        this.conn = conn;
        this.pgconn = (org.postgresql.PGConnection) conn;
        Statement stmt = conn.createStatement();
        System.out.println("ESTOU NA LINHA 31");
        stmt.execute("LISTEN stocks");
        stmt.close();
    }

    public void run() {
        while (true) {
            try {

                org.postgresql.PGNotification notifications[] = pgconn.getNotifications();
                if (notifications != null) {
                    for (int i=0; i < notifications.length; i++)
                        System.out.println("Got notification: " + notifications[i].getParameter());
                }

            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }

}
    