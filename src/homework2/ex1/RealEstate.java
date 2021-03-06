package homework2.ex1;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@XmlRootElement(name = "apartments")
public class RealEstate {
    @XmlElement(name = "apartment")
    private List<Apartment> apartments = new ArrayList<>();


    public void add(Apartment apartment) {
        checkNull(apartment);

        if (!apartments.contains(apartment)) {
            apartments.add(apartment);
        }
    }

    public List<Apartment> get(Predicate<Apartment> func) {
        checkNull(func);

        return apartments.stream()
                .filter(func)
                .collect(Collectors.toList());
    }

    public List<Apartment> getApartmentsByPriceRange(double from, double to) {
        checkPositive(from, to);
        return get(a -> a.getPrice() >= from && a.getPrice() <= to);
    }

    public List<Apartment> getApartmentsByRoomsRange(int from, int to) {
        checkPositive(from, to);
        return get(a -> a.getRooms() >= from && a.getRooms() <= to);
    }


    public void update(Apartment oldApartment, Apartment newApartment) {

        checkNull(oldApartment, newApartment);

        int index = apartments.indexOf(oldApartment);

        if (index != -1) {
            Apartment apartment = apartments.get(index);
            apartment.setAddress(newApartment.getAddress());
            apartment.setDescription(newApartment.getDescription());
            apartment.setFloor(newApartment.getFloor());
            apartment.setPrice(newApartment.getPrice());
            apartment.setRooms(newApartment.getRooms());
        }

    }

    public void delete(Apartment apartment) {
        checkNull(apartment);
        apartments.remove(apartment);
    }

    public int size() {
        return apartments.size();
    }


    private void checkNull(Object object, Object... objects) {
        
        if (Stream.concat(Stream.of(object), Stream.of(objects))
                .anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("argument is null");
        }

    }

    private void checkPositive(Number number, Number... numbers) {

        Stream.concat(Stream.of(number), Stream.of(numbers))
                .mapToLong(Number::longValue)
                .filter(n -> n < 0)
                .findAny()
                .ifPresent(n -> {
                    throw new IllegalArgumentException("argument is negative");
                });
    }
}
