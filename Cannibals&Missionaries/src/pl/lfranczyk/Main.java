package pl.lfranczyk;

public class Main {
    public static void main(String[] args) {

        final State state = new State(3,3,0,0,true,null);

        new Search(state).lookup();
    }
}