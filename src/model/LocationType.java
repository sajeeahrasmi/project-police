package model;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class LocationType {
    private String location;
    private String count;
    private int minorCase;
    private int majorCount;
    private int criminals;

    public int getCriminals() {
        return criminals;
    }

    public void setCriminals(int criminals) {
        this.criminals = criminals;
    }

    public LocationType() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getMinorCase() {
        return minorCase;
    }

    public void setMinorCase(int minorCase) {
        this.minorCase = minorCase;
    }

    public int getMajorCount() {
        return majorCount;
    }

    public void setMajorCount(int majorCount) {
        this.majorCount = majorCount;
    }

    public static ArrayList<LocationType> mapView() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        String[] locations = {"Mount Lavinia", "Piliyandala","Angulana", "Dehiwala", "Kohuwala", "Moratuwa", "Kahathuduwa", "Moratumulla", "Egoda Uyana", "Kasbewa", "Other"};


        PreparedStatement ps1 = con.prepareStatement("SELECT criminal_wom.nic , womenAndChild.location , COUNT(criminal_wom.nic) AS nicCount FROM criminal_wom INNER JOIN womenAndChild ON criminal_wom.caseId = womenAndChild.caseId WHERE womenAndChild.location = ? GROUP by nic ORDER BY nicCount DESC");

        PreparedStatement ps2 = con.prepareStatement("SELECT criminal_mo.nic , mo.location, COUNT(criminal_mo.nic) AS nicCount FROM criminal_mo INNER JOIN mo ON criminal_mo.caseId = mo.caseId WHERE mo.location = ? GROUP by nic ORDER BY nicCount DESC");

        PreparedStatement ps3 = con.prepareStatement("SELECT criminal_crim.nic , crime.location , COUNT(criminal_crim.nic) AS nicCount FROM criminal_crim INNER JOIN crime ON criminal_crim.caseId = crime.caseId WHERE crime.location = ? GROUP by nic ORDER BY nicCount DESC");

        PreparedStatement ps4 = con.prepareStatement("SELECT criminal_drugs.nic , drugs.location , COUNT(criminal_drugs.nic) AS nicCount FROM criminal_drugs INNER JOIN drugs ON criminal_drugs.caseId = drugs.caseId WHERE drugs.location = ? GROUP by nic ORDER BY nicCount DESC");

        ArrayList<LocationType> arrayList = new ArrayList<>();

        for (int i=0; i< locations.length ;i++) {

            HashSet<String> arrayListNic = new HashSet<>();

            ps1.setString(1, locations[i]);
            ResultSet rs1 = ps1.executeQuery();

            ps2.setString(1 , locations[i]);
            ResultSet rs2 = ps2.executeQuery();

            ps3.setString(1 , locations[i]);
            ResultSet rs3 = ps3.executeQuery();

            ps4.setString(1 , locations[i]);
            ResultSet rs4 = ps4.executeQuery();

            LocationType locationType = new LocationType();
            locationType.location = locations[i];
            int count=0;

            while (rs1.next()){
                count+= rs1.getInt(3);
                arrayListNic.add(rs1.getString(1));
            }

            while (rs2.next()){
                count+= rs2.getInt(3);
                arrayListNic.add(rs2.getString(1));
            }

            while (rs3.next()){
                count+= rs3.getInt(3);
                arrayListNic.add(rs3.getString(1));
            }

            while (rs4.next()){
                count+= rs4.getInt(3);
                arrayListNic.add(rs4.getString(1));
            }

            int minor = 0;
            int major = 0;
            for (String nic:
                    arrayListNic) {
                minor += CaseTypeAll.searchCriminalMinorCases(nic).size();
                major += CaseTypeAll.searchCriminalMajorCases(nic).size();
            }

            locationType.setMinorCase(minor);
            locationType.setMajorCount(major);
            locationType.setCriminals(arrayListNic.size());

            locationType.count = count+"";
            arrayList.add(locationType);
        }

        return arrayList;

    }

}
