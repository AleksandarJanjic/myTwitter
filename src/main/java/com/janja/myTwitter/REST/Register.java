package com.janja.myTwitter.REST;

import com.janja.myTwitter.beans.User;
import com.janja.myTwitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Register {

    @Autowired
    User user;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register/newaccount", method = RequestMethod.POST)
    public String newAccount(
            @RequestParam(value = "username", defaultValue = "") String userName,
            @RequestParam(value = "password", defaultValue = "") String password
    ) {
        user.setName(userName);
        user.setPassword(password);
        System.out.println(userName);
        System.out.println(password);
        String result = userService.createUser(user);
        return result;
    }
}
