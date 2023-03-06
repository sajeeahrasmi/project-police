package model;

import java.sql.*;

public class MoType extends CaseTypeAll{
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

    public MoType(String caseId, String caseType, String description, String location, String date, String sOrC) {
        this.caseId = caseId;
        this.caseType = caseType;
        this.description = description;
        this.location = location;
        this.date = date;
    }

    public MoType() {



    }
    public static MoType searchMoType(String caseId) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("SELECT * FROM mo WHERE caseId = ?");
        ps.setString(1 , caseId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            MoType moType = new MoType();

            moType.setCaseId(rs.getString(1));
            moType.setCaseType(rs.getString(2));
            moType.setDescription(rs.getString(3));
            moType.setLocation(rs.getString(5));
            moType.setDate(rs.getString(4));

            return moType;
        }else{
            return null;
        }

    }

    public static boolean addMoType(MoType moType) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("INSERT INTO mo VALUES (? , ? , ? , ? , ? )");
        ps.setString(1 , moType.getCaseId());
        ps.setString(2 , moType.getCaseType());
        ps.setString(3 , moType.getDescription());
        ps.setString(5 , moType.getLocation());
        ps.setString(4 , moType.getDate());

        ps.executeUpdate();
        return true;
    }
    public static boolean addMoNic(String nic , String caseId) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("INSERT INTO criminal_mo VALUES (? , ?)");
        ps.setString(1 , nic);
        ps.setString(2 , caseId);

        ps.executeUpdate();
        return true;
    }

}
