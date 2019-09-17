package cn.com.Inet2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginMultiServer {
    //模拟登录 多个客户端请求  加入多线程
    public static void main(String[] args) throws IOException {
        System.out.println("----server-----");
        ServerSocket server = new ServerSocket(6666);
        boolean isRunning = true;
        while(isRunning) {
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接");
            new Thread(new channel(client)).start();
        }
        server.close();
    }
    //一个channel代表一个客户端
    static class channel implements Runnable {
        private Socket client;
        //输入流
        private DataInputStream dis;
        //输出流
        private DataOutputStream dos;
        public channel(Socket client) {
            this.client= client;
            try {
                //输入
                dis = new DataInputStream(client.getInputStream());
                //输出
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }
        }
        //接受数据
        private String receive() {
            String datas = null;
            try {
                datas = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return datas;
        }
        //发送数据
        private void send(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //释放资源
        private void release() {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            String uname = "";
            String upwd = "";
            String[] dataArray = receive().split("&");
            for (String info : dataArray) {
                String[] useInfo = info.split("=");
                if (useInfo[0].equals("uname")) {
                    System.out.println("用户名：" + useInfo[1]);
                    uname = useInfo[1];
                } else if (useInfo[0].equals("upwd")) {
                    System.out.println("密码：" + useInfo[1]);
                    upwd = useInfo[1];
                }
            }
            if (uname.equals("sh") && upwd.equals("123")) {
                send("成功");
            } else {
                send("失败");
            }
            release();
        }
    }
}
