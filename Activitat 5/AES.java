import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "ElFCBGuanyaraElTriplet";

    public static byte[] xifraAES(String missatge, String contrasenya) throws Exception {
        byte[] missatgeBytes = missatge.getBytes("UTF-8");
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauBytes = sha.digest(contrasenya.getBytes("UTF-8"));
        clauBytes = Arrays.copyOf(clauBytes, 32);
        SecretKeySpec clauSecreta = new SecretKeySpec(clauBytes, ALGORISME_XIFRAT);

        Cipher xifrat = Cipher.getInstance(FORMAT_AES);
        xifrat.init(Cipher.ENCRYPT_MODE, clauSecreta, ivSpec);
        byte[] missatgeXifrat = xifrat.doFinal(missatgeBytes);
        byte[] combinatIvIMissatge = new byte[MIDA_IV + missatgeXifrat.length];
        System.arraycopy(iv, 0, combinatIvIMissatge, 0, MIDA_IV);
        System.arraycopy(missatgeXifrat, 0, combinatIvIMissatge, MIDA_IV, missatgeXifrat.length);

        return combinatIvIMissatge;
    }

    public static String desxifraAES(byte[] bytesXifrats, String contrasenya) throws Exception {
        System.arraycopy(bytesXifrats, 0, iv, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        byte[] missatgeXifrat = new byte[bytesXifrats.length - MIDA_IV];
        System.arraycopy(bytesXifrats, MIDA_IV, missatgeXifrat, 0, missatgeXifrat.length);

        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauBytes = sha.digest(contrasenya.getBytes("UTF-8"));
        clauBytes = Arrays.copyOf(clauBytes, 32);
        SecretKeySpec clauSecreta = new SecretKeySpec(clauBytes, ALGORISME_XIFRAT);
        Cipher xifrat = Cipher.getInstance(FORMAT_AES);
        xifrat.init(Cipher.DECRYPT_MODE, clauSecreta, ivSpec);

        byte[] missatgeDesxifrat = xifrat.doFinal(missatgeXifrat);

        return new String(missatgeDesxifrat, "UTF-8");
    }

    public static void main(String[] args) {
        String missatges[] = {"Lorem ipsum dicet",
                        "Hola Andrés cómo está tu cuñado",
                        "Àgora ïlla Ôtto"};
        
        for (int i = 0; i < missatges.length; i++) {
            String missatge = missatges[i];

            byte[] bytesXifrats = null;
            String desxifrat = "";
            try {
                bytesXifrats = xifraAES(missatge, CLAU);
                desxifrat = desxifraAES(bytesXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "+ e.getLocalizedMessage());
            }
            System.out.println("--------------------" );
            System.out.println("Msg: " + missatge);
            System.out.println("Enc: " + new String(bytesXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}
