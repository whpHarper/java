package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * @author whp 18-4-17
 */
public class GreetingServer extends Thread{
    private ServerSocket serverSocket;

    public GreetingServer(int port) throws IOException {
        serverSocket=new ServerSocket(port);
        serverSocket.setSoTimeout(20000);
    }

    public void run()
    {
        while(true){
            try{
                System.out.println("等待远程链接，端口号为："+serverSocket.getLocalPort());
                Socket server=serverSocket.accept();
                System.out.println("远程主机地址"+server.getRemoteSocketAddress());
                DataInputStream in=new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());
                DataOutputStream out=new DataOutputStream(server.getOutputStream());
                out.writeUTF("谢谢连接我："+server.getLocalSocketAddress()+"\nGoodbye!");
                server.close();
            }catch (SocketTimeoutException s){
                break;
            }catch (IOException e){
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args){
        int port=Integer.parseInt("80");
        try{
            Thread t=new GreetingServer(port);
            t.run();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
