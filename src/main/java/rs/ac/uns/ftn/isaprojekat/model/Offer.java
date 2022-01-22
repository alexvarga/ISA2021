package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class Offer extends BaseEntity {

    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    @Column(columnDefinition ="TEXT")
    private String info;
    @NotNull
    private Float avgRating;
    private Integer noOfRatings;
    @NotNull
    private Float price;
    private String misc;
    @NotNull
    private Integer noOfPersons;
    private String imageLink;


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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public Integer getNoOfPersons() {
        return noOfPersons;
    }

    public void setNoOfPersons(Integer noOfPersons) {
        this.noOfPersons = noOfPersons;
    }

    public Integer getNoOfRatings() {
        return noOfRatings;
    }

    public void setNoOfRatings(Integer noOfRatings) {
        this.noOfRatings = noOfRatings;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
