package com.websocket.demo.services;

import java.sql.*;
import com.websocket.demo.ConectionBanco.PGConnStocks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ScheduledPushMessages {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public ScheduledPushMessages(SimpMessagingTemplate simpMessagingTemplate) throws SQLException {
        this.simpMessagingTemplate = simpMessagingTemplate;

        Connection lConn = PGConnStocks.getConn();
        Listener listener = new Listener(lConn);
        listener.start();
    }

    class Listener extends Thread {

        private Connection conn;
        private org.postgresql.PGConnection pgconn;
        ScheduledPushMessages scheduledPushMessages;

        Listener(Connection conn) throws SQLException {
            this.conn = conn;
            this.pgconn = (org.postgresql.PGConnection) conn;
            Statement stmt = conn.createStatement();
            stmt.execute("LISTEN listenstocks");
            stmt.close();
        }

        public void run() {
            while (true) {
                try {

                    org.postgresql.PGNotification notifications[] = pgconn.getNotifications();
                    if (notifications != null) {
                        for (int i = 0; i < notifications.length; i++) {
                            simpMessagingTemplate.convertAndSend("/topic/pushmessages",
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
