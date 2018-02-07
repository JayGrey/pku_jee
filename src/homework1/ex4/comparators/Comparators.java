package homework1.ex4.comparators;

import homework1.ex3.Student;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Comparators {
    private static List<Comparator<Student>> comparatorList = new ArrayList<>();

    static {
        comparatorList.add(new AgeComparator());
        comparatorList.add(new NameComparator());
    }

    public static Comparator<Student> get(Class<? extends Annotation> clazz) {
        return comparatorList.stream()
                .filter(c -> c.getClass().getAnnotation(clazz) != null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("comparator not found"));
    }
}
