package iticbcn.xifratge;
import java.util.*;

public class XifradorPolialfabetic {
    
    private static final char[] ALFABET = "abcdefghijklmnopqrstuvwxyzçñàáèéíïòóúü".toCharArray();
    private static List<Character> permutatedAlfabet;
    private static Random random;

    public static void initRandom(String clauSecreta) {
        random = new Random(clauSecreta.hashCode() * 31);  // Potser no cal multiplicar per 31, però funciona
    }

    public static void permutaAlfabet() {
        List<Character> alfabetList = new ArrayList<>();
        for (char c : ALFABET) {
            alfabetList.add(c);
        }
        Collections.shuffle(alfabetList, random);
        permutatedAlfabet = alfabetList;
    }

    public static String xifraPoliAlfa(String msg) {
        StringBuilder msgXifrat = new StringBuilder();
        for (char c : msg.toLowerCase().toCharArray()) {
            if (Character.isLetter(c) || "çñàáèéíïòóúü".indexOf(c) != -1) {
                permutaAlfabet(); 
                int indexOriginal = findIndex(c, ALFABET);
                if (indexOriginal != -1) { 
                    msgXifrat.append(permutatedAlfabet.get(indexOriginal));
                } else {
                    msgXifrat.append(c); 
                }
            } else {
                msgXifrat.append(c);
            }
        }
        return msgXifrat.toString();
    }

    public static String desxifraPoliAlfa(String msgXifrat) {
        StringBuilder msgDesxifrat = new StringBuilder();
        for (char c : msgXifrat.toLowerCase().toCharArray()) {
            if (Character.isLetter(c) || "çñàáèéíïòóúü".indexOf(c) != -1) {
                permutaAlfabet(); 
                int indexPermutated = findIndex(c, permutatedAlfabet);
                if (indexPermutated != -1) {
                    msgDesxifrat.append(ALFABET[indexPermutated]);
                } else {
                    msgDesxifrat.append(c); 
                }
            } else {
                msgDesxifrat.append(c);
            }
        }
        return msgDesxifrat.toString();
    }

    private static int findIndex(char c, char[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1;
    }

    private static int findIndex(char c, List<Character> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == c) {
                return i;
            }
        }
        return -1;  
    }

    public static void main(String[] args) {
        String clauSecreta = "RodriBalonDeOro";
        String msgs[] = {
            "Messi millor que Crisiano",
            "El Barça guanyara la Champions",
            "Vinicus malo",
            "Lamine Yamal futur balon de oro"
        };
        String msgsXifrats[] = new String[msgs.length];
    
        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta); 
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-40s -> %s%n", msgs[i], msgsXifrats[i]);
        }
    
        System.out.println("\nDesxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta); 
            String msgDesxifrat = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-40s -> %s%n", msgsXifrats[i], msgDesxifrat);
        }
    }
}
