package com.whp.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * @Classname TCPEchoClient
 * @Describe TODO
 * @Date 2020/5/19
 * @Auth whp
 * @Version 1.0
 */
public class TCPEchoClient {
    public static void main(String[] args) throws IOException {
        if((args.length<2)|| args.length>3)
            throw new IllegalArgumentException( "Parameter(s)" );
        String server=args[0];
        byte[] bytes=args[1].getBytes();
        int port=7;
        Socket soket=new Socket( server,port );
        System.out.println("connected to server ... sending echo string");
        InputStream in =soket.getInputStream();
        OutputStream out =soket.getOutputStream();
        out.write( bytes );
        int totalBytesRcvd=0;
        int bytesRcvd;
        while(totalBytesRcvd<bytes.length){
            if((bytesRcvd=in.read(bytes,totalBytesRcvd,bytes.length-totalBytesRcvd))==-1)
                throw new SocketException( "connection closed prematurely" );
            totalBytesRcvd+=bytesRcvd;
        }
        System.out.println( "Received:"+new String( bytes ) );
        soket.close();
    }
}
