package pl.losK.model;

import java.util.UUID;

/**
 * Created by m.losK on 2017-03-15.
 */
public class Company {
    private UUID id;
    private Address address;
    private String nip;
    private String regon;
    private String name;

    public Company() {
    }

    public Company(UUID id, Address address, String nip, String regon, String name) {
        this.id = id;
        this.address = address;
        this.nip = nip;
        this.regon = regon;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", address=" + address +
                ", nip='" + nip + '\'' +
                ", regon='" + regon + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
