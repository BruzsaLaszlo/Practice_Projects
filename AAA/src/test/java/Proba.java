import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class Proba {


    static class Mammal {

        public Mammal() {
            System.out.print("Mammal");
        }

    }

    public class Vehicle {

        public Vehicle() {
            System.out.print("Vehicle");
        }
    }

    public class Car extends Vehicle {

        public Car() {
            System.out.print("Car");
        }
    }

    public class Truck extends Car {

        public Truck() {
            System.out.print("Truck");
        }
    }

//    public List<Course> createCourses(String... names) {
//        List<Course> courses = new ArrayList<>();
//        for (String name: names) { courses.add(new Course(name)); }
//        for (int i = 0; i < names.length; i++) { courses.add(new Course(names[i])); }
//        for (int i = 0; i < names.length; i++) { courses.add(new Course(names[i])); }
////   Bejárás
//    }


    @Test
    void gdfgfd() {
        Truck truck = new Truck();
    }

    public class Platypus extends Mammal {

        public Platypus() {
            System.out.print("Platypus");
        }

        public static void main(String[] args) {
            new Mammal();
        }

    }

    @Test
    void name2() {
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 3) {
                    break;
                }
                counter++;
            }
        }
        System.out.println(counter);
    }

    @SuppressWarnings("RedundantOperationOnEmptyContainer")
    List<String> dfsfs() {
        return new HashMap<String, Integer>().entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry<String, Integer>::getKey).reversed())
                .map(Map.Entry::getKey)
                .toList();
    }

    @Test
    @Description("Get a list of resources from classpath directory")
    void name() {
        System.out.println(getClass().getResource("apingdemo.properties"));
        Pattern pattern;
        pattern = Pattern.compile(".*");
//            pattern = Pattern.compile(args[0]);
        final Collection<String> list = Proba.getResources(pattern);
        for (final String name : list) {
            System.out.println(name);
        }
    }

    /**
     * for all elements of java.class.path get a Collection of resources Pattern
     * pattern = Pattern.compile(".*"); gets all resources
     *
     * @param pattern the pattern to match
     * @return the resources in the order they are found
     */
    public static Collection<String> getResources(
            final Pattern pattern) {
        final ArrayList<String> retval = new ArrayList<String>();
        final String classPath = System.getProperty("java.class.path", ".");
        final String[] classPathElements = classPath.split(System.getProperty("path.separator"));
        for (final String element : classPathElements) {
            retval.addAll(getResources(element, pattern));
        }
        return retval;
    }


    private static Collection<String> getResources(
            final String element,
            final Pattern pattern) {
        final ArrayList<String> retval = new ArrayList<String>();
        final File file = new File(element);
        if (file.isDirectory()) {
            retval.addAll(getResourcesFromDirectory(file, pattern));
        } else {
            retval.addAll(getResourcesFromJarFile(file, pattern));
        }
        return retval;
    }

    private static Collection<String> getResourcesFromJarFile(
            final File file,
            final Pattern pattern) {
        final ArrayList<String> retval = new ArrayList<String>();
        ZipFile zf;
        try {
            zf = new ZipFile(file);
        } catch (final ZipException e) {
            throw new Error(e);
        } catch (final IOException e) {
            throw new Error(e);
        }
        final Enumeration e = zf.entries();
        while (e.hasMoreElements()) {
            final ZipEntry ze = (ZipEntry) e.nextElement();
            final String fileName = ze.getName();
            final boolean accept = pattern.matcher(fileName).matches();
            if (accept) {
                retval.add(fileName);
            }
        }
        try {
            zf.close();
        } catch (final IOException e1) {
            throw new Error(e1);
        }
        return retval;
    }

    private static Collection<String> getResourcesFromDirectory(
            final File directory,
            final Pattern pattern) {
        final ArrayList<String> retval = new ArrayList<String>();
        final File[] fileList = directory.listFiles();
        for (final File file : fileList) {
            if (file.isDirectory()) {
                retval.addAll(getResourcesFromDirectory(file, pattern));
            } else {
                try {
                    final String fileName = file.getCanonicalPath();
                    final boolean accept = pattern.matcher(fileName).matches();
                    if (accept) {
                        retval.add(fileName);
                    }
                } catch (final IOException e) {
                    throw new Error(e);
                }
            }
        }
        return retval;
    }
}
