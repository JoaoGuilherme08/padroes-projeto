package com.websocket.demo.services;

import java.sql.*;

import com.websocket.demo.ConectionBanco.PGConnOrders;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ScheduledPushMessagesOrders {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public ScheduledPushMessagesOrders(SimpMessagingTemplate simpMessagingTemplate) throws SQLException {
        this.simpMessagingTemplate = simpMessagingTemplate;

        Connection lConn = PGConnOrders.getConn();
        Listener listener = new Listener(lConn);
        listener.start();
    }

    class Listener extends Thread {

        private org.postgresql.PGConnection pgconn;
        ScheduledPushMessagesOrders scheduledPushMessages;

        Listener(Connection conn) throws SQLException {
            this.pgconn = (org.postgresql.PGConnection) conn;
            Statement stmt = conn.createStatement();
            stmt.execute("LISTEN listenorders");
            stmt.close();
        }

        @Override
        public void run() {
            while (true) {
                try {

                    org.postgresql.PGNotification[] notifications = pgconn.getNotifications();
                    if (notifications != null) {
                        for (int i = 0; i < notifications.length; i++) {
                            simpMessagingTemplate.convertAndSend("/topic/pushorders",
                                    notifications[i].getParameter());
                        }
                    }
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }

    }
}
