package bruzsal;

import lombok.Getter;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Getter
public class File {

    private final String checksum;
    private final Path filePath;

    public File(Path filePath) {
        this.filePath = filePath;
        try {
            byte[] data = Files.readAllBytes(filePath);
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(data);
            checksum = new BigInteger(1, hash).toString(16);
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof File file)) return false;
        return Objects.equals(checksum, file.checksum);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(checksum);
    }
}
