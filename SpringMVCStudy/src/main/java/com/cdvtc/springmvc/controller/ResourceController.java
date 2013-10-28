package com.cdvtc.springmvc.controller;

import com.cdvtc.springmvc.vo.ShowFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenjian
 * Date: 13-10-27
 * Time: 上午8:40
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/resource")
@SuppressWarnings("uncheck")
public class ResourceController {
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String download(@ModelAttribute("showFiles") List<ShowFile> showFiles) {
        return "fileManage";
    }
    @ModelAttribute("showFiles")
    public List<ShowFile> getShowFile(HttpServletRequest request) {
        File[] uploadedFiles = new File(request.getServletContext().getRealPath("/upload")).listFiles();
        List<ShowFile> showFiles = new ArrayList<ShowFile>();
        ShowFile sf;
        for (File f : uploadedFiles) {
            sf = new ShowFile();
            if ("root".equals(f.getName())) continue;
            sf.setName(f.getName());
            sf.setUrl("/SpringMVCStudy/upload/" + f.getName());
            showFiles.add(sf);
        }
        return showFiles;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, @RequestParam(value = "fileName", defaultValue = "") String fileName, @RequestParam("file") MultipartFile file) {
        try {
            String originalName = file.getOriginalFilename();
            String suffix = originalName.substring(originalName.lastIndexOf("."), originalName.length());   //获取后缀名
            if ("".equals(fileName) || null == fileName)
                fileName = originalName;
            else
                fileName = fileName + suffix;
            String saveName = request.getServletContext().getRealPath("/upload") + "/" + fileName;
            OutputStream out = new FileOutputStream(saveName);
            out.write(file.getBytes(), 0, file.getBytes().length);
            out.close();
            System.out.println("success!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/resource/download";
    }
}
