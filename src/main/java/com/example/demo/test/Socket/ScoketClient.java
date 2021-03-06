package com.example.demo.test.Socket;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

@Controller
@RequestMapping("/api")
public class ScoketClient {

    @GetMapping("/socket/client/io/set")
    @ApiOperation("客户端接口")
    public void set() throws Exception {
        //1.连接诶服务器
        Socket s = new Socket("127.0.0.1", 8094);
        System.out.println("已连接到服务器8094端口，准备传送图片...");
        //获取图片字节流
        InputStream fis = new FileInputStream("src/main/java/com/example/demo/test/Io/SocketService.java");
        //获取输出流
        //OutputStream out = s.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
        byte[] buf = new byte[1024];
        int len = 0;
        //2.往输出流里面投放数据
        while ((len = fis.read(buf)) != -1) {
            bos.write(buf, 0, len);
        }
        //通知服务端，数据发送完毕
        s.shutdownOutput();
        //3.获取输出流，接受服务器传送过来的消息“上传成功”
        //InputStream in = s.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
        byte[] bufIn = new byte[1024];
        int num = bis.read(bufIn);
        System.out.println(new String(bufIn, 0, num));
        //关闭资源
        fis.close();
        bos.close();
        bis.close();
        s.close();
    }
}
