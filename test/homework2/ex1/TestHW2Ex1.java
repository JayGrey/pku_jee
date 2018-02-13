package homework2.ex1;


import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class TestHW2Ex1 {

    @Test
    public void testMarshalRealEstate() throws JAXBException {


        StringWriter writer = new StringWriter();


        XMLApartmentsDAO dao = new XMLApartmentsDAO();
        RealEstate realEstate = new RealEstate();
        realEstate.add(
                new Apartment("nice flat", 2, "First street", 3, 150_000.00)
        );
        realEstate.add(
                new Apartment("nice flat", 3, "First street", 3, 150_000.00)
        );

        JAXBContext context = JAXBContext.newInstance(RealEstate.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(realEstate, writer);


        System.out.println(writer.toString());
    }


}
