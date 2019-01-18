package com.framework.util;

import com.framework.bean.FileAttrBean;
import com.framework.entity.TblAttachment;
import com.framework.exception.ZswException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FileUtil {

    public static List<FileAttrBean> doUpload(MultipartFile[] files, HttpServletRequest request,String folderPath) throws IOException {

        List<FileAttrBean> fileAttrBeanList = new ArrayList<>();

        String uploadDir = ContextUtil.getRealPath(request,folderPath);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String monthDirName = sdf.format(new Date());
        uploadDir += File.separator+monthDirName;
        File dir = new File(uploadDir);
        if(!dir.exists()){
            dir.mkdirs();
        }

        for(int i = 0;i<files.length;i++){
            MultipartFile file = files[i];
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID()+suffix;
            String absoluteFilePath = uploadDir+File.separator+fileName;
            File serverFile = new File(absoluteFilePath);
            file.transferTo(serverFile);

            FileAttrBean fileAttrBean = new FileAttrBean();
            fileAttrBean.setSourceName(file.getOriginalFilename());
            fileAttrBean.setAbsolutePath(absoluteFilePath);
            fileAttrBean.setSuffix(suffix);
            fileAttrBean.setSize(file.getSize());
            fileAttrBean.setNewFile(serverFile);
            fileAttrBean.setNewName(fileName);
            fileAttrBean.setRelativePath((folderPath+"/"+fileName).substring(1));
            fileAttrBeanList.add(fileAttrBean);
        }

        return fileAttrBeanList;

    }

    public static void doDownload(String absolutePath, String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String userAgent = request.getHeader("User-Agent");
        // 针对IE或者以IE为内核的浏览器：
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            // 非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        File file = new File(absolutePath);
        if (!file.exists()) throw new ZswException("404", "文件不存在！");

        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try{
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream  os= response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }catch (Exception e) {
            throw e;
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
