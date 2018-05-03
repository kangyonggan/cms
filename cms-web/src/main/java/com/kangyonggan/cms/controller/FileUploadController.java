package com.kangyonggan.cms.controller;

import com.kangyonggan.base.dto.Response;
import com.kangyonggan.base.util.FileUpload;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author kangyonggan
 * @date 2018/5/1 0001
 */
@Controller
@RequestMapping("file")
@Log4j2
public class FileUploadController {

    @Value("${cms.file.dir}")
    private String fileDir;

    @Value("${cms.upload.dir}")
    private String uploadDir;

    /**
     * 编辑器文件、图片上传
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "editor", method = RequestMethod.POST)
    @ResponseBody
    public Response upload(@RequestParam(value = "imgFile") MultipartFile file,
                           HttpServletRequest request) {
        Response response = Response.getResponse();
        ServletContext context = request.getServletContext();
        String ctx = context.getContextPath();

        //上传本地文件
        String fileName = null;
        int error = 0;
        try {
            fileName = FileUpload.upload(fileDir, uploadDir, file, "EDITOR");
        } catch (Exception e) {
            log.error("编辑器上传失败", e);
            error = -1;
        }

        response.put("error", error);
        response.put("url", ctx + fileName);
        return response;
    }

    /**
     * ajax文件上传
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public Response upload(@RequestParam(value = "file", required = false) MultipartFile file) {
        String fileName = null;
        String status = "success";
        try {
            fileName = FileUpload.upload(fileDir, uploadDir, file, "EDITOR");
        } catch (Exception e) {
            log.error("上传失败", e);
            status = "fail";
        }
        Response response = Response.getResponse();
        response.put("fileName", fileName);
        response.put("status", status);
        return response;
    }


}
