package cn.com.Inet2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginTwoWayServer {
    //模拟登录 双向
    public static void main(String[] args) throws IOException {
//        System.out.println("---server---");
//        ServerSocket server = new ServerSocket(8888);
//        Socket client = server.accept();
//        DataInputStream dis = new DataInputStream(client.getInputStream());
//        String datas = dis.readUTF();
//        String uname="";
//        String upwd="";
//        String[] dataArray = datas.split("&");
//        for (String info:dataArray) {
//            String[] userInfo = info.split("=");
//            if (userInfo[0].equals("uname")) {
//                System.out.println("你的用户名为："+userInfo[1]);
//                uname = userInfo[1];
//            }else if(userInfo[0].equals("upwd")) {
//                System.out.println("你的密码为："+userInfo[1]);
//                upwd = userInfo[1];
//            }
//        }
//        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
//        if (uname.equals("sh") && upwd.equals("123")) {
//            dos.writeUTF("成功");
//        }else {
//            dos.writeUTF("失败");
//        }
//        dos.flush();
//        client.close();
//        server.close();
        System.out.println("----server-----");
        ServerSocket server = new ServerSocket(6666);
        Socket client = server.accept();
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String datas = dis.readUTF();
        String uname = "";
        String upwd = "";
        String[] dataArray = datas.split("&");
        for (String info:dataArray) {
            String[] userInfo = info.split("=");
            if (userInfo[0].equals("uname")) {
                System.out.println("请输入用户名："+userInfo[1]);
                uname = userInfo[1];
            }else if (userInfo[0].equals("upwd")) {
                System.out.println("请输入密码："+userInfo[1]);
                upwd = userInfo[1];
            }
        }
        DataOutputStream dos  = new DataOutputStream(client.getOutputStream());
        if (uname.equals("sh") && upwd.equals("123")) {
            dos.writeUTF("登录成功");
        }else {
            dos.writeUTF("登陆失败");
        }
        dos.flush();
        dos.close();
        dis.close();
        client.close();
        server.close();
    }
}
