package parttern.builder;

public class Directory {
    private Builder builder;

    public Directory(Builder builder){
        this.builder=builder;
    }

    public void contruct(String board,String dispay){
        builder.buildBoard(board);
        builder.buildDisplay(dispay);
        builder.buildOs();
    }
}
