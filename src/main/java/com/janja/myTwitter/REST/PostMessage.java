package com.janja.myTwitter.REST;

import com.janja.myTwitter.beans.Message;
import com.janja.myTwitter.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostMessage {

    @Autowired
    Message message;

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/message/postMessage", method = RequestMethod.POST)
    public String postMessage(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "userID") int userID,
            @RequestParam(value = "message") String messageContent
    ) {
        message.setAuthor(userID);
        message.setContent(messageContent);
        messageService.postMessage(message);
        return "success";
    }
}
