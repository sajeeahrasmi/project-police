package model;

import controller.CriminalDetailsFormController;

import java.sql.*;

public class CrimeType extends CaseTypeAll{
    private String caseId;
    private String caseType;
    private String description;
    private String location;
    private String date;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CrimeType(String caseId, String caseType, String description, String location, String date, String sOrC) {
        this.caseId = caseId;
        this.caseType = caseType;
        this.description = description;
        this.location = location;
        this.date = date;
    }

    public CrimeType() {


    }

    public static CrimeType searchCrimeType(String caseId) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("SELECT * FROM crime WHERE caseId = ?");
        ps.setString(1 , caseId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            CrimeType crimeType = new CrimeType();

            crimeType.setCaseId(rs.getString(1));
            crimeType.setCaseType(rs.getString(2));
            crimeType.setDescription(rs.getString(3));
            crimeType.setLocation(rs.getString(5));
            crimeType.setDate(rs.getString(4));

            return crimeType;
        }else{
            return null;
        }

    }
    public static boolean addCrimeType(CrimeType crimeType) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("INSERT INTO crime VALUES (? , ? , ? , ? , ? )");
        ps.setString(1 , crimeType.getCaseId());
        ps.setString(2 , crimeType.getCaseType());
        ps.setString(3 , crimeType.getDescription());
        ps.setString(5 , crimeType.getLocation());
        ps.setString(4 , crimeType.getDate());

        ps.executeUpdate();
        return true;
    }
    public static boolean addCrimeNic(String nic , String caseId) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("INSERT INTO criminal_crim VALUES (? , ?)");
        ps.setString(1 , nic);
        ps.setString(2 , caseId);

        ps.executeUpdate();
        return true;
    }

}
