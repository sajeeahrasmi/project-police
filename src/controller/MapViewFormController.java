package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import model.LocationType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MapViewFormController {
    public static ArrayList<LocationType> ar;
    public static int round;

    public Label label1;
    public JFXButton backWardBtn;
    public JFXButton forwardBtn;
    public Label label4;
    public Label label2;
    public Label label3;
    public JFXButton mainBtn;

    @FXML
    public void initialize() throws SQLException, IOException, ClassNotFoundException {
        ar = LocationType.mapView();
        round++;
        visible();
        changing();

    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/SummaryForm.fxml")));
    }

    public void backWardBtnOnAction(ActionEvent actionEvent) throws IOException {
        round--;
        visible();
        changing();
    }

    public void forwardBtnOnAction(ActionEvent actionEvent) {
        round++;
        visible();
        changing();
    }

    public void mainBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalMainMenuForm.fxml")));
    }
    public void visible(){
        if (round == 1){
            backWardBtn.setVisible(false);
        }else if (round == 2){
            backWardBtn.setVisible(true);
            forwardBtn.setVisible(true);
            label3.setVisible(true);
            label4.setVisible(true);
        }else {
            forwardBtn.setVisible(false);
            label3.setVisible(false);
            label4.setVisible(false);
        }
    }
    public void changing(){
        if (round == 1){
            label1.setText( "\t\tLocation   : "+ar.get(0).getLocation()+"\n\n\tAll cases    : "+ar.get(0).getCount()+"\n\n"+
                "\tMinor cases  : "+ar.get(0).getMinorCase()+"\n\n\t"+"Major cases  : "+ar.get(0).getMajorCount()+"\n\n\tCriminals  : "+ar.get(0).getCriminals());
            label2.setText( "\t\tLocation   : "+ar.get(1).getLocation()+"\n\n\tAll cases    : "+ar.get(1).getCount()+"\n\n"+
                "\tMinor cases  : "+ar.get(1).getMinorCase()+"\n\n\t"+"Major cases  : "+ar.get(1).getMajorCount()+"\n\n\tCriminals  : "+ar.get(1).getCriminals());
            label3.setText( "\t\tLocation   : "+ar.get(2).getLocation()+"\n\n\tAll cases    : "+ar.get(2).getCount()+"\n\n"+
                "\tMinor cases  : "+ar.get(2).getMinorCase()+"\n\n\t"+"Major cases  : "+ar.get(2).getMajorCount()+"\n\n\tCriminals  : "+ar.get(2).getCriminals());
            label4.setText( "\t\tLocation   : "+ar.get(3).getLocation()+"\n\n\tAll cases    : "+ar.get(3).getCount()+"\n\n"+
                "\tMinor cases  : "+ar.get(3).getMinorCase()+"\n\n\t"+"Major cases  : "+ar.get(3).getMajorCount()+"\n\n\tCriminals  : "+ar.get(3).getCriminals());
        }else if (round == 2){
            label1.setText( "\t\tLocation   : "+ar.get(4).getLocation()+"\n\n\tAll cases    : "+ar.get(4).getCount()+"\n\n"+
                    "\tMinor cases  : "+ar.get(4).getMinorCase()+"\n\n\t"+"Major cases  : "+ar.get(4).getMajorCount()+"\n\n\tCriminals  : "+ar.get(4).getCriminals());
            label2.setText( "\t\tLocation   : "+ar.get(5).getLocation()+"\n\n\tAll cases    : "+ar.get(5).getCount()+"\n\n"+
                    "\tMinor cases  : "+ar.get(5).getMinorCase()+"\n\n\t"+"Major cases  : "+ar.get(5).getMajorCount()+"\n\n\tCriminals  : "+ar.get(5).getCriminals());
            label3.setText( "\t\tLocation   : "+ar.get(6).getLocation()+"\n\n\tAll cases    : "+ar.get(6).getCount()+"\n\n"+
                    "\tMinor cases  : "+ar.get(6).getMinorCase()+"\n\n\t"+"Major cases  : "+ar.get(6).getMajorCount()+"\n\n\tCriminals  : "+ar.get(6).getCriminals());
            label4.setText( "\t\tLocation   : "+ar.get(7).getLocation()+"\n\n\tAll cases    : "+ar.get(7).getCount()+"\n\n"+
                    "\tMinor cases  : "+ar.get(7).getMinorCase()+"\n\n\t"+"Major cases  : "+ar.get(7).getMajorCount()+"\n\n\tCriminals  : "+ar.get(7).getCriminals());

        }else {
            label1.setText( "\t\tLocation   : "+ar.get(8).getLocation()+"\n\n\tAll cases    : "+ar.get(8).getCount()+"\n\n"+
                    "\tMinor cases  : "+ar.get(8).getMinorCase()+"\n\n\t"+"Major cases  : "+ar.get(8).getMajorCount()+"\n\n\tCriminals  : "+ar.get(8).getCriminals());
            label2.setText( "\t\tLocation   : "+ar.get(9).getLocation()+"\n\n\tAll cases    : "+ar.get(9).getCount()+"\n\n"+
                    "\tMinor cases  : "+ar.get(9).getMinorCase()+"\n\n\t"+"Major cases  : "+ar.get(9).getMajorCount()+"\n\n\tCriminals  : "+ar.get(9).getCriminals());

        }
    }
}
