package homework2.ex1;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class XMLApartmentsDAO implements ApartmentsDAO {
    @Override
    public ApartmentsRetail load(String filename) {
        try {
            return (ApartmentsRetail) JAXBContext
                    .newInstance(ApartmentsRetail.class)
                    .createUnmarshaller()
                    .unmarshal(new FileReader(filename));
        } catch (JAXBException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(ApartmentsRetail retail, String filename) {
        try {

            Marshaller marshaller = JAXBContext
                    .newInstance(ApartmentsRetail.class)
                    .createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(retail, new FileWriter(filename));
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
