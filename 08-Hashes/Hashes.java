import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    private static final String CHARSET = "abcdefABCDEF1234567890!";
    public int npass = 0;

    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] hashedBytes = md.digest(pw.getBytes());
            HexFormat hex = HexFormat.of();
            return hex.formatHex(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), 10000, 128);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hashedBytes = skf.generateSecret(spec).getEncoded();
            HexFormat hex = HexFormat.of();
            return hex.formatHex(hashedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String forcaBruta(String alg, String hash, String salt) {
        npass = 0;
        char[] password = new char[6];
        Arrays.fill(password, '\0');
        for (int len = 1; len <= 6; len++) {
            if (recorreCombinacions(password, 0, len, alg, hash, salt)) {
                return new String(password, 0, len);
            }
        }
        return null;
    }

    private boolean recorreCombinacions(char[] password, int pos, int len, String alg, String hash, String salt) {
        if (pos == len) {
            npass++;
            String generatedHash = alg.equals("SHA-512")
                ? getSHA512AmbSalt(new String(password, 0, len), salt)
                : getPBKDF2AmbSalt(new String(password, 0, len), salt);
            return hash.equals(generatedHash);
        }
        for (char c : CHARSET.toCharArray()) {
            password[pos] = c;
            if (recorreCombinacions(password, pos + 1, len, alg, hash, salt)) {
                return true;
            }
        }
        return false;
    }

    public String getInterval(long t1, long t2) {
        long millis = t2 - t1;
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        millis %= 1000;
        seconds %= 60;
        minutes %= 60;
        hours %= 24;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                days, hours, minutes, seconds, millis);
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();

        String[] aHashes = {
            h.getSHA512AmbSalt(pw, salt),
            h.getPBKDF2AmbSalt(pw, salt)
        };

        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}
