package pl.lfranczyk;

public class State {

    private int LKL, LML, LKP, LMP;
    State p;
    private boolean boatOnLeft;

    public State(int LKL, int LML, int LKP, int LMP, boolean boatOnLeft, State p) {
        this.LKL = LKL;
        this.LML = LML;
        this.LKP = LKP;
        this.LMP = LMP;
        this.boatOnLeft = boatOnLeft;
        this.p = p;
    }

    public pl.lfranczyk.State getP() {
        return p;
    }

    public void setP(pl.lfranczyk.State p) {
        this.p = p;
    }


    public int getLKL() {
        return LKL;
    }

    public void setLKL(int LKL) {
        this.LKL = LKL;
    }

    public int getLML() {
        return LML;
    }

    public void setLML(int LML) {
        this.LML = LML;
    }

    public int getLKP() {
        return LKP;
    }

    public void setLKP(int LKP) {
        this.LKP = LKP;
    }

    public int getLMP() {
        return LMP;
    }

    public void setLMP(int LMP) {
        this.LMP = LMP;
    }

    public boolean isBoatOnLeft() {
        return boatOnLeft;
    }

    public void setBoatOnLeft(boolean boatOnLeft) {
        this.boatOnLeft = boatOnLeft;
    }


    public String printState() {
        return "State{" +
                "LKL=" + LKL +
                ", LML=" + LML +
                ", LKP=" + LKP +
                ", LMP=" + LMP +
                ", boatOnLeft=" + boatOnLeft +
                '}';
    }

    @Override
    public String toString() {
        return "State{" +
                "LKL=" + LKL +
                ", LML=" + LML +
                ", LKP=" + LKP +
                ", LMP=" + LMP +
                ", boatOnLeft=" + boatOnLeft +
                ", parent=" + p +
                '}';
    }
}

