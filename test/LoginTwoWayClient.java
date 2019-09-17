package cn.com.Inet2;

import java.io.*;
import java.net.Socket;

public class LoginTwoWayClient {
    //模拟登录 双向
    public static void main(String[] args) throws IOException {
//        System.out.println("----client----");
//        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
//        System.out.print("请输入用户名：");
//        String uname = console.readLine();
//        System.out.print("请输入密码：");
//        String upwd = console.readLine();
//        Socket client = new Socket("localhost",8888);
//        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
//        dos.writeUTF("uname="+uname+"&"+"upwd="+upwd);
//        dos.flush();
//        DataInputStream dis = new DataInputStream(client.getInputStream());
//        String result = dis.readUTF();
//        System.out.println(result);
//        dis.close();
//        dos.close();
//        client.close();
        System.out.println("----client-----");
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入你的用户名：");
        String uname = console.readLine();
        System.out.print("请输入你的密码：");
        String upwd = console.readLine();

        Socket client = new Socket("localhost",6666);
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF("uname="+uname+"&"+"upwd="+upwd);
        dos.flush();
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String result = dis.readUTF();
        System.out.println(result);
        dis.close();
        dos.close();
        client.close();
    }
}
