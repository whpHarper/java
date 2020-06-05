package com.whp.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

/**
 * @Classname UDPSendTest
 * @Describe TODO
 * @Date 2020/5/16
 * @Auth whp
 * @Version 1.0
 */
public class UDPSendTest {
    public static void main(String[] args) throws Exception{
        DatagramSocket ds = new DatagramSocket();
        while(true) {
           String s = "my name is happywindwan" + new Date();
           byte[] bytes = s.getBytes();
           int length = s.length();
//           InetAddress ip = InetAddress.getByName( "192.168.10.131" );
        InetAddress ip = InetAddress.getByName( "127.0.0.1" );
//           int port = 52999;
        int port = 10086;
           DatagramPacket dp = new DatagramPacket( bytes, length, ip, port );
           ds.send( dp );
//            ds.close();
        }
    }
}
