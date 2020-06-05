package com.whp.socket;

import java.net.*;
import java.util.Enumeration;

/**
 * @Classname InetAddressExample
 * @Describe TODO
 * @Date 2020/5/19
 * @Auth whp
 * @Version 1.0
 */
public class InetAddressExample {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();  //获取本地服务地址信息
            if (interfaceList != null) {
                while (interfaceList.hasMoreElements()) {
                    NetworkInterface iface = interfaceList.nextElement();
                    System.out.println( "interface " + interfaceList.nextElement().getName() + ":" );
                    Enumeration<InetAddress> addrList = iface.getInetAddresses();  //打印地址信息
                    while (addrList.hasMoreElements()) {
                        InetAddress address = addrList.nextElement();
                        System.out.print( "\tAddress" + (address instanceof Inet4Address ? "(v4)" : (address instanceof Inet6Address ? "(v6)" : "(?)")) );
                        System.out.println( address.getHostAddress() );
                    }
                }
                try {
                    for (String host : args) {
                        InetAddress[] addresses = InetAddress.getAllByName( host );
                        for(InetAddress address:addresses){
                            System.out.println("\t"+address.getHostName()+"/"+address.getHostAddress());
                        }
                    }
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }
}
