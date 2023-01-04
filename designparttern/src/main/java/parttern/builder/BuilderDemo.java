package parttern.builder;

public class BuilderDemo {
    public static void main(String[] args) {
        Builder macBuilder = new MacBuilder();
        Directory directory=new Directory(macBuilder);
        directory.contruct("mac主板","mac 播放器");
        Computer computer = macBuilder.build();
        System.out.println(computer.toString());
    }
}
