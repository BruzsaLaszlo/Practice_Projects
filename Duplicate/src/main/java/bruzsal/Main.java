package bruzsal;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Directory firstDir;
        Directory secondDir;

        if (args.length == 2) {
            firstDir = new Directory(args[0]);
            secondDir = new Directory(args[1]);
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter the path to the directory you would like to scan:");
            firstDir = new Directory(sc.nextLine());
            System.out.println("Please enter the path to the directory you would like to delete files:");
            secondDir = new Directory(sc.nextLine());
        }

        DeleteDuplicates deleteDuplicates = new DeleteDuplicates();
        deleteDuplicates.deleteInSecond(firstDir.getPath(), secondDir.getPath());

    }


}
