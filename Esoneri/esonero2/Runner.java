package esonero2;

public class Runner extends Thread {

    protected Pista p;
    protected long delay;

    public Runner (String nome, Pista p, int delay) {
        super(nome);
        this.p = p;
        this.delay = delay;
    }
}