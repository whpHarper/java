package parttern.builder;

public abstract class Computer {
    public String getDashBoard() {
        return dashBoard;
    }

    public void setDashBoard(String dashBoard) {
        this.dashBoard = dashBoard;
    }

    public String getOs() {
        return os;
    }

    public abstract void setOs(String os) ;

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    private String dashBoard;
    protected String os;
    private String display;

}
