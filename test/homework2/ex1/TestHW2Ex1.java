package homework2.ex1;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestHW2Ex1 {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testMarshalRealEstate() throws IOException {

        RealEstate realEstate = new RealEstate();
        realEstate.add(
                new Apartment("nice flat", 2, "First street", 3, 150_000.00)
        );
        realEstate.add(
                new Apartment("nice flat", 3, "First street", 3, 150_000.00)
        );
        File file = tempFolder.newFile();
        ApartmentsDAO dao = new XMLApartmentsDAO();
        dao.save(realEstate, file.getAbsolutePath());

        assertTrue(file.length() > 0);
    }

    @Test
    public void testUnMarshalRealEstate() throws IOException {
        RealEstate realEstate = new RealEstate();
        realEstate.add(
                new Apartment("nice flat", 2, "First street", 3, 150_000.00)
        );
        realEstate.add(
                new Apartment("very nice flat", 3, "Second street", 3,
                        150_000.00)
        );
        File file = tempFolder.newFile();
        ApartmentsDAO dao = new XMLApartmentsDAO();
        dao.save(realEstate, file.getAbsolutePath());

        assertTrue(file.length() > 0);

        //
        realEstate = dao.load(file.getAbsolutePath());
        assertEquals(2, realEstate.size());
    }

    @Test
    public void testRealEstateAdd() {
        RealEstate realEstate = new RealEstate();
        assertEquals(0, realEstate.size());

        realEstate.add(
                new Apartment("nice flat", 2, "First street", 3, 150_000.00)
        );
        assertEquals(1, realEstate.size());

        realEstate.add(
                new Apartment("nice flat", 2, "First street", 3, 150_000.00)
        );
        assertEquals(1, realEstate.size());
    }

    @Test
    public void testRealEstateAddNull() {
        RealEstate realEstate = new RealEstate();
        assertEquals(0, realEstate.size());

        thrown.expect(IllegalArgumentException.class);
        realEstate.add(null);
    }

    @Test
    public void testRealEstateGet() {
        RealEstate realEstate = new RealEstate();
        assertEquals(0, realEstate.size());

        realEstate.add(
                new Apartment("nice flat", 2, "First street", 3, 150_000.00)
        );

        assertEquals(1, realEstate.size());

        assertEquals(1, realEstate.get(a -> a.getRooms() >= 2).size());


    }

    @Test
    public void testGetApartmentsByPriceRange() {
        RealEstate realEstate = new RealEstate();
        assertEquals(0, realEstate.size());

        realEstate.add(
                new Apartment("nice flat", 2, "First street", 3, 150_000.00)
        );

        realEstate.add(
                new Apartment("nice flat", 2, "First street", 3, 250_000.00)
        );

        realEstate.add(
                new Apartment("nice flat", 2, "First street", 3, 350_000.00)
        );

        assertEquals(2, realEstate.getApartmentsByPriceRange(100_000,
                300_000).size());

        assertEquals(0, realEstate.getApartmentsByPriceRange(100, 300).size());

    }

    @Test
    public void getApartmentsByRoomsRange() {
        RealEstate realEstate = new RealEstate();
        assertEquals(0, realEstate.size());

        realEstate.add(
                new Apartment("nice flat", 1, "First street", 3, 150_000.00)
        );

        realEstate.add(
                new Apartment("nice flat", 2, "First street", 3, 250_000.00)
        );

        realEstate.add(
                new Apartment("nice flat", 3, "First street", 3, 350_000.00)
        );

        assertEquals(2, realEstate.getApartmentsByRoomsRange(2, 3).size());
        assertEquals(0, realEstate.getApartmentsByRoomsRange(5, 10).size());
    }

    @Test
    public void updateApartment() {
        RealEstate realEstate = new RealEstate();
        assertEquals(0, realEstate.size());

        Apartment apartment
                = new Apartment("nice flat", 1, "First street", 3, 150_000.00);
        realEstate.add(apartment);

        assertEquals(1, realEstate.getApartmentsByPriceRange(150_000.00,
                150_000.00).size());

        Apartment apartmentNew
                = new Apartment("nice flat", 1, "First street", 3, 350_000.00);

        realEstate.update(apartment, apartmentNew);
        assertEquals(1, realEstate.size());
        assertEquals(0, realEstate.getApartmentsByPriceRange(150_000.00,
                150_000.00).size());
        assertEquals(1, realEstate.getApartmentsByPriceRange(350_000.00,
                350_000.00).size());

    }

    @Test
    public void deleteApartment() {
        RealEstate realEstate = new RealEstate();

        Apartment apartment
                = new Apartment("nice flat", 1, "First street", 3, 150_000.00);
        realEstate.add(apartment);
        assertEquals(1, realEstate.size());

        realEstate.delete(apartment);
        assertEquals(0, realEstate.size());

        realEstate.delete(apartment);
        assertEquals(0, realEstate.size());
    }
}
