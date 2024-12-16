package bruzsal.file;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class FileOwnerAttributeChanger {

    public static void main(String[] args) {
        FileOwnerAttributeChanger fileOwnerAttributeChanger = new FileOwnerAttributeChanger();

//        Path dir = Path.of("D:\\OneDrive\\Pictures");
//        long l = fileOperations.countOwners("LACIPC\\Laci", dir);
//        System.out.println("Number of owners: " + l);

        Path dir = Path.of("d:\\OneDrive");
        fileOwnerAttributeChanger.setOwner(dir, "LACIPC\\Laci");

//        Path dir = Path.of("d:\\OneDrive\\Pictures\\");
        fileOwnerAttributeChanger.collectAllFiles(dir);
    }

    private String getOwner(Path path) {
        try {
            return Files.getFileAttributeView(path, FileOwnerAttributeView.class).getOwner().getName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setOwner(Path dir, String owner) {
        UserPrincipal userPrincipal;
        try {
            userPrincipal = FileSystems.getDefault()
                    .getUserPrincipalLookupService()
                    .lookupPrincipalByName(owner);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getAllFilesFromDirectory(dir)
                .forEach(path -> {
                    try {
                        FileOwnerAttributeView av = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
                        if (!av.getOwner().getName().equals(owner)) {
                            av.setOwner(userPrincipal);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private Stream<Path> getAllFilesFromDirectory(Path dir) {
        try {
            return Files.walk(dir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private long countOwners(String ownerName, Path dir) {
        return getAllFilesFromDirectory(dir)
                .filter(path -> getOwner(path).equals(ownerName))
                .count();
    }

    private void collectAllFiles(Path dir) {
        Set<String> owners = new HashSet<>();
        String collect = getAllFilesFromDirectory(dir)
                .map(path -> {
                    String owner = getOwner(path);
                    owners.add(owner);
                    return owner + "\t\t" + path;
                })
                .collect(joining("\n"));
        try {
            Files.write(Path.of("files.dat"), collect.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("collected : " + owners);
    }
}