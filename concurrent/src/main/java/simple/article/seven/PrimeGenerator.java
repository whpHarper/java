package simple.article.seven;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class PrimeGenerator implements Runnable {

    private final List<BigInteger> primes=new ArrayList<BigInteger>();
    private volatile Boolean cancelled=false;

    public void run() {
        BigInteger p=BigInteger.ONE;
        while(!cancelled){
            p=p.nextProbablePrime();
            synchronized (this){
                primes.add(p);
            }
        }
    }

    public void cancell(){
        cancelled=true;
    }

    public synchronized List<BigInteger> get(){
        return new ArrayList<BigInteger>(primes);
    }

    public static void main(String[] args){
        PrimeGenerator  generator=new PrimeGenerator();
        new Thread(generator).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            generator.cancell();
        }
        List<BigInteger> result=generator.get();
        for (BigInteger i:result) {
            System.out.println(i);
        }
    }
}
