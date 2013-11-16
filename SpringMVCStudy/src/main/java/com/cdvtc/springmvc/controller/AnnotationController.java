package com.cdvtc.springmvc.controller;

import com.cdvtc.springmvc.vo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: chenjian
 * Date: 13-10-21
 * Time: 下午3:55
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/annotation")
@SessionAttributes(value = {"sessionUser"})
public class AnnotationController {
    @RequestMapping(value = "/pathVariable/{name}", method = RequestMethod.GET)
    public void pathVariable(PrintWriter out, @PathVariable String name /*若此时变量名与{}之间的名不同时，可以使用@PathVariable("name")*/) {
        out.print("name is : " + name);
    }

    @RequestMapping(value = "/cookieValue", method = RequestMethod.GET)
    public String cookieValue(@CookieValue(value = "JSESESSION", defaultValue = "") String cookie) {
        System.out.println("cookie : " + cookie);
        return "index";
    }

    @RequestMapping(value = "/formObject", method = {RequestMethod.GET, RequestMethod.POST})
    public void formObject(PrintWriter out, User user) {
        out.println("username: " + user.getUsername() + " password: " + user.getPassword());
    }

    @RequestMapping(value = "/model", method = RequestMethod.GET)
    public String model(Model model1, Map model2, ModelMap model3) {
        model1.addAttribute("a", "a");
        model2.put("b", "c");
        model3.put("c", "c");
        System.out.println(model1 == model2);
        return "success";
    }

    @RequestMapping(value = "/regexURL/\\d+", method = RequestMethod.GET)
    public void regexUrl(PrintWriter out, @RequestParam(value = "requestCode") int requestCode) {
        out.println("__" + requestCode);
    }

    @RequestMapping(value = "/requestParam", method = RequestMethod.GET)
    public void requestParam(PrintWriter out, @RequestParam(value = "value") String param) {
        out.println("param : " + param);
    }

    @RequestMapping(value = "/requestHeader", method = RequestMethod.GET)
    public void requestHeader(PrintWriter out, @RequestHeader("name") String name) {
        out.println("name : " + name);
    }

    @RequestMapping(value = "responseBody1", method = RequestMethod.GET)
    @ResponseBody
    public User responseBody1(@ModelAttribute("user2") User user) {
        return user;
    }

    @RequestMapping(value = "responseBody2", method = RequestMethod.GET)
    @ResponseBody
    public List<User> responseBody2() {
        List<User> list = new ArrayList<User>();
        User u = null;
        u = new User();
        u.setUsername("111");
        u.setPassword("111");
        list.add(u);
        u = new User();
        u.setUsername("222");
        u.setPassword("222");
        list.add(u);
        u = new User();
        u.setUsername("333");
        u.setPassword("333");
        list.add(u);
        return list;
    }

    //@ModelAttribute使用
    @RequestMapping(value = "/modelAttribute1", method = RequestMethod.GET)
    public String modelAttribute1(@ModelAttribute("user") User user) {   //当@ModelAttribute放在参数前时，可以在页面中使用{u.username}获取值
        System.out.println("username: " + user.getUsername() + " password: " + user.getPassword());
        return "success";
    }

    @RequestMapping(value = "/modelAttribute2/{username}", method = RequestMethod.GET)
    public String modelAttribute2(@ModelAttribute("user") User user) {
        System.out.println("username: " + user.getUsername() + " password: " + user.getPassword());
        return "success";
    }

    @RequestMapping(value = "/modelAttribute3", method = RequestMethod.GET)
    public String modelAttribute3(@ModelAttribute("user1") User user1) {
        System.out.println("username: " + user1.getUsername() + " password:" + user1.getPassword());
        return "success";
    }

    @ModelAttribute("user1")
    public User getUser1(@RequestParam(value = "username", defaultValue = "") String username) {
        User u = new User();
        u.setUsername(username);
        u.setPassword("12346");
        return u;
    }

    @RequestMapping(value = "/modelAttribute4", method = RequestMethod.GET)
    public String modelAttribute4(@ModelAttribute User user2) {
        System.out.println("username: " + user2.getUsername() + " password:" + user2.getPassword());
        return "success";
    }

    public
    @ModelAttribute("user2")
    User getUser2(@ModelAttribute User user2) {
        user2.setUsername("wangwu");
        user2.setPassword("123456");
        return user2;
    }

    @ModelAttribute("sessionUser")
    public User getUser3() {
        User u = new User();
        u.setUsername("zhaoliu");
        u.setPassword("123456");
        return u;
    }

    @RequestMapping(value = "/sessionUser", method = RequestMethod.GET)
    public String sessionUser(@ModelAttribute("sessionUser") User user, HttpServletRequest request) {
        System.out.println("session value:" + request.getSession().getAttribute("sessionUser"));
        System.out.println("username : " + user.getUsername() + " password:" + user.getPassword());
        return "success";
    }

    @RequestMapping(value = "/clearSession", method = RequestMethod.GET)
    public String clearSession(SessionStatus status) {
        status.setComplete();
        return "index";
    }

    @RequestMapping(value = "/value", method = RequestMethod.GET)
    public String value(@Value("#{systemProperties['java.vm.version']}") String version) {
        System.out.println("jvm version : " + version);
        return "index";
    }
}
