package com.janja.myTwitter.REST;

import com.janja.myTwitter.beans.Message;
import com.janja.myTwitter.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class GetMessage {

    @Autowired
    MessageService messageService;

    @Autowired
    Message message;

    @RequestMapping(value = "/message/getMessages", method = RequestMethod.POST)
    public ArrayList<Message> getAllMessages() {
        ArrayList<Message> allMessages = messageService.getAllMessages();
        return allMessages;
    }
}
