package contacts;

import java.io.Serializable;

public class Organization extends Contact implements Serializable {
    private String name;
    private String address;

    public Organization(String phoneNumber, String name, String address) {
        super(phoneNumber);
        this.name = name;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getSuperstring() {
        return name + address + super.getSuperstring();
    }

    @Override
    public String toString() {
        return String.format("Organization name: %s%nAddress: %s%n%s", name, address, super.toString());
    }

    @Override
    public String getInfo() {
        return name;
    }
}
