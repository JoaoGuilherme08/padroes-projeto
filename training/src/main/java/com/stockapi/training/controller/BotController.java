package com.stockapi.training.controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.stockapi.training.model.Message;
import com.stockapi.training.model.OutputMessage;

@Controller
public class BotController {

    @MessageMapping("/chatwithbots")
    @SendTo("/topic/pushmessages")
    public OutputMessage send(final Message message) throws Exception {
        System.out.println("Bots controller entrou metodo");
        final String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }

}