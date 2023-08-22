import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
class DigiKepekTest {

    private static final MessageDigest digest;
    private static final String digitalCamera = "d:\\OneDrive\\Pictures\\Digital Camera\\";
    private static final String digiKepek = "d:\\OneDrive\\Pictures\\DigiKépek\\";
    private static final String testDir = "c:\\temp\\sgfds\\";
    private static final String testDir2 = "c:\\temp\\sgfds2\\";

    static {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private final Set<String> digests = new HashSet<>();
    private final Set<String> pictures = new HashSet<>();

    @Test
    void ssh() throws NoSuchAlgorithmException, IOException {

        bejaras1(new File(digitalCamera));

        bejaras2(new File(digiKepek));

        Files.writeString(Path.of("c:\\temp\\pictures.txt"), pictures.stream().collect(Collectors.joining("\n")));

    }

    void bejaras1(File file) throws IOException {
        if (file.isFile()) {
            byte[] d = digest.digest(Files.readAllBytes(file.toPath()));
            digests.add(new String(d));
        } else if (file.isDirectory()) {
            System.out.println(file.getAbsolutePath());
            for (File f : file.listFiles()) {
                bejaras1(f);
            }
        }
    }

    void bejaras2(File file) throws IOException {
        if (file.isFile()) {
            byte[] dd = digest.digest(Files.readAllBytes(file.toPath()));
            if (digests.contains(new String(dd))) {
                pictures.add(file.getParent());
            }
        } else if (file.isDirectory()) {
            System.out.println(file.getAbsolutePath());
            for (File f : file.listFiles()) {
                bejaras2(f);
            }
        }
    }

    void set(int i) {
        set(Integer.parseInt("32"));
    }

//    void set(Integer i) {
//
//    }

    void set(Long i) {

    }

    @Test
    void sort() throws IOException {
        String collect = Files.readAllLines(Path.of("c:\\temp\\pictures.txt")).stream().sorted().collect(Collectors.joining("\n"));
        Files.writeString(Path.of("c:\\temp\\pictures2.txt"), collect);
    }
}
