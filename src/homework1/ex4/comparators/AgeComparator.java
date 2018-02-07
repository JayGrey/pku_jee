package homework1.ex4.comparators;

import homework1.ex3.Student;

import java.util.Comparator;

@StudentComparator
public class AgeComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {

        return o1.getAge() - o2.getAge();
    }
}
