package com.janja.myTwitter.REST;

import com.janja.myTwitter.beans.User;
import com.janja.myTwitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserREST {

    @Autowired
    User user;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/getIDbyName", method = RequestMethod.POST)
    public User getIDbyName (
            @RequestParam(value = "username") String username
    ) {
        User Userresult = new User();
        Userresult = userService.getUserID(username);
        int result = Userresult.getId();
        return Userresult;
    }
}
