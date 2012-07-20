package com.directi.train.twimini.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {
    public final SimpleJdbcTemplate db;

    @Autowired
    public UserController(SimpleJdbcTemplate db) {this.db = db;}

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String loginForm() {
        return "index";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") String userName,
                              @RequestParam("password") String password,
                              HttpSession session) {
        ModelAndView mv = new ModelAndView("/index");
        try {
            Map<String, Object> userData = db.queryForMap("select username, password from users where username=?",
                                                          userName);
            if (!userData.get("password").equals(password)) {
                mv.addObject("message", "Invalid password.");
                return mv;
            }
        } catch (EmptyResultDataAccessException e) {
            mv.addObject("message", "Invalid username");
            return mv;
        }
        session.setAttribute("userName", userName);
        mv.setViewName("redirect:/tweet");
        return mv;
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam("username") String userName,
                              @RequestParam("password") String password,
                              HttpSession session) {
        ModelAndView mv = new ModelAndView("/index");
        try {
            Map<String, Object> userData = db.queryForMap("select username, password from users where username=?",
                    userName);
            mv.addObject("message", "Username already exists");
            return mv;
        } catch (EmptyResultDataAccessException e) {
            db.update("INSERT INTO users (username, password) VALUES (\"" + userName + "\",\"" + password + "\");");
            session.setAttribute("userName", userName);
            mv.setViewName("redirect:/tweet");
            return mv;
        }
    }

    @RequestMapping(value = "/tweet", method = RequestMethod.GET)
    public ModelAndView tweet() {
        ModelAndView mv = new ModelAndView("/tweet");
        return mv;
    }

    @RequestMapping(value = "/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}