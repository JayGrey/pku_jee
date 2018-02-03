package homework1.ex2;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
                .allMatch(f -> hasAccessor(clazz, f) && hasMutator(clazz, f));
    }

    private boolean isSerializable(Class<?> clazz) {
        return Arrays.stream(clazz.getInterfaces())
                .anyMatch(c -> c == Serializable.class);
    }

    private boolean hasAccessor(Class<?> clazz, Field field) {
        try {
            String methodName = buildMethodName(field.getName(), true);
            Method method = clazz.getMethod(methodName);
            return method.isAccessible()
                    && method.getReturnType() == field.getType();
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private boolean hasMutator(Class<?> clazz, Field field) {
        try {
            String methodName = buildMethodName(field.getName(), false);
            Method method = clazz.getMethod(methodName, field.getType());
            return method.isAccessible()
                    && method.getReturnType() == Void.class;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private String buildMethodName(String fieldName, boolean isMethodAccessor) {
        StringBuilder result = new StringBuilder();
        if (isMethodAccessor) {
            result.append("get");
        } else {
            result.append("set");
        }

        result.append(Character.toUpperCase(fieldName.charAt(0)));

        if (fieldName.length() > 1) {
            result.append(fieldName.substring(1));
        }

        return result.toString();
    }
}
