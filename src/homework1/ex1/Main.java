package homework1.ex1;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {


    }

    private static <T> void sortByField(List<T> coll, String fieldName) {
        if (coll == null || coll.size() == 0 || fieldName == null) {
            return;
        }

        List<String> sortableFields = getSortableFields(coll.get(0));
        if (!sortableFields.contains(fieldName)) {
            throw new IllegalArgumentException("field not found: " + fieldName);
        }


        coll.sort(makeComparator(coll.get(0).getClass(), fieldName));

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

    static Comparator makeComparator(Class<?> aClass,
                                             String fieldName) {
        return (o1, o2) -> {
            try {

                if (o1.getClass() != aClass || o2.getClass() != aClass) {
                    throw new IllegalArgumentException();
                }

                Field field1 = o1.getClass().getDeclaredField(fieldName);
                Field field2 = o2.getClass().getDeclaredField(fieldName);
                Comparable value = (Comparable) field1.get(o1);
                return value.compareTo(field2.get(o2));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException(e);
            }

        };
    }

}
