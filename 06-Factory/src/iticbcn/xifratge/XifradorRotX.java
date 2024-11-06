package iticbcn.xifratge;

public class XifradorRotX {
    private static final char[] MINUSCULES = "abcdefghijklmnopqrstuvwxyzàèéêëìòóùüñç".toCharArray();
    private static final char[] MAJUSCULES = "ABCDEFGHIJKLMNOPQRSTUVWXYZÀÈÉÊËÌÒÓÙÜÑÇ".toCharArray();

    private int desplaçament;

    public XifradorRotX(int desplaçament) {
        this.desplaçament = desplaçament;
    }

    public String xifraRot13(String cadena) {
        return xifraRotX(cadena, 13);
    }

    public String desxifraRot13(String cadena) {
        return xifraRot13(cadena); 
    }

    public String xifraRotX(String cadena) {
        return xifraRotX(cadena, this.desplaçament);
    }

    public String desxifraRotX(String cadena) {
        return xifraRotX(cadena, -this.desplaçament);
    }

    private String xifraRotX(String cadena, int desplaçament) {
        StringBuilder resultat = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char car = cadena.charAt(i);
            resultat.append(xifraDesxifraCaracter(car, desplaçament, true));
        }
        return resultat.toString();
    }

    private char xifraDesxifraCaracter(char car, int desplaçament, boolean xifrar) {
        char[] alfabets = Character.isLowerCase(car) ? MINUSCULES : MAJUSCULES;
        for (int i = 0; i < alfabets.length; i++) {
            if (alfabets[i] == car) {
                int index;
                if (xifrar) {
                    index = (i + desplaçament) % alfabets.length;
                } else {
                    index = (i - desplaçament + alfabets.length) % alfabets.length;
                }
                return alfabets[index];
            }
        }
        return car;
    }

    public void forcaBrutaRotX(String cadenaXifrada) {
        for (int d = 1; d <= MINUSCULES.length; d++) {
            String desxifrat = xifraRotX(cadenaXifrada, -d);
            System.out.println("Desplaçament " + d + ": " + desxifrat);
        }
    }

    public static void main(String[] args) {
        String original = "Prova de ROTX.";
        int desplaçament = 3;

        XifradorRotX xifrador = new XifradorRotX(desplaçament);
        String xifrada = xifrador.xifraRotX(original);
        System.out.println("Text original: " + original);
        System.out.println("Text xifrat amb desplaçament " + desplaçament + ": " + xifrada);

        String desxifrada = xifrador.desxifraRotX(xifrada);
        System.out.println("Text desxifrat: " + desxifrada);

        System.out.println("Força bruta del text xifrat:");
        xifrador.forcaBrutaRotX(xifrada);
    }
}
