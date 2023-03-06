package model;

import java.sql.*;

public class DrugsType extends CaseTypeAll{
    private String caseId;
    private String caseType;
    private String description;
    private String location;
    private String date;
    private String drugsType;
    private String drugsAmount;
    private String getFromWhom;
    private String toWhom;
    private String sOrC;

    public DrugsType(String caseId, String caseType, String description, String location, String date, String drugsType, String drugsAmount, String getFromWhom, String toWhom, String sOrC) {
        this.caseId = caseId;
        this.caseType = caseType;
        this.description = description;
        this.location = location;
        this.date = date;
        this.drugsType = drugsType;
        this.drugsAmount = drugsAmount;
        this.getFromWhom = getFromWhom;
        this.toWhom = toWhom;
        this.sOrC = sOrC;
    }

    public DrugsType() {
    }

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

    public String getDrugsType() {
        return drugsType;
    }

    public void setDrugsType(String drugsType) {
        this.drugsType = drugsType;
    }

    public String getDrugsAmount() {
        return drugsAmount;
    }

    public void setDrugsAmount(String drugsAmount) {
        this.drugsAmount = drugsAmount;
    }

    public String getGetFromWhom() {
        return getFromWhom;
    }

    public void setGetFromWhom(String getFromWhom) {
        this.getFromWhom = getFromWhom;
    }

    public String getToWhom() {
        return toWhom;
    }

    public void setToWhom(String toWhom) {
        this.toWhom = toWhom;
    }

    public String getsOrC() {
        return sOrC;
    }

    public void setsOrC(String sOrC) {
        this.sOrC = sOrC;
    }

    public static DrugsType searchDrugsType(String caseId) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("SELECT  * FROM drugs WHERE caseId = ?");

        ps.setString(1 , caseId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            DrugsType drugsType = new DrugsType();
            drugsType.setCaseId(rs.getString(1));
            drugsType.setCaseType(rs.getString(2));
            drugsType.setDescription(rs.getString(3));
            drugsType.setLocation(rs.getString(5));
            drugsType.setDate(rs.getString(4));
            drugsType.setDrugsType(rs.getString(6));
            drugsType.setDrugsAmount(rs.getString(7));
            drugsType.setGetFromWhom(rs.getString(8));
            drugsType.setToWhom(rs.getString(9));
            drugsType.setsOrC(rs.getString(10));

            return drugsType;
        }else {
            return null;
        }


    }
    public static boolean addDrugsType(DrugsType drugsType) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("INSERT INTO mo VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? , ?)");
        ps.setString(1 , drugsType.getCaseId());
        ps.setString(2 , drugsType.getCaseType());
        ps.setString(3 , drugsType.getDescription());
        ps.setString(5 , drugsType.getLocation());
        ps.setString(4 , drugsType.getDate());
        ps.setString(6 , drugsType.getDrugsType());
        ps.setString(7 , drugsType.getDrugsAmount());
        ps.setString(8 , drugsType.getGetFromWhom());
        ps.setString(9 , drugsType.getToWhom());
        ps.setString(10 , drugsType.getsOrC());

        ps.executeUpdate();

        return true;

    }
    public static boolean addDrugsNic(String nic , String caseId) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("INSERT INTO criminal_drugs VALUES (? , ?)");
        ps.setString(1 , nic);
        ps.setString(2 , caseId);

        ps.executeUpdate();
        return true;
    }

}
