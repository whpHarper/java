package parttern.chain;

public class InfoLogInfo extends AbstractLogInfo{
    public InfoLogInfo(int level){
        this.level=level;
    }
    @Override
    void write(String message) {
        System.out.println("Info message---"+message);
    }
}
