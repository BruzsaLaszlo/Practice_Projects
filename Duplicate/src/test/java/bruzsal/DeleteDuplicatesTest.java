package bruzsal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.io.CleanupMode.ON_SUCCESS;

class DeleteDuplicatesTest {

    @Test
    void deleteInSecondTest(@TempDir(cleanup = ON_SUCCESS) Path temp1, @TempDir(cleanup = ON_SUCCESS) Path temp2) throws Exception {
        Path pathFirst = Files.createDirectory(temp1.resolve("first"));
        for (int i = 0; i < 5; i++) {
            Files.writeString(pathFirst.resolve(pathFirst + "/file" + i + ".txt"), "contain" + i);
        }

        Path pathSecond = Files.createDirectory(temp2.resolve("second"));
        for (int i = 1; i < 6; i++) {
            Files.writeString(pathSecond.resolve(pathSecond + "/file" + i + ".txt"), "contain" + i);
        }


        DeleteDuplicates deleteDuplicates = new DeleteDuplicates();
        deleteDuplicates.deleteInSecond(pathFirst, pathSecond);

        assertTrue(Files.isDirectory(pathFirst));
        assertTrue(Files.isDirectory(pathSecond));
        for (int i = 0; i < 5; i++) {
            assertTrue(Files.exists(pathFirst.resolve(pathFirst + "/file" + i + ".txt")));
        }
        for (int i = 1; i < 5; i++) {
            assertFalse(Files.exists(pathSecond.resolve(pathSecond + "/file" + i + ".txt")));
            assertTrue(Files.exists(pathSecond.resolve(pathSecond + "/file5.txt")));
        }
    }
}