package parttern.chain;

public class ErrorLogInfo extends AbstractLogInfo{
    public ErrorLogInfo(int level){
        this.level=level;
    }
    @Override
    void write(String message) {
        System.out.println("Error message:"+message);
    }
}
