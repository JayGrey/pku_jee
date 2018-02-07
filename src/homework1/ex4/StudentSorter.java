package homework1.ex4;

import homework1.ex3.Student;
import homework1.ex4.comparators.Comparators;
import homework1.ex4.comparators.StudentComparator;

import java.util.Comparator;
import java.util.List;

public class StudentSorter {
    public void sort(List<Student> students) {
        Comparator<Student> comparator = findComparator();
        students.sort(comparator);
    }

    private Comparator<Student> findComparator() {
        return Comparators.get(StudentComparator.class);
    }
}
