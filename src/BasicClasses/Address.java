package BasicClasses;

import java.io.Serializable;

public class Address implements Serializable {
    private String street; //Поле не может быть null
    private String zipCode; //Поле не может быть null
    private Location town; //Поле не может быть null

    public Address(String street, String zipCode, Location town){
        this.street = street;
        this.zipCode = zipCode;
        this.town = town;
    }

    public Location getTown() {
        return town;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setTown(Location town) {
        this.town = town;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}