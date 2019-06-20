package com.mmall.demo2.controller;

import com.mmall.demo2.model.User;
import com.mmall.demo2.service.UserService;
import com.mmall.demo2.utils.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@Controller
public class TestController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(){
        return "edit success";
    }
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null){
            subject.logout();
        }
        return "login";
    }
    @ResponseBody
    @RequestMapping("/admin")
    private String admin(){
        return "admin success";
    }

/*    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            session.setAttribute("user", user);
            return "index";
        } catch (AuthenticationException e) {
            return "login";
        }

    }*/

    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpServletResponse response) {
        User user = this.userService.findByUsername(username);
        if (user.getPassword().equals(password)) {
            String token = JwtUtil.sign(username, password);
            System.out.println(token);
            response.setHeader("Authorization", token);
            return "index";
        } else {
            return "login";
        }

    }
}
