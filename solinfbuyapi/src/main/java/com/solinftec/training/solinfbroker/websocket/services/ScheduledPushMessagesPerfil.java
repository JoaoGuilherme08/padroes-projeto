package com.solinftec.training.solinfbroker.websocket.services;

import java.sql.*;

import com.solinftec.training.solinfbroker.websocket.conectionBanco.PGConn;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ScheduledPushMessagesPerfil {
    // private final SimpMessagingTemplate simpMessagingTemplate;

    // public ScheduledPushMessagesPerfil(SimpMessagingTemplate
    // simpMessagingTemplate) throws SQLException {
    // this.simpMessagingTemplate = simpMessagingTemplate;

    // Connection lConn = PGConn.getConn();
    // Listener listener = new Listener(lConn);
    // listener.start();
    // }

    // class Listener extends Thread {

    // private Connection conn;
    // private org.postgresql.PGConnection pgconn;
    // ScheduledPushMessagesPerfil scheduledPushMessages;

    // Listener(Connection conn) throws SQLException {
    // this.conn = conn;
    // this.pgconn = (org.postgresql.PGConnection) conn;
    // Statement stmt = conn.createStatement();
    // stmt.execute("LISTEN listenperfil");
    // stmt.close();
    // }

    // public void run() {
    // while (true) {
    // try {

    // org.postgresql.PGNotification notifications[] = pgconn.getNotifications();
    // if (notifications != null) {

    // for (int i = 0; i < notifications.length; i++) {
    // System.out.println("PUSH PERFIL");
    // System.out.println(notifications[i].getName());
    // simpMessagingTemplate.convertAndSend("/topic/pushperfil",
    // notifications[i].getParameter());
    // }

    // }

    // } catch (SQLException sqle) {
    // sqle.printStackTrace();
    // }
    // }
    // }

    // }
}