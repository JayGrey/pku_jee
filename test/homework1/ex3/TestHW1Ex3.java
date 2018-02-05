package homework1.ex3;

import homework1.ex1.Sorter;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestHW1Ex3 {
    @Test
    public void testAnnotation() {
        class Cl {
            private int i;

            @SortMePleeeez
            private String stringField;
        }

        List<String> fields = Sorter.getAnnotatedFields(Cl.class,
                SortMePleeeez.class);

        assertEquals(1, fields.size());
        assertEquals("stringField", fields.get(0));

    }

    @Test
    public void testNoAnnotation() {
        class Cl {
            private int i;
            private String stringField;
        }

        List<String> fields = Sorter.getAnnotatedFields(Cl.class,
                SortMePleeeez.class);

        assertEquals(0, fields.size());

    }

    @Test
    public void testMultiplyAnnotations() {
        class Cl {
            @SortMePleeeez
            private int i;

            @SortMePleeeez
            private String stringField;
        }

        List<String> fields = Sorter.getAnnotatedFields(Cl.class,
                SortMePleeeez.class);

        assertEquals(2, fields.size());

    }

    @Test
    public void testSortingPrimitiveField() {
        class Cl {
            @SortMePleeeez
            private int field1;
            private String field2;

            public Cl(int field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
        }

        List<Cl> list = Arrays.asList(new Cl(3, "a"), new Cl(2, "b"));

        AnnotatedSorter sorter = new AnnotatedSorter();
        sorter.sort(list);

        assertEquals(2, list.size());
        assertEquals(2, list.get(0).field1);
        assertEquals(3, list.get(1).field1);
    }

    @Test
    public void testSortingObjectField() {
        class Cl {
            private int field1;
            @SortMePleeeez
            private String field2;

            public Cl(int field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
        }

        List<Cl> list = Arrays.asList(new Cl(3, "b"), new Cl(2, "a"));

        AnnotatedSorter sorter = new AnnotatedSorter();
        sorter.sort(list);

        assertEquals(2, list.size());
        assertEquals("a", list.get(0).field2);
        assertEquals("b", list.get(1).field2);
    }

    @Test
    public void testSortingEnumField() {
        class Cl {
            private int field1;
            private String field2;
            @SortMePleeeez
            private Sex field3;

            public Cl(int field1, String field2, Sex field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
        }

        List<Cl> list = Arrays.asList(
                new Cl(3, "b", Sex.MALE),
                new Cl(2, "a", Sex.FEMALE)
        );

        AnnotatedSorter sorter = new AnnotatedSorter();
        sorter.sort(list);

        assertEquals(2, list.size());
        assertEquals(Sex.FEMALE, list.get(0).field3);
        assertEquals(Sex.MALE, list.get(1).field3);
    }
}
