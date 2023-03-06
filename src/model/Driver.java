package model;

import javafx.scene.image.WritableImage;

public class Driver {
    private WritableImage image;
    private String id;
    private String name;
    private String address;
    private String birthDay;
    private String birthPlace;
    private String licenceNo;

    public Driver() {
    }

    public Driver(WritableImage image, String id, String name, String address, String birthDay, String birthPlace, String licenceNo) {
        this.image = image;
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthDay = birthDay;
        this.birthPlace = birthPlace;
        this.licenceNo = licenceNo;
    }

    public WritableImage getImage() {
        return image;
    }

    public void setImage(WritableImage image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }
}
