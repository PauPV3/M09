import java.util.Collections;
import java.util.List;

public class Monalfabetic {
    private static final char[] ORIGINAL = "abcdefghijklmnopqrstuvwxyzàèéêëìòóùüñç".toCharArray();
    private char[] PERMUTA;
    
    public void permutaAlfabet() {
        //Convertim el array a llista
        List<Character> llistaAlfabet = new ArrayList<>();
        for (char c : ORIGINAL) {
            llistaAlfabet.add(c);
        }
        //Aixo ho possa aleatori
        Collections.shuffle(llistaAlfabet);

        //Ara tornem a passar-ho a array
        PERMUTA = new Char[llistaAlfabet.size()];
        for (int i = 0; i < llistaAlfabet.size(); i++) {
            PERMUTA[i] = llistaAlfabet.get(i);
        }
    }

    public void xifraMonoAlfa(String cadena) {

    }

    public void desxifraMonoAlfa(String cadena) {

    }

}