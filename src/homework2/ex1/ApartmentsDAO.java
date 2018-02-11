package homework2.ex1;

public interface ApartmentsDAO {

    ApartmentsRetail load(String filename);

    void save(ApartmentsRetail collection, String filename);
}
