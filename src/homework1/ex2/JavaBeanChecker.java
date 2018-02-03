package homework1.ex2;

public class JavaBeanChecker {
    public boolean check(Class<?> clazz) {
        return meetFirstRequirement(clazz) &&
                meetSecondRequirement(clazz) &&
                meetThirdRequirement(clazz);
    }

    /**
     * Check that this class has default constructor
     * @param clazz
     * @return
     */
    private boolean meetFirstRequirement(Class<?> clazz) {
        return false;
    }


    /**
     * Check that this class has accesible properties through get, set or is methods
     * @param clazz
     * @return
     */
    private boolean meetSecondRequirement(Class<?> clazz) {
        return false;
    }

    /**
     * Check that this class is  serializable.
     * @param clazz
     * @return boolean
     */
    private boolean meetThirdRequirement(Class<?> clazz) {
        return false;
    }
}
