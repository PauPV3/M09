package iticbcn.xifratge;

public class AlgorismeMonoalfabetic extends AlgorismeFactory {

    @Override
    public Xifrador creXifrador() {
        return new XifradorMonoalfabetic();
    }
}