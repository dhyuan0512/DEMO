package com.example.demo.test.Io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Controller
@RequestMapping("api/socket/service/io")
public class SocketService {

    @RequestMapping("get")
    public void get() throws Exception {
        ServerSocket ss = new ServerSocket(9999);
        System.out.println("服务器端以及启动正在接受数据");
        Socket s = ss.accept();
        System.out.println("检测到客户端");
        InputStream in = s.getInputStream();
        FileOutputStream fos = new FileOutputStream("src\\com\\day1\\2.jpg");
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = in.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        //收到图片，给客户端回送信息
        OutputStream out = s.getOutputStream();
        out.write("上传成功".getBytes());
        //关闭资源
        fos.close();
        in.close();
        out.close();
        s.close();
        ss.close();
    }
}
