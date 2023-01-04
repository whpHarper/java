package parttern.builder;

public class MacBuilder extends Builder{
    private MacComputer macComputer=new MacComputer();

    @Override
    void buildBoard(String board) {
        macComputer.setDashBoard(board);
    }

    @Override
    void buildDisplay(String display) {
        macComputer.setDisplay(display);
    }

    @Override
    void buildOs() {
        macComputer.setOs("mac 123 ");
    }

    @Override
    Computer build() {
        return macComputer;
    }
}
