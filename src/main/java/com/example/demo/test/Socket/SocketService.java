package com.example.demo.test.Socket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

@Controller
@RequestMapping("api/socket/service/io")
public class SocketService {

    @GetMapping("get")
    public void get() throws Exception {
        ServerSocket ss = new ServerSocket(8080);
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

    public static void main(String[] args) {
        String filePath = "src/main/java/com/example/demo/test/Io/SocketService.java";
        // 根据指定路径创建文件对象
        File file = new File(filePath);
        System.out.println("文件名称:" + file.getName());
        System.out.println("文件是否存在：" + file.exists());
        System.out.println("文件的相对路径：" + file.getPath());
        System.out.println("文件的绝对路径：" + file.getAbsolutePath());
        System.out.println("是否为可执行文件：" + file.canExecute());
        System.out.println("文件可以读取：" + file.canRead());
        System.out.println("文件可以写入：" + file.canWrite());
        System.out.println("文件上级路径：" + file.getParent());
        System.out.println("文件大小：" + file.length() + "B");
        System.out.println("文件最后修改时间：" + new Date(file.lastModified()));
        System.out.println("是否文件类型：" + file.isFile());
        System.out.println("是否为文件夹：" + file.isDirectory());

    }
}
