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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: chenjian
 * Date: 13-10-13
 * Time: 下午7:38
 * To change this template use File | Settings | File Templates.
 */
public class FMStudyServlet extends HttpServlet {
    public final static String ENCODING = "UTF-8";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setConfiguration(FMStudyServlet.class, "/template");
        String methodName = req.getParameter("methodName");
        Writer out = new OutputStreamWriter(resp.getOutputStream(), ENCODING);
        try {         //根据前台传入的方法名动态调用方法
            Method method = this.getClass().getMethod(methodName, Writer.class, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, out, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void helloWorld(Writer out, HttpServletRequest req, HttpServletResponse resp) {
        try {
            setTemplate("hello.ftl");
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("name", " world");
            template.process(root, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tagAndDirective(Writer out, HttpServletRequest req, HttpServletResponse resp) {
        try{
            setTemplate("tagAndDirective.ftl");
            Map<String, Object> root = new HashMap<String, Object>();
            String sex = req.getParameter("sex");
            root.put("sex", sex);
            List<Map<String, Object>> persons = new ArrayList<Map<String, Object>>();
            Map<String, Object> person;
            person = new HashMap<String, Object>();
            person.put("name", "张三");
            persons.add(person);

            person = new HashMap<String, Object>();
            person.put("name", "李四");
            persons.add(person);

            person = new HashMap<String, Object>();
            person.put("name", "王五");
            persons.add(person);

            root.put("persons", persons);
            template.process(root, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTemplate(String fileName) throws IOException {
        template = cfg.getTemplate(fileName);
        template.setEncoding(ENCODING);
    }

    private void setConfiguration(Class c, String path) {
        cfg = new Configuration();
        cfg.setClassForTemplateLoading(c, path);
        cfg.setDefaultEncoding(ENCODING);
    }

    private Configuration cfg;
    private Template template;

    public Configuration getCfg() {
        return cfg;
    }

    public void setCfg(Configuration cfg) {
        this.cfg = cfg;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
