package parttern.chain;

public abstract class AbstractLogInfo {
    public static int INFO=0;
    public static int DEBUG=1;
    public static int ERROR=2;
    protected AbstractLogInfo nextLog;
    protected int level;

    public void setNextLog(AbstractLogInfo nextLog){
        this.nextLog=nextLog;
    }

    public void printLog(int level,String msg){
        if(level<=this.level){
            write(msg);
        }
        if(nextLog!=null){
            nextLog.printLog(level,msg);
        }
    }

    abstract void write(String message);
}
