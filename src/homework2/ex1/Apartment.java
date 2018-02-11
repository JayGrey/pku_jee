package homework2.ex1;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class Apartment {
    private String description;
    private int rooms;
    private String address;
    private int floor;
    private double price;

    public Apartment(String description, int rooms, String address, int
            floor, double price) {
        this.description = description;
        this.rooms = rooms;
        this.address = address;
        this.floor = floor;
        this.price = price;
    }

    public Apartment() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return rooms == apartment.rooms &&
                floor == apartment.floor &&
                Double.compare(apartment.price, price) == 0 &&
                Objects.equals(description, apartment.description) &&
                Objects.equals(address, apartment.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(description, rooms, address, floor, price);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "description='" + description + '\'' +
                ", rooms=" + rooms +
                ", address='" + address + '\'' +
                ", floor=" + floor +
                ", price=" + price +
                '}';
    }
}
