package homework1.ex2;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;

public class JavaBeanChecker {
    public boolean check(Class<?> clazz) {
        return hasDefaultConstructor(clazz) &&
                hasFieldsAccessors(clazz) &&
                isSerializable(clazz);
    }

    private boolean hasDefaultConstructor(Class<?> clazz) {
        try {
            return clazz.getConstructor() != null;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private boolean hasFieldsAccessors(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .allMatch(f -> hasAccessor(f) && hasMutator(f));
    }

    private boolean hasAccessor(Field field) {
        //todo: check method name like get<FieldName>
        //todo: check that method has no args
        //todo: check that return value and field type is equal
        return false;
    }

    private boolean hasMutator(Field field) {
        //todo: check method name like set<FieldName>
        //todo: check that method has one argument with type equal to field type
        //todo: check that return value is void
        return false;
    }

    private boolean isSerializable(Class<?> clazz) {
        return Arrays.stream(clazz.getInterfaces())
                .anyMatch(c -> c == Serializable.class);
    }
}
