package simple.article.six;

import sun.misc.Request;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class Lifecycle {
    private final ExecutorService exeService=new ExcutorServiceImpl();

    private void start() throws IOException {
        ServerSocket serverSocke=new ServerSocket(80);
        while(!exeService.isShutdown()){
            final Socket conn=serverSocke.accept();
            exeService.execute(new Runnable() {
                public void run() {

                }
            });
        }
    }

    void handleRequest(Socket connection){
        Request request=readRequest(connection);
//        if(isShutdownRequest(request)){
//            stop();
//        }else{
//            dispatchRequest(request);
//        }
    }

    private Request readRequest(Socket connection) {
        return null;
    }
}
