package homework1.ex4;

import homework1.ex3.Sex;
import homework1.ex3.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestHW1Ex4 {

    @Test
    public void test() {
        StudentSorter sorter = new StudentSorter();
        List<Student> students = Arrays.asList(
                new Student("Ivan", 23, Sex.MALE),
                new Student("Mary", 20, Sex.FEMALE),
                new Student("Alan", 27, Sex.MALE)
        );

        sorter.sort(students);

        assertEquals(3, students.size());
        assertEquals(students.get(0).getName(), "Mary");
    }
}
