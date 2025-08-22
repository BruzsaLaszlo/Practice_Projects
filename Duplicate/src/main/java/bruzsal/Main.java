package bruzsal;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        Path pathMain = Paths.get("D:\\OneDrive\\Pictures\\Digital Camera");
        Path pathSub = Paths.get("D:\\OneDrive\\Pictures\\DigiKépek");


        DeleteDuplicates deleteDuplicates = new DeleteDuplicates();
        deleteDuplicates.deleteInSecond(pathMain, pathSub);

    }
}
