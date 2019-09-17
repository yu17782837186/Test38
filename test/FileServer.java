package cn.com.Inet2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    //服务器端存储文件
    public static void main(String[] args) throws IOException {
        System.out.println("------server------");
        ServerSocket server  = new ServerSocket(6666);
        Socket client = server.accept();
        System.out.println("一个客户端建立了连接");
        //文件的拷贝 存储
        InputStream is = new BufferedInputStream(client.getInputStream());
        OutputStream os = new BufferedOutputStream(new FileOutputStream("F:/java code/Inet2/copy.png"));
        byte[] flush = new byte[1024];
        int len;
        while((len = is.read(flush)) != -1) {
            os.write(flush,0,len);
        }
        os.flush();
        os.close();
        is.close();
        client.close();
        server.close();
    }
}
