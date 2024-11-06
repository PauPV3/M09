package iticbcn.xifratge;

public class AlgorismePolialfabetic extends AlgorismeFactory {

    @Override
    public Xifrador creXifrador() {
        return new XifradorPolialfabetic();
    }
}