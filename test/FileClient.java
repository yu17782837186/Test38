package cn.com.Inet2;

import java.io.*;
import java.net.Socket;

public class FileClient {
    //客户端上传文件
    public static void main(String[] args) throws IOException {
        System.out.println("------client-------");
        Socket client = new Socket("localhost",6666);
        //文件的拷贝 上传
        InputStream is = new BufferedInputStream(new FileInputStream("F:/java code/Inet2/ndl.png"));
        OutputStream os = new BufferedOutputStream(client.getOutputStream());
        byte[] flush = new byte[1024];
        int len;
        while((len = is.read(flush)) != -1) {
            os.write(flush,0,len);
        }
        os.flush();
        os.close();
        is.close();
        client.close();
    }
}
