package homework1.ex1;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestHW1Ex1 {

    class ClassPPF {
        public int field1;

        ClassPPF(int value) {
            field1 = value;
        }
    }

    @Test
    public void testPublicPrimitiveField() {
        List<String> fields = Main.getSortableFields(new ClassPPF(0));

        assertEquals(1, fields.size());
        assertEquals("field1", fields.get(0));

    }

    @Test
    public void testMakeComparatorForPublicPrimitiveField() {
        Comparator<ClassPPF> comparator = Main.makeComparator(
                ClassPPF.class, "field1");

        ClassPPF value1 = new ClassPPF(2);
        ClassPPF value2 = new ClassPPF(-1);
        assertEquals(1, comparator.compare(value1, value2));

        value1 = new ClassPPF(1);
        value2 = new ClassPPF(2);
        assertEquals(-1, comparator.compare(value1, value2));

        value1 = new ClassPPF(1);
        value2 = new ClassPPF(1);
        assertEquals(0, comparator.compare(value1, value2));

    }
}
