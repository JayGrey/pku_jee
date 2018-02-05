package homework1.ex3;

import homework1.ex1.Sorter;

import java.util.List;

public class AnnotatedSorter {
    public <T> void sort(List<T> students) {
        if (students == null) {
            throw new IllegalArgumentException();
        }
        if (students.size() < 1) {
            return;
        }

        List<String> fields = Sorter.getAnnotatedFields(
                students.get(0).getClass(), SortMePleeeez.class);
        if (fields.size() < 1) {
            throw new RuntimeException("Class has no annotated fields");
        }

        Sorter.sortByField(students, fields.get(0));
    }


}
