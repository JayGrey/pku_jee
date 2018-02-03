package homework1.ex1;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sorter {

    public static <T> void sortByField(List<T> collection, String fieldName) {
        if (collection == null || collection.size() == 0 || fieldName == null) {
            return;
        }

        List<String> sortableFields = getSortableFields(collection.get(0));
        if (!sortableFields.contains(fieldName)) {
            throw new IllegalArgumentException("field not found: " + fieldName);
        }


        collection.sort(makeComparator(fieldName));

    }


    static List<String> getSortableFields(Object object) {
        List<String> result = new ArrayList<>();

        if (object == null) {
            throw new IllegalArgumentException();
        }
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().isPrimitive()) {
                result.add(field.getName());
                continue;
            }
            Class<?>[] interfaces = field.getType().getInterfaces();
            for (Class<?> anInterface : interfaces) {
                if (anInterface == Comparable.class) {
                    result.add(field.getName());
                    break;
                }
            }
        }

        return result;
    }

    static <T extends Comparable<T>> Comparator makeComparator(String fieldName) {
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
