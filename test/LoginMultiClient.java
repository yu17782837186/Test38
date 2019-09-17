package cn.com.Inet2;

import java.io.*;
import java.net.Socket;

public class LoginMultiClient {
    //模拟登录 多个客户端请求
    //先请求后响应
    public static void main(String[] args) throws IOException {
        System.out.println("------client------");
        Socket client = new Socket("localhost",6666);
        new Request(client).Request();
        new Response(client).Response();
        client.close();
    }
    //发送
    static class Request {
        private Socket client;
        private DataOutputStream dos;
        private BufferedReader console;
        private String msg;
        public Request(Socket client) {
            console = new BufferedReader(new InputStreamReader(System.in));
            this.msg = init();
            this.client = client;
            try {
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private String init() {
            try {
                System.out.print("请输入你的用户名：");
                String uname = console.readLine();
                System.out.print("请输入你的密码：");
                String upwd = console.readLine();
                return "uname="+uname+"&"+"upwd="+upwd;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        public void Request() {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //接收
    static class Response {
        private Socket client;
        private DataInputStream dis;
        public Response(Socket client) {
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void Response() {
            String result = null;
            try {
                result = dis.readUTF();
                System.out.println(result);
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
