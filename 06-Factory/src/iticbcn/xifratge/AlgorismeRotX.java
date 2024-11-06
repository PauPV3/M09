package iticbcn.xifratge;

public class AlgorismeRotX extends AlgorismeFactory {

    @Override
    public Xifrador creXifrador() {
        return new XifradorRotX();
    }
}