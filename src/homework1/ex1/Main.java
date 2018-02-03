package homework1.ex1;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("usage: Main <class_name>");
            System.exit(1);
        }

        try {
            Class<?> clazz = Class.forName(args[0]);
            System.out.println("Sortable fields:");
            Sorter.getSortableFieldsByClass(clazz).forEach(System.out::println);
        } catch (ClassNotFoundException e) {
            System.err.println("class " + args[0] + " not found");
        }
    }
}
