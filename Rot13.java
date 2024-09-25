public class Rot13 {

    private static final char[] MINUSCULES = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] MAJUSCULES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

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

    public static void main(String[] args) {
        String original = "Prova de ROT13.";
        String xifrada = xifraRot13(original);
        String desxifrada = desxifraRot13(xifrada);

        System.out.println("Text original: " + original);
        System.out.println("Text xifrat amb ROT13: " + xifrada);
        System.out.println("Text desxifrat: " + desxifrada);
    }
}
