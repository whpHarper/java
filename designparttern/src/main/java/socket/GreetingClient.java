package socket;

import java.io.*;
import java.net.Socket;

/**
 * @author whp 18-4-17
 */
public class GreetingClient {
    public static void main(String[] args){
        String servername="whp-MS-7918";  //搭建的服务器名称，必须在hosts文件中配置
        int port=Integer.parseInt("80"); //端口号要开启
        try{
            System.out.println("连接到主机："+servername+",端口号："+port);
            Socket client=new Socket(servername,port);
            System.out.println("远程主机地址："+client.getRemoteSocketAddress());
            OutputStream outToServer=client.getOutputStream();
            DataOutputStream out=new DataOutputStream(outToServer);

            out.writeUTF("Hello from "+client.getLocalSocketAddress());
            InputStream inFromServer=client.getInputStream();
            DataInputStream in=new DataInputStream(inFromServer);
            System.out.println("服务器响应："+in.readUTF());
            client.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
