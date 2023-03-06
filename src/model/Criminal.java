package model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageInputStreamImpl;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.sql.*;

public class Criminal {
    private WritableImage photo;
    private String fullName;
    private String nic;
    private String gender;
    private String birthDay;
    private String address;
    private String birthPlace;
    private CaseTypeAll caseTypeAll;


    public Criminal(WritableImage photo, String fullName, String nic, String gender, String birthDay, String address, String birthPlace) {
        this.photo = photo;
        this.fullName = fullName;
        this.nic = nic;
        this.gender = gender;
        this.birthDay = birthDay;
        this.address = address;
        this.birthPlace = birthPlace;
    }

    public Criminal() {

    }

    public CaseTypeAll getCaseTypeAll() {
        return caseTypeAll;
    }

    public void setCaseTypeAll(CaseTypeAll caseTypeAll) {
        this.caseTypeAll = caseTypeAll;
    }

    public WritableImage getPhoto() {
        return photo;
    }

    public void setPhoto(WritableImage photo) {
        this.photo = photo;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public static Criminal searchCriminal(String idOrName) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("SELECT * FROM criminal WHERE nic = ? || fullName = ?");
        ps.setString(1 , idOrName);
        ps.setString(2 , idOrName);
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            Criminal criminal = new Criminal();

            Blob imageBlob = rs.getBlob(1);
            InputStream input = imageBlob.getBinaryStream();
            BufferedImage bufferedImage = ImageIO.read(input);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);

            criminal.setPhoto(image);
            criminal.setFullName(rs.getString(2));
            criminal.setNic(rs.getString(3));
            criminal.setGender(rs.getString(4));
            criminal.setBirthDay(rs.getString(5));
            criminal.setAddress(rs.getString(6));
            criminal.setBirthPlace(rs.getString(7));
            return criminal;
        }

        return null;

    }

    public static boolean addCriminal(Criminal criminal) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("INSERT INTO criminal VALUES (? , ? , ? , ? , ? , ? , ?)");

        BufferedImage bImage = SwingFXUtils.fromFXImage(criminal.getPhoto(), null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ImageIO.write(bImage, "png", outputStream);
        byte[] res  = outputStream.toByteArray();
        InputStream inputStream = new ByteArrayInputStream(res);

        ps.setBinaryStream(1 , inputStream , inputStream.available());

        ps.setString(2 , criminal.getFullName());
        ps.setString(3 , criminal.getNic());
        ps.setString(4 , criminal.getGender());
        ps.setString(5 , criminal.getBirthDay());
        ps.setString(6 , criminal.getAddress());
        ps.setString(7 , criminal.getBirthPlace());

        ps.executeUpdate();

        return true;
    }

    public static boolean updateCriminal(Criminal criminal , String nic) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("UPDATE criminal SET photo = ? , fullName = ? , nic = ? , gender = ? , birthDay = ? , address = ? , birthPlace = ? WHERE nic = ?");

        BufferedImage bImage = SwingFXUtils.fromFXImage(criminal.getPhoto(), null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ImageIO.write(bImage, "png", outputStream);
        byte[] res  = outputStream.toByteArray();
        InputStream inputStream = new ByteArrayInputStream(res);

        ps.setBinaryStream(1 , inputStream , inputStream.available());

        ps.setString(2 , criminal.getFullName());
        ps.setString(3 , criminal.getNic());
        ps.setString(4 , criminal.getGender());
        ps.setString(5 , criminal.getBirthDay());
        ps.setString(6 , criminal.getAddress());
        ps.setString(7 , criminal.getBirthPlace());

        ps.setString(8 , nic);
        ps.executeUpdate();
        return true;
    }

}
