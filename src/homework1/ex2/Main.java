package homework1.ex2;


public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("usage: Main <class_name>");
            System.exit(1);
        }

        try {
            Class<?> clazz = Class.forName(args[0]);
            if (new JavaBeanChecker().check(clazz)) {
                System.out.println("class meets JavaBean specification");
            } else {
                System.out.println("class doesn't meet JavaBean specification");
            }

        } catch (ClassNotFoundException e) {
            System.err.println("class " + args[0] + " not found");
        }
    }
}
