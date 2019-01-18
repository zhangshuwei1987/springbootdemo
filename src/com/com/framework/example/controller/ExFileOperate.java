package com.framework.example.controller;

import com.framework.entity.TblAttachment;
import com.framework.service.TblAttachmentServiceImpl;
import com.framework.util.FileUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(value = "上传下载文件示例",description = "上传下载操作示例")
@RestController
@RequestMapping(value = "ex-file-operate")
public class ExFileOperate {

    @Autowired
    TblAttachmentServiceImpl attachmentService;
    /*
        headers:规定请求的content-type，SWAGGER自动解析该属性并将更新该请求的CONTENT-TYPE
     */
    @RequestMapping(value = "/do-upload",method = RequestMethod.POST,headers = {"content-type=multipart/form-data"})
    public Object doUpload(HttpServletRequest request,@RequestParam("file") MultipartFile[] files) throws IOException {
        return FileUtil.doUpload(files,request,"/upload");
    }

    @RequestMapping(value = "/do-download",method = {RequestMethod.POST,RequestMethod.GET})
    public void doDownload(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer id) throws IOException {
        TblAttachment tblAttachment = attachmentService.get(id);
        FileUtil.doDownload(tblAttachment.getFilePath(), tblAttachment.getName(),request, response);
    }


}
