package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Student implements Comparable<Student> {

    private final int id;
    private String name;
    private double cgpa;

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    public static Student parseStudent(String text) {
        String[] s = text.split(" ");
        return new Student(Integer.parseInt(s[3]), s[1], Double.parseDouble(s[2]));
    }

    @Override
    public int compareTo(Student s2) {
        if (cgpa != s2.getCgpa()) {
            return Double.compare(cgpa, s2.getCgpa());
        } else if (!name.equals(s2.getName())) {
            return s2.getName().compareTo(name);
        } else {
            return Integer.compare(id, s2.getId());
        }
    }

}

class Priorities {

    List<Student> getStudents(List<String> events) {
        PriorityQueue<Student> students = new PriorityQueue<>();
        for (String event : events) {
            if (event.startsWith("ENTER")) {
                students.offer(Student.parseStudent(event));
            } else if (event.startsWith("SERVED") && !students.isEmpty()) {
                students.poll();
            }
        }
        List<Student> result = new ArrayList<>();
        while (!students.isEmpty()) {
            result.add(students.poll());
        }
        return result;
    }
}

public class JavaPriorityQueue {

    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();

    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();

        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }

        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st : students) {
                System.out.println(st.getName());
            }
        }
    }

}
