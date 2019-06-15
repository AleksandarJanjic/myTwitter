package com.janja.myTwitter.REST;

import com.janja.myTwitter.beans.User;
import com.janja.myTwitter.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class GetLikers {

    @Autowired
    LikeService likeService;

    @RequestMapping(value = "/likes/all", method = RequestMethod.POST)
    public ArrayList<User> getAllLikers(
            @RequestParam(value = "messageID") int messageid
    ) {
        ArrayList<User> allLikers = new ArrayList<User>();
        allLikers = likeService.getAllLikers(messageid);
        return allLikers;
    }
}
