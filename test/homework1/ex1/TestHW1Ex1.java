package homework1.ex1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestHW1Ex1 {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    class ClassPubPF {
        public int field1;

        ClassPubPF(int value) {
            field1 = value;
        }
    }

    class ClassPrPF {
        private int field1;

        ClassPrPF(int value) {
            field1 = value;
        }
    }

    class ClassRefF {
        public String field1;

        ClassRefF(String value) {
            field1 = value;
        }
    }

    class ClassRefPrF {
        private String field1;

        ClassRefPrF(String value) {
            field1 = value;
        }
    }

    class Cl {
        private int intField;
        private String stringField;

        Cl(int intFiled, String stringField) {
            this.intField = intFiled;
            this.stringField = stringField;
        }

        public int getIntField() {
            return intField;
        }

        public String getStringField() {
            return stringField;
        }
    }

    @Test
    public void testPublicPrimitiveField() {
        List<String> fields = Sorter.getSortableFields(new ClassPubPF(0));

        assertEquals(1, fields.size());
        assertEquals("field1", fields.get(0));

    }

    @Test
    public void testMakeComparatorForPublicPrimitiveField() {
        Comparator<ClassPubPF> comparator = Sorter.makeComparator(
                ClassPubPF.class, "field1");

        ClassPubPF value1 = new ClassPubPF(2);
        ClassPubPF value2 = new ClassPubPF(-1);
        assertEquals(1, comparator.compare(value1, value2));

        value1 = new ClassPubPF(1);
        value2 = new ClassPubPF(2);
        assertEquals(-1, comparator.compare(value1, value2));

        value1 = new ClassPubPF(1);
        value2 = new ClassPubPF(1);
        assertEquals(0, comparator.compare(value1, value2));

    }

    @Test
    public void testPrivatePrimitiveField() {
        List<String> fields = Sorter.getSortableFields(new ClassPrPF(0));

        assertEquals(1, fields.size());
        assertEquals("field1", fields.get(0));

    }

    @Test
    public void testMakeComparatorForPrivatePrimitiveField() {
        Comparator<ClassPrPF> comparator = Sorter.makeComparator(
                ClassPrPF.class, "field1");

        ClassPrPF value1 = new ClassPrPF(2);
        ClassPrPF value2 = new ClassPrPF(-1);
        assertEquals(1, comparator.compare(value1, value2));

        value1 = new ClassPrPF(-1);
        value2 = new ClassPrPF(1);
        assertEquals(-1, comparator.compare(value1, value2));

        value1 = new ClassPrPF(1);
        value2 = new ClassPrPF(1);
        assertEquals(0, comparator.compare(value1, value2));

    }

    @Test
    public void testPublicReferenceField() {
        List<String> fields = Sorter.getSortableFields(new ClassRefF("hello"));

        assertEquals(1, fields.size());
        assertEquals("field1", fields.get(0));

    }

    @Test
    public void testMakeComparatorForPublicReferenceField() {
        Comparator<ClassRefF> comparator = Sorter.makeComparator(
                ClassRefF.class, "field1");

        ClassRefF value1 = new ClassRefF("b");
        ClassRefF value2 = new ClassRefF("a");
        assertEquals(1, comparator.compare(value1, value2));

        value1 = new ClassRefF("a");
        value2 = new ClassRefF("b");
        assertEquals(-1, comparator.compare(value1, value2));

        value1 = new ClassRefF("a");
        value2 = new ClassRefF("a");
        assertEquals(0, comparator.compare(value1, value2));
    }

    @Test
    public void testPrivateReferenceField() {
        List<String> fields = Sorter.getSortableFields(new ClassRefPrF
                ("hello"));

        assertEquals(1, fields.size());
        assertEquals("field1", fields.get(0));

    }

    @Test
    public void testMakeComparatorForPrivateReferenceField() {
        Comparator<ClassRefPrF> comparator = Sorter.makeComparator(
                ClassRefPrF.class, "field1");

        ClassRefPrF value1 = new ClassRefPrF("b");
        ClassRefPrF value2 = new ClassRefPrF("a");
        assertEquals(1, comparator.compare(value1, value2));

        value1 = new ClassRefPrF("a");
        value2 = new ClassRefPrF("b");
        assertEquals(-1, comparator.compare(value1, value2));

        value1 = new ClassRefPrF("a");
        value2 = new ClassRefPrF("a");
        assertEquals(0, comparator.compare(value1, value2));
    }

    @Test
    public void testSortByField() {
        List<Cl> list = Arrays.asList(new Cl(2, "A"), new Cl(3, "C")
                , new Cl(1, "B"));

        Sorter.sortByField(list, "intField");

        assertEquals(3, list.size());
        assertEquals(1, list.get(0).getIntField());
        assertEquals(2, list.get(1).getIntField());
        assertEquals(3, list.get(2).getIntField());

        Sorter.sortByField(list, "stringField");
        assertEquals("A", list.get(0).getStringField());
        assertEquals("B", list.get(1).getStringField());
        assertEquals("C", list.get(2).getStringField());
    }

    @Test
    public void testSortByFieldException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("field not found: someField");


        List<Cl> list = Arrays.asList(new Cl(2, "A"), new Cl(3, "C"));
        Sorter.sortByField(list, "someField");
    }
}
