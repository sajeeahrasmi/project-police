package model;

import java.sql.*;

public class WomenAndChildType extends CaseTypeAll{
    private String caseId;
    private String womenOrChild;
    private String caseType;
    private String description;
    private String location;
    private String date;
    private String sOrC;

    public WomenAndChildType() {

    }

    public WomenAndChildType(String caseId, String womenOrChild, String caseType, String description, String location, String date, String sOrC) {
        this.caseId = caseId;
        this.womenOrChild = womenOrChild;
        this.caseType = caseType;
        this.description = description;
        this.location = location;
        this.date = date;
        this.sOrC = sOrC;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getWomenOrChild() {
        return womenOrChild;
    }

    public void setWomenOrChild(String womenOrChild) {
        this.womenOrChild = womenOrChild;
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

    public String getsOrC() {
        return sOrC;
    }

    public void setsOrC(String sOrC) {
        this.sOrC = sOrC;
    }

    public static WomenAndChildType searchWomenAndChildType(String caseId) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("SELECT * FROM WomenAndChild WHERE caseId = ?");
        ps.setString(1 , caseId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            WomenAndChildType womenAndChildType = new WomenAndChildType();

            womenAndChildType.setCaseId(rs.getString(1));
            womenAndChildType.setWomenOrChild(rs.getString(2));
            womenAndChildType.setCaseType(rs.getString(3));
            womenAndChildType.setDescription(rs.getString(4));
            womenAndChildType.setDate(rs.getString(5));
            womenAndChildType.setLocation(rs.getString(6));
            womenAndChildType.setsOrC(rs.getString(7));

            return womenAndChildType;
        }else{
            return null;
        }

    }
    public static boolean addWomenAndChildType(WomenAndChildType womenAndChildType) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("INSERT INTO womenAndChild VALUES (? , ? , ? , ? , ? , ? , ?)");
        ps.setString(1 , womenAndChildType.getCaseId());
        ps.setString(2 , womenAndChildType.getWomenOrChild());
        ps.setString(3 , womenAndChildType.getCaseType());
        ps.setString(4 , womenAndChildType.getDescription());
        ps.setString(5 , womenAndChildType.getDate());
        ps.setString(6 , womenAndChildType.getLocation());
        ps.setString(7 , womenAndChildType.getsOrC());

        ps.executeUpdate();

        return true;

    }
    public static boolean addWcNic(String nic , String caseId) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("INSERT INTO criminal_wom VALUES (? , ?)");
        ps.setString(1 , nic);
        ps.setString(2 , caseId);

        ps.executeUpdate();
        return true;
    }

}
