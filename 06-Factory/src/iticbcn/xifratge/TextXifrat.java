package iticbcn.xifratge;

import java.util.Arrays;

public class TextXifrat {
    private final byte[] dadesXifrades;
    
    public TextXifrat(byte[] dadesXifrades) {
        this.dadesXifrades = dadesXifrades;
    }
    
    public byte[] getBytes() {
        return dadesXifrades;
    }

    public void setBytes(Byte[] dadesXifrades) {
        this.dadesXifrades = dadesXifrades;
    }

    @Override
    public String toString() {
        return Arrays.toString(dadesXifrades);
    }
}