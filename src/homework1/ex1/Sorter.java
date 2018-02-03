package homework1.ex1;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

interface Sorter {
    static <T> void sortByField(List<T> collection, String fieldName) {
        if (collection == null || collection.size() == 0 || fieldName == null) {
            return;
        }

        List<String> sortableFields = getSortableFieldsByObject(collection.get(0));
        if (!sortableFields.contains(fieldName)) {
            throw new IllegalArgumentException("field not found: " + fieldName);
        }

        collection.sort(getComparator(fieldName));

    }


    static List<String> getSortableFieldsByObject(Object object) {
        return getSortableFieldsByClass(object.getClass());
    }

    static List<String> getSortableFieldsByClass(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> Arrays.stream(f.getType().getInterfaces())
                        .anyMatch(i -> i == Comparable.class) ||
                        f.getType().isPrimitive())
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    static <T extends Comparable<T>> Comparator getComparator(String fieldName) {
        return Comparator.comparing((o -> {
            try {
                Field field = o.getClass().getDeclaredField(fieldName);
                boolean accessible = field.isAccessible();
                field.setAccessible(true);
                T value = (T) field.get(o);
                field.setAccessible(accessible);
                return value;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }));
    }
}
