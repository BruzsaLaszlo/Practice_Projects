import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class JavaMD5 {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try {
            byte[] md5 = MessageDigest.getInstance("SHA-256").digest(sc.nextLine().getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : md5) {
                sb.append(String.format("%02X", b));
            }
            System.out.println(sb.toString().toLowerCase());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

}
