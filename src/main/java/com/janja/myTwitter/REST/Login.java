package com.janja.myTwitter.REST;

import com.janja.myTwitter.beans.User;
import com.janja.myTwitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class Login {

    @Autowired
    User user;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login/checkLogin", method = RequestMethod.POST)
    public void checkLogin(
            HttpServletResponse response,
            HttpSession session,
            @RequestParam(value = "username", defaultValue = "") String userName,
            @RequestParam(value = "password", defaultValue = "") String password
    ) {
        user.setName(userName);
        user.setPassword(password);
        boolean passCheck = userService.checkUserCredentials(user);
        if(passCheck == true) {
            try {
                session.setAttribute("username", user.getName());
                response.sendRedirect("/myTwitter");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
