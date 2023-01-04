package parttern.chain;

public class DemoTest {
    public static AbstractLogInfo getLogChain(){
        ErrorLogInfo errorLogInfo=new ErrorLogInfo(AbstractLogInfo.ERROR);
        DebugLoginfo debugLoginfo=new DebugLoginfo(AbstractLogInfo.DEBUG);
        InfoLogInfo infoLogInfo=new InfoLogInfo(AbstractLogInfo.INFO);
        errorLogInfo.setNextLog(debugLoginfo);
        debugLoginfo.setNextLog(infoLogInfo);
        return errorLogInfo;
    }

    public static void main(String[] args) {
        AbstractLogInfo chain=getLogChain();
        chain.printLog(AbstractLogInfo.ERROR,"info level");
    }
}
