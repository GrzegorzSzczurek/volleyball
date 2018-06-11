package sample.model;

public class Hall {
    private int id;
    private String hallName;
    private int capacity;
    private String city;
    private int postCode;
    private String street;

    public Hall(int id, String hallName, int capacity, String city, int postCode, String street) {
        this.id = id;
        this.hallName = hallName;
        this.capacity = capacity;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
    }

    public Hall(String hallName, int capacity, String city, int postCode, String street) {
        this.hallName = hallName;
        this.capacity = capacity;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


}
