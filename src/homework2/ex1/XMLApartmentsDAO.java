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
    public RealEstate load(String filename) {

        try {
            return (RealEstate) JAXBContext
                    .newInstance(RealEstate.class)
                    .createUnmarshaller()
                    .unmarshal(new FileReader(filename));
        } catch (JAXBException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(RealEstate realEstate, String filename) {

        try {

            Marshaller marshaller = JAXBContext
                    .newInstance(RealEstate.class)
                    .createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(realEstate, new FileWriter(filename));
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
