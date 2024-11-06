package iticbcn.xifratge;

public class AlgorismeAES extends AlgorismeFactory {
    @Override
    public Xifrador creXifrador() {
        return new XifradorAES();
    }
}