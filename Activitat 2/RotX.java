public class RotX {
    private static final char[] MINUSCULES = "abcdefghijklmnopqrstuvwxyzàèéêëìòóùüñç".toCharArray();
    private static final char[] MAJUSCULES = "ABCDEFGHIJKLMNOPQRSTUVWXYZÀÈÉÊËÌÒÓÙÜÑÇ".toCharArray();

    public static String xifraRot13(String cadena) {
        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {
            char car = cadena.charAt(i);
            resultat.append(xifraDesxifraCaracter(car, 13));
        }

        return resultat.toString();
    }

    public static String desxifraRot13(String cadena) {
        return xifraRot13(cadena);
    }

    private static char xifraDesxifraCaracter(char car, int desplaçament) {
        if (Character.isLowerCase(car)) {
            int index = (car - 'a' + desplaçament) % 26;
            return MINUSCULES[index];
        } else if (Character.isUpperCase(car)) {
            int index = (car - 'A' + desplaçament) % 26;
            return MAJUSCULES[index];
        } else {
            return car;
        }
    }

    public static String xifraRotX(String cadena, int desplaçament) {
        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {
            char car = cadena.charAt(i);
            resultat.append(xifraDesxifraCaracter(car, desplaçament, true));
        }
        return resultat.toString();
    }

    public static String desxifraRotX(String cadena, int desplaçament) {
        return xifraRotX(cadena, -desplaçament);
    }

    public static void forcaBrutaRotX(String cadenaXifrada) {
        for (int desplaçament = 1; desplaçament <= MINUSCULES.length; desplaçament ++) {
            String desxifrat = desxifraRotX(cadenaXifrada, desplaçament);
            System.out.println("Despplaçament "+ desplaçament+": " + desxifrat);
        }
    }

    private static char xifraDesxifraCaracter(char car, int desplaçament, boolean xifrar) {
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

    public static void main(String[] args) {
        String original = "Prova de ROTX.";
        int desplaçament = 3;

        String xifrada = xifraRotX(original, desplaçament);
        System.out.println("Text original: " + original);
        System.out.println("Text xifrat amb desplaçament " + desplaçament + ": " + xifrada);

        String desxifrada = desxifraRotX(xifrada, desplaçament);
        System.out.println("Text desxifrat: " + desxifrada);

        System.out.println("Força bruta dle text xifrat:");
        forcaBrutaRotX(xifrada);
    }
}