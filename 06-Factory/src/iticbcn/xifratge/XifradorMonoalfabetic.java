package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {
    private static final char[] ORIGINAL = "abcdefghijklmnopqrstuvwxyzàèéêëìòóùüñç".toCharArray();
    private char[] permuta;

    public XifradorMonoalfabetic() {
        permutaAlfabet();
    }

    public void permutaAlfabet() {
        // Convertim el array a llista
        List<Character> llistaAlfabet = new ArrayList<>();
        for (char c : ORIGINAL) {
            llistaAlfabet.add(c);
        }
        // Permutem l'ordre aleatòriament
        Collections.shuffle(llistaAlfabet);

        // Tornem a convertir a array
        permuta = new char[llistaAlfabet.size()];
        for (int i = 0; i < llistaAlfabet.size(); i++) {
            permuta[i] = llistaAlfabet.get(i);
        }
    }

    public String xifraMonoAlfa(String cadena) {
        StringBuilder xifrat = new StringBuilder();
        HashMap<Character, Character> mapaXifratge = crearMapaXifratge();

        for (char c : cadena.toCharArray()) {
            if (Character.isLowerCase(c)) {
                xifrat.append(mapaXifratge.get(c));
            }
            else if (Character.isUpperCase(c)) {
                char lowerC = Character.toLowerCase(c);
                char xifratLower = mapaXifratge.get(lowerC);
                xifrat.append(Character.toUpperCase(xifratLower));
            }
            else {
                xifrat.append(c);
            }
        }
        return xifrat.toString();
    }

    public String desxifrarMonoAlfa(String cadena) {
        StringBuilder desxifrat = new StringBuilder();
        HashMap<Character, Character> mapaDesxifratge = crearMapaDesxifratge();

        for (char c : cadena.toCharArray()) {
            if (Character.isLowerCase(c)) {
                desxifrat.append(mapaDesxifratge.get(c));
            }
            else if (Character.isUpperCase(c)) {
                char lowerC = Character.toLowerCase(c);
                char desxifratLower = mapaDesxifratge.get(lowerC);
                desxifrat.append(Character.toUpperCase(desxifratLower));
            }
            else {
                desxifrat.append(c);
            }
        }
        return desxifrat.toString();
    }

    private HashMap<Character, Character> crearMapaXifratge() {
        HashMap<Character, Character> mapa = new HashMap<>();
        for (int i = 0; i < ORIGINAL.length; i++) {
            mapa.put(ORIGINAL[i], permuta[i]);
        }
        return mapa;
    }

    private HashMap<Character, Character> crearMapaDesxifratge() {
        HashMap<Character, Character> mapa = new HashMap<>();
        for (int i = 0; i < ORIGINAL.length; i++) {
            mapa.put(permuta[i], ORIGINAL[i]);
        }
        return mapa;
    }

    public static void main(String[] args) {
        XifradorMonoalfabetic monoalfabetic = new XifradorMonoalfabetic();
        String cadena = "El Barça guanyarà la Champions!";
        String xifrat = monoalfabetic.xifraMonoAlfa(cadena);
        System.out.println("Text xifrat: " + xifrat);
        String desxifrat = monoalfabetic.desxifrarMonoAlfa(xifrat);
        System.out.println("Text desxifrat: " + desxifrat); 
    }
}
