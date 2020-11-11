package com.example.demo.test.IO;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UploadControler {

    @RequestMapping(value="/up", method = RequestMethod.POST)
    public String up (String desc, MultipartFile uploadFile, HttpSession session) throws Exception {
        //获取上传文件的名称
        String fileName = uploadFile.getOriginalFilename();
        //解决文件重名问题
        String finalFileName = UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));
        //获取上传文件的路径
        String path = "D:\\JT-SOFTWARE"+ File.separator+finalFileName;
        uploadFile.transferTo(new File(path));
        return "success";
    }

    @RequestMapping(value="/up_old", method = RequestMethod.POST)
    public String up_old (String desc,MultipartFile uploadFile,HttpSession session) throws Exception {
        //获取上传文件的名称
        String fileName = uploadFile.getOriginalFilename();
        //获取上传文件的路径
        String realPath = session.getServletContext().getRealPath("");
        //自定义文件上传目录
        String path = "D:\\JT-SOFTWARE"+File.separator+fileName;
        //获取输入流
        InputStream is = uploadFile.getInputStream();
        //获取输出流
        OutputStream os = new FileOutputStream(new File(path));
        //开始复制
        int i = 0;
        byte[] bytes = new byte[1024];
        while((i = is.read(bytes))!=-1) {
            os.write(bytes, 0, i);
        }
        os.close();
        is.close();
        return "success";

    }

    @RequestMapping(value = "/down" , method = RequestMethod.POST)
    public ResponseEntity<byte[]> down(HttpSession session) throws Exception{
        //获取下载文件的路径 (获取tomcat服务器的位置)
        String realPath = session.getServletContext().getRealPath("img");
        String finalPath = "D:\\JT-SOFTWARE\\"+ "343ECA80C4504BBAB3C9D9EBFE475254.jpg";
        //创建字节输入流
        InputStream in = new FileInputStream(finalPath);
        //available():获取输入流所读取的文件的最大字节数
        byte[] body = new byte[in.available()];
        //把字节读取到数组中
        in.read(body);
        //设置请求头
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=aaa.jpg");
        //设置响应状态
        HttpStatus statusCode = HttpStatus.OK;
        in.close();


        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        String s = JSON.toJSONString(entity);
        System.out.println("entity = " + s);
        return entity;

    }
}
