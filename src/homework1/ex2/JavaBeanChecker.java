package homework1.ex2;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

class JavaBeanChecker {
    boolean check(Class<?> clazz) {
        return hasDefaultConstructor(clazz) &&
                hasFieldsAccessors(clazz) &&
                isSerializable(clazz);
    }

    boolean hasDefaultConstructor(Class<?> clazz) {
        try {
            return clazz.getConstructor() != null;
        } catch (NoSuchMethodException e) {
            try {
                Class<?> enclosingClass = clazz.getEnclosingClass();
                return enclosingClass != null &&
                        clazz.getConstructor(enclosingClass) != null;
            } catch (NoSuchMethodException err) {
                return false;
            }
        }
    }

    boolean hasFieldsAccessors(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> !f.isSynthetic())
                .allMatch(f -> hasGetter(clazz, f) && hasSetter(clazz, f));
    }

    boolean isSerializable(Class<?> clazz) {
        return Arrays.stream(clazz.getInterfaces())
                .anyMatch(c -> c == Serializable.class);
    }

    private boolean hasGetter(Class<?> clazz, Field field) {
        try {
            String methodName = buildMethodName(field.getName(), true);
            Method method = clazz.getMethod(methodName);

            return Modifier.isPublic(method.getModifiers())
                    && (method.getReturnType() == field.getType());
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private boolean hasSetter(Class<?> clazz, Field field) {
        try {
            String methodName = buildMethodName(field.getName(), false);
            Method method = clazz.getMethod(methodName, field.getType());
            return Modifier.isPublic(method.getModifiers())
                    && method.getReturnType() == void.class;
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
