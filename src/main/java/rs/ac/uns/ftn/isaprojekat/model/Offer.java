package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Offer extends BaseEntity {

    private String name;
    private String address;
    private String info;
    private Float avgRating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Float avgRating) {
        this.avgRating = avgRating;
    }
}
