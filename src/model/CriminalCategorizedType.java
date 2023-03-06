package model;

import javafx.scene.image.WritableImage;

public class CriminalCategorizedType {
    private WritableImage image;
    private String fullName;
    private String nic;
    private String address;
    private String birthDay;
    private int count;
    private int minorCasesCount;
    private int majorCasesCount;

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public WritableImage getImage() {
        return image;
    }

    public void setImage(WritableImage image) {
        this.image = image;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMinorCasesCount() {
        return minorCasesCount;
    }

    public void setMinorCasesCount(int minorCasesCount) {
        this.minorCasesCount = minorCasesCount;
    }

    public int getMajorCasesCount() {
        return majorCasesCount;
    }

    public void setMajorCasesCount(int majorCasesCount) {
        this.majorCasesCount = majorCasesCount;
    }

    public CriminalCategorizedType() {
    }


}
