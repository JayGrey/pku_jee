package homework1.ex2;

import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestHW1Ex2 {

    @Test
    public void DefaultConstructor() {
        JavaBeanChecker checker = new JavaBeanChecker();

        assertTrue(checker.hasDefaultConstructor(Class0.class));
        assertFalse(checker.hasDefaultConstructor(Class1.class));
    }

    @Test
    public void DefaultConstructorForInnerClass() {
        class Cl {
            public Cl(int i) {

            }
        }

        JavaBeanChecker checker = new JavaBeanChecker();


        assertFalse(checker.hasDefaultConstructor(Cl.class));

        class Cl1 {
            public Cl1() {

            }
        }
        assertTrue(checker.hasDefaultConstructor(Cl1.class));
    }

    @Test
    public void fieldAccessors() {
        JavaBeanChecker checker = new JavaBeanChecker();

        assertTrue(checker.hasFieldsAccessors(Class2.class));
        assertFalse(checker.hasFieldsAccessors(Class3.class));
        assertFalse(checker.hasFieldsAccessors(Class4.class));
    }

    @Test
    public void fieldAccessorsForInnerClass() {
        class Class2i {
            private int field1;
            private String fieldTwo;

            public int getField1() {
                return field1;
            }

            public void setField1(int field1) {
                this.field1 = field1;
            }

            public String getFieldTwo() {
                return fieldTwo;
            }

            public void setFieldTwo(String fieldTwo) {
                this.fieldTwo = fieldTwo;
            }
        }

        class Class3i {
            private int field1;
            private String fieldTwo;
            private double fieldThree;

            public int getField1() {
                return field1;
            }

            public void setField1(int field1) {
                this.field1 = field1;
            }

            public String getFieldTwo() {
                return fieldTwo;
            }

            public void setFieldTwo(String fieldTwo) {
                this.fieldTwo = fieldTwo;
            }

            public double getFieldThree() {
                return fieldThree;
            }
        }

        class Class4i {
            private int field1;
            private String fieldTwo;
            private double fieldThree;

            public int getField1() {
                return field1;
            }

            public void setField1(int field1) {
                this.field1 = field1;
            }

            public String getFieldTwo() {
                return fieldTwo;
            }

            private void setFieldTwo(String fieldTwo) {
                this.fieldTwo = fieldTwo;
            }

        }

        JavaBeanChecker checker = new JavaBeanChecker();

        assertTrue(checker.hasFieldsAccessors(Class2i.class));
        assertFalse(checker.hasFieldsAccessors(Class3i.class));
        assertFalse(checker.hasFieldsAccessors(Class4i.class));
    }

    @Test
    public void isClassSerializable() {
        JavaBeanChecker checker = new JavaBeanChecker();

        assertFalse(checker.hasFieldsAccessors(Class3.class));
        assertTrue(checker.hasFieldsAccessors(Class5.class));
    }

    @Test
    public void isInnerClassSerializable() {

        class Class3i {
            private int field1;
            private String fieldTwo;
            private double fieldThree;

            public int getField1() {
                return field1;
            }

            public void setField1(int field1) {
                this.field1 = field1;
            }

            public String getFieldTwo() {
                return fieldTwo;
            }

            public void setFieldTwo(String fieldTwo) {
                this.fieldTwo = fieldTwo;
            }

            public double getFieldThree() {
                return fieldThree;
            }
        }

        class Class5i implements Serializable {
            private int field1;
            private String fieldTwo;

            public int getField1() {
                return field1;
            }

            public void setField1(int field1) {
                this.field1 = field1;
            }

            public String getFieldTwo() {
                return fieldTwo;
            }

            public void setFieldTwo(String fieldTwo) {
                this.fieldTwo = fieldTwo;
            }

        }

        JavaBeanChecker checker = new JavaBeanChecker();

        assertFalse(checker.hasFieldsAccessors(Class3i.class));
        assertTrue(checker.hasFieldsAccessors(Class5i.class));
    }

}