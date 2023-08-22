import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CtoD {

    private static final String path = "c:\\Users\\Laci\\AppData\\Local\\qBittorrent\\BT_backup\\";

    @Test
    void name() throws IOException {
        for (File f : new File(path).listFiles()) {
            if (f.getName().endsWith("fastresume")) {
                System.out.println(f.getAbsolutePath());
                byte[] bytes = Files.readAllBytes(Path.of(f.getAbsolutePath()));
                for (int i = 0; i < bytes.length; i++) {
                    if ((bytes[i] == 'D' || bytes[i] == 'd') && bytes[i + 1] == ':') {
                        if (bytes[i + 3] == 'Z') continue;
                        bytes[i] = 'B';
                    }
                }
                Files.write(f.toPath(), bytes);

//                String content = new String();
//                String replaced = content.replace("D:", "B:");
//                FileWriter fileWriter = new FileWriter(f);
//                fileWriter.write(replaced);
//                fileWriter.close();
            }
        }
    }


}
