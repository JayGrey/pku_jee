package homework2.ex1;

public interface ApartmentsDAO {

    RealEstate load(String filename);

    void save(RealEstate realEstate, String filename);

}
