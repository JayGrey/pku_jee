package homework2.ex1;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@XmlRootElement(name = "apartments")
public class ApartmentsRetail {
    @XmlElement(name = "apartment")
    private List<Apartment> apartments = new ArrayList<>();

    public void add(List<Apartment> collection) {
        apartments.addAll(collection);
    }

    public void add(Apartment apartment) {
        apartments.add(apartment);
    }

    public void change(Apartment oldValue, Apartment newValue) {
        apartments.set(apartments.indexOf(oldValue), newValue);
    }

    public void delete(Apartment apartment) {
        apartments.remove(apartment);
    }

    List<Apartment> filter(Predicate<Apartment> func) {
        return apartments.stream()
                .filter(func)
                .collect(Collectors.toList());
    }

    public List<Apartment> getApartmentsByPriceRange(double from, double to) {
        return filter(a -> a.getPrice() >= from && a.getPrice() <= to);
    }

    public List<Apartment> getApartmentsByRoomsRange(int from, int to) {
        return filter(a -> a.getRooms() >= from && a.getRooms() <= to);
    }

    @Override
    public String toString() {
        return "ApartmentsRetail{" + apartments + '}';
    }
}
