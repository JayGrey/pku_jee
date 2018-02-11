package homework2.ex1;


import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestHW2Ex1 {

    @Test
    public void testMarshalApartment() throws JAXBException {
        Apartment apartment = new Apartment("nice flat", 2, "First street",
                3, 150_000.00);

        StringWriter writer = new StringWriter();

        JAXBContext context = JAXBContext.newInstance(Apartment.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(apartment, writer);

        assertTrue(writer.toString().length() > 0);
        System.out.println(writer.toString());
    }

    @Test
    public void testMarshalApartmentRetail() throws JAXBException {
        ApartmentsRetail retail = new ApartmentsRetail();
        retail.add(new Apartment("nice flat", 2, "First street",
                3, 150_000.00));
        retail.add(new Apartment("very nice flat", 3, "Second street",
                7, 350_000.00));

        StringWriter writer = new StringWriter();

        JAXBContext context = JAXBContext.newInstance(ApartmentsRetail.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(retail, writer);

        assertTrue(writer.toString().length() > 0);
        System.out.println(writer.toString());

    }

    @Test
    public void testUnmarshallApartmentRetail() throws JAXBException {
        ApartmentsRetail retail = new ApartmentsRetail();
        retail.add(new Apartment("nice flat", 2, "First street",
                3, 150_000.00));
        retail.add(new Apartment("very nice flat", 3, "Second street",
                7, 350_000.00));

        StringWriter writer = new StringWriter();

        JAXBContext context = JAXBContext.newInstance(ApartmentsRetail.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(retail, writer);



        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(writer.toString());
        ApartmentsRetail result = (ApartmentsRetail)unmarshaller.unmarshal(reader);
        System.out.println(result);
        assertEquals(2, result.filter(apartment -> true).size());
    }
}
