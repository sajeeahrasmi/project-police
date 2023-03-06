package model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class Vehicle {
    private String vehiNo;
    private String vehiType;
    private String vehiColour;
    private String chassiNo;
    private String engineNo;
    private WritableImage vehiPhoto;
    private String owner;
    private String vehiLicenceNo;
    private Driver driver;

    public Vehicle() {

    }

    public Vehicle(String vehiNo, String vehiType, String vehiColour, String chassiNo, String engineNo, WritableImage vehiPhoto, String owner, String vehiLicenceNo, Driver driver) {
        this.vehiNo = vehiNo;
        this.vehiType = vehiType;
        this.vehiColour = vehiColour;
        this.chassiNo = chassiNo;
        this.engineNo = engineNo;
        this.vehiPhoto = vehiPhoto;
        this.owner = owner;
        this.vehiLicenceNo = vehiLicenceNo;
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getVehiNo() {
        return vehiNo;
    }

    public void setVehiNo(String vehiNo) {
        this.vehiNo = vehiNo;
    }

    public String getVehiType() {
        return vehiType;
    }

    public void setVehiType(String vehiType) {
        this.vehiType = vehiType;
    }

    public String getVehiColour() {
        return vehiColour;
    }

    public void setVehiColour(String vehiColour) {
        this.vehiColour = vehiColour;
    }

    public String getChassiNo() {
        return chassiNo;
    }

    public void setChassiNo(String chassiNo) {
        this.chassiNo = chassiNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public WritableImage getVehiPhoto() {
        return vehiPhoto;
    }

    public void setVehiPhoto(WritableImage vehiPhoto) {
        this.vehiPhoto = vehiPhoto;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getVehiLicenceNo() {
        return vehiLicenceNo;
    }

    public void setVehiLicenceNo(String vehiLicenceNo) {
        this.vehiLicenceNo = vehiLicenceNo;
    }

    public static Vehicle searchVehicle(String vehiNoOrDriverId) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice", "root", "1234");

        PreparedStatement ps = con.prepareStatement("SELECT * FROM vehicle WHERE vehiNo = ?");
        ps.setString(1 , vehiNoOrDriverId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            Vehicle vehicle = new Vehicle();
            vehicle.setVehiNo(rs.getString(1));
            vehicle.setVehiType(rs.getString(2));
            vehicle.setVehiColour(rs.getString(3));
            vehicle.setChassiNo(rs.getString(4));
            vehicle.setEngineNo(rs.getString(5));
            vehicle.setOwner(rs.getString(7));
            vehicle.setVehiLicenceNo(rs.getString(8));

            Blob imageBlob = rs.getBlob(6);
            InputStream input = imageBlob.getBinaryStream();
            BufferedImage bufferedImage = ImageIO.read(input);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);

            vehicle.setVehiPhoto(image);

            ps = con.prepareStatement("SELECT * FROM dri_vehi WHERE vehiNo = ?");
            ps.setString(1 , vehicle.getVehiNo() );
            rs = ps.executeQuery();

            if(rs.next()) {

                Driver driver = new Driver();
                driver.setId(rs.getString(3));

                ps = con.prepareStatement("SELECT * FROM driver WHERE id = ?");
                ps.setString(1, driver.getId());
                rs = ps.executeQuery();
                rs.next();

                imageBlob = rs.getBlob(1);
                input = imageBlob.getBinaryStream();
                bufferedImage = ImageIO.read(input);
                image = SwingFXUtils.toFXImage(bufferedImage, null);

                driver.setImage(image);

                driver.setName(rs.getString(3));
                driver.setAddress(rs.getString(4));
                driver.setBirthDay(rs.getString(5));
                driver.setBirthPlace(rs.getString(6));
                driver.setLicenceNo(rs.getString(7));

                vehicle.setDriver(driver);

            }

            return vehicle;
        }else{
            ps = con.prepareStatement("SELECT * FROM driver WHERE id = ?");
            ps.setString(1 , vehiNoOrDriverId);

            rs = ps.executeQuery();
            if (rs.next()){
                Driver driver = new Driver();

                Blob imageBlob = rs.getBlob(1);
                InputStream input = imageBlob.getBinaryStream();
                BufferedImage bufferedImage = ImageIO.read(input);
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);

                driver.setImage(image);

                driver.setId(rs.getString(2));
                driver.setName(rs.getString(3));
                driver.setAddress(rs.getString(4));
                driver.setBirthDay(rs.getString(5));
                driver.setBirthPlace(rs.getString(6));
                driver.setLicenceNo(rs.getString(7));

                ps = con.prepareStatement("SELECT * FROM dri_vehi WHERE id = ?");
                ps.setString(1 , driver.getId());
                rs = ps.executeQuery();

                rs.next();

                Vehicle vehicle = new Vehicle();
                vehicle.setVehiNo(rs.getString(1));

                ps = con.prepareStatement("SELECT * FROM vehicle WHERE vehiNo = ?");
                ps.setString(1 , vehicle.getVehiNo());
                rs = ps.executeQuery();

                rs.next();

                vehicle.setVehiType(rs.getString(2));
                vehicle.setVehiColour(rs.getString(3));
                vehicle.setChassiNo(rs.getString(4));
                vehicle.setEngineNo(rs.getString(5));
                vehicle.setOwner(rs.getString(7));
                vehicle.setVehiLicenceNo(rs.getString(8));

                vehicle.setDriver(driver);



                return vehicle;
            }else{
                return null;
            }
        }



    }

    public static boolean addVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "password");

        PreparedStatement ps = con.prepareStatement("INSERT INTO vehicle VALUES(?,?,?,?,?,?,?,?) ");

        ps.setString(1 , vehicle.getVehiNo());
        ps.setString(2 , vehicle.getVehiType());
        ps.setString(3 , vehicle.getVehiColour());
        ps.setString(4 , vehicle.getChassiNo());
        ps.setString(5 , vehicle.getEngineNo());
        ps.setString(7 , vehicle.getVehiType());
        ps.setString(8 , vehicle.getVehiType());

        InputStream inputStream=new FileInputStream(String.valueOf(vehicle.getVehiPhoto()));
        ps.setBinaryStream(6 , inputStream , inputStream.available());

        ps.executeUpdate();

        if (vehicle.getDriver() != null){
            ps = con.prepareStatement("INSERT INTO driver VALUES(?,?,?,?,?,?,?) ");

            inputStream=new FileInputStream(String.valueOf(vehicle.getDriver().getImage()));
            ps.setBinaryStream(1 , inputStream , inputStream.available());

            ps.setString(2 , vehicle.getDriver().getId());
            ps.setString(3 , vehicle.getDriver().getName());
            ps.setString(4 , vehicle.getDriver().getAddress());
            ps.setString(5 , vehicle.getDriver().getBirthDay());
            ps.setString(6 , vehicle.getDriver().getBirthPlace());
            ps.setString(7 , vehicle.getDriver().getLicenceNo());

            ps.executeUpdate();

            return true;
        }


        return false;
    }



}
