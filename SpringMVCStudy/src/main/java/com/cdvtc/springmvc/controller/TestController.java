package com.cdvtc.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: chenjian
 * Date: 13-10-20
 * Time: 下午1:49
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class TestController {
    @RequestMapping("test.do")
    public void test(PrintWriter out, String username, String password) {
        out.print("username: " + username + " - password:" + password);
    }
}
