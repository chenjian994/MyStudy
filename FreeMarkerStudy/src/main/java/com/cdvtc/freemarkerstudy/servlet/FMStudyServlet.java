package com.cdvtc.freemarkerstudy.servlet;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: chenjian
 * Date: 13-10-13
 * Time: 下午7:38
 * To change this template use File | Settings | File Templates.
 */
public class FMStudyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(FMStudyServlet.class, "/template");
        cfg.setDefaultEncoding("UTF-8");
        try {
            Map<String, Object> root = new HashMap<String, Object>();
            Template temp = cfg.getTemplate("hello.ftl");
            temp.setEncoding("UTF-8");
            root.put("name", "张三");
            Writer out = new OutputStreamWriter(resp.getOutputStream(), "UTF-8");
            //TODO     尚未把值传入模版中
            temp.process(root, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
