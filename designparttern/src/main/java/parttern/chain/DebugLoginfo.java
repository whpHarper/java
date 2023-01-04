package parttern.chain;

public class DebugLoginfo extends AbstractLogInfo{

    public DebugLoginfo(int level){
        this.level=level;
    }
    @Override
    void write(String message) {
        System.out.println("debug info:"+message);
    }
}
