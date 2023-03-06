package model;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class CaseTypeAll {
    public static ArrayList<String> searchCriminalMinorCases(String nic) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("SELECT * FROM criminal_mo WHERE nic = ?");
        ps.setString(1 , nic);
        ResultSet rs = ps.executeQuery();

        ArrayList<String> caseIdAr = new ArrayList();

        while (rs.next()){
            caseIdAr.add(rs.getString(2));
        }
/////////////////////////////////////////////////////////////////
        ps = con.prepareStatement("SELECT criminal_wom.nic , criminal_wom.caseId , womenAndChild.sOrC FROM criminal_wom INNER JOIN womenAndChild ON criminal_wom.caseId = womenAndChild.caseId WHERE (criminal_wom.nic = ? && womenAndChild.sOrC = ?) ");
        ps.setString(1 , nic);
        ps.setString(2, "minor case");
        rs = ps.executeQuery();

        while (rs.next()){
            caseIdAr.add(rs.getString(2));
        }

/////////////////////////////////////////////////////////////////////

        ps = con.prepareStatement("SELECT criminal_drugs.nic , criminal_drugs.caseId , drugs.sOrC FROM criminal_drugs INNER JOIN drugs ON criminal_drugs.caseId = drugs.caseId WHERE (criminal_drugs.nic = ? && drugs.sOrC = ?) ");
        ps.setString(1 , nic);
        ps.setString(2, "minor case");
        rs = ps.executeQuery();

        while (rs.next()){
            caseIdAr.add(rs.getString(2));
        }

        return caseIdAr;
    }

    public static ArrayList<String> searchCriminalMajorCases(String nic) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("SELECT * FROM criminal_crim WHERE nic = ?");
        ps.setString(1 , nic);
        ResultSet rs = ps.executeQuery();

        ArrayList<String> caseIdAr = new ArrayList();

        while (rs.next()){
            caseIdAr.add(rs.getString(2));
        }
/////////////////////////////////////////////////////////////////////////////////
        ps = con.prepareStatement("SELECT criminal_wom.nic , criminal_wom.caseId , womenAndChild.sOrC FROM criminal_wom INNER JOIN womenAndChild ON criminal_wom.caseId = womenAndChild.caseId WHERE (criminal_wom.nic = ? && womenAndChild.sOrC = ?) ");
        ps.setString(1 , nic);
        ps.setString(2, "major case");
        rs = ps.executeQuery();

        while (rs.next()){
            caseIdAr.add(rs.getString(2));
        }
////////////////////////////////////////////////////////////////////////////////////

        ps = con.prepareStatement("SELECT criminal_drugs.nic , criminal_drugs.caseId , drugs.sOrC FROM criminal_drugs INNER JOIN drugs ON criminal_drugs.caseId = drugs.caseId WHERE (criminal_drugs.nic = ? && drugs.sOrC = ?) ");
        ps.setString(1 , nic);
        ps.setString(2, "major case");
        rs = ps.executeQuery();

        while (rs.next()){
            caseIdAr.add(rs.getString(2));
        }

        return caseIdAr;

    }

    public static ArrayList<CriminalCategorizedType> catogerized(String caseType , String location) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice" , "root" , "1234");

        PreparedStatement ps = con.prepareStatement("SELECT criminal_wom.nic , criminal_wom.caseId , womenAndChild.caseType , COUNT(criminal_wom.nic) AS nicCount FROM criminal_wom INNER JOIN womenAndChild ON criminal_wom.caseId = womenAndChild.caseId WHERE (womenAndChild.caseType = ? && womenAndChild.location = ?) GROUP by nic ORDER BY nicCount DESC");

        ps.setString(1 , caseType);
        ps.setString(2 , location);
        ResultSet rs = ps.executeQuery();

        ArrayList<CriminalCategorizedType> arrayList = new ArrayList<CriminalCategorizedType>();

        iterating(rs,arrayList);

        ps = con.prepareStatement("SELECT criminal_mo.nic , mo.caseType , COUNT(criminal_mo.nic) AS nicCount FROM criminal_mo INNER JOIN mo ON criminal_mo.caseId = mo.caseId WHERE (mo.caseType = ? && mo.location = ?) GROUP by nic ORDER BY nicCount DESC");

        ps.setString(1 , caseType);
        ps.setString(2 , location);
        rs = ps.executeQuery();

        iterating(rs,arrayList);

        ps = con.prepareStatement("SELECT criminal_crim.nic , criminal_crim.caseId , crime.caseType , COUNT(criminal_crim.nic) AS nicCount FROM criminal_crim INNER JOIN crime ON criminal_crim.caseId = crime.caseId WHERE (crime.caseType = ? && crime.location = ?) GROUP by nic ORDER BY nicCount DESC");

        ps.setString(1 , caseType);
        ps.setString(2 , location);
        rs = ps.executeQuery();

        iterating(rs,arrayList);

        ps = con.prepareStatement("SELECT criminal_drugs.nic , criminal_drugs.caseId , drugs.caseType , COUNT(criminal_drugs.nic) AS nicCount FROM criminal_drugs INNER JOIN drugs ON criminal_drugs.caseId = drugs.caseId WHERE (drugs.caseType = ? && drugs.location = ?) GROUP by nic ORDER BY nicCount DESC");

        ps.setString(1 , caseType);
        ps.setString(2 , location);
        rs = ps.executeQuery();

        iterating(rs,arrayList);

        return arrayList;

    }
    private static void iterating(ResultSet rs , ArrayList<CriminalCategorizedType> arrayList) throws SQLException, IOException, ClassNotFoundException {
        while (rs.next()){
            Criminal criminal = Criminal.searchCriminal(rs.getString(1));

            CriminalCategorizedType criminalCategorizedType = new CriminalCategorizedType();

            criminalCategorizedType.setBirthDay(criminal.getBirthDay());
            criminalCategorizedType.setImage(criminal.getPhoto());
            criminalCategorizedType.setFullName(criminal.getFullName());
            criminalCategorizedType.setNic(criminal.getNic());
            criminalCategorizedType.setAddress(criminal.getAddress());
            criminalCategorizedType.setCount(rs.getInt(4));
            criminalCategorizedType.setMinorCasesCount(searchCriminalMinorCases(criminal.getNic()).size());
            criminalCategorizedType.setMajorCasesCount(searchCriminalMajorCases(criminal.getNic()).size());

            arrayList.add(criminalCategorizedType);
        }
    }

}
