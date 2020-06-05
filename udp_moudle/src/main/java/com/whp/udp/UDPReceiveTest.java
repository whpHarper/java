package com.whp.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @Classname UDPReceiveTest
 * @Describe TODO
 * @Date 2020/5/16
 * @Auth whp
 * @Version 1.0
 */
public class UDPReceiveTest {
    public static void main(String[] args) throws IOException {
            DatagramSocket ds = new DatagramSocket( 10086 );
            while(true) {
            byte[] bytes = new byte[1024];
            int length = bytes.length;
            DatagramPacket dp = new DatagramPacket( bytes, length );
            ds.receive( dp );
            InetAddress address = dp.getAddress();
            byte[] data = dp.getData();
            int len = dp.getLength();
            System.out.println( address );
            System.out.println( new String( data, 0, len ) );
//            ds.close();close
        }
    }
}
