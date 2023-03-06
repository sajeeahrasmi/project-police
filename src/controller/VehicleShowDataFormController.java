package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Vehicle;

import java.io.IOException;

public class VehicleShowDataFormController {
    public static Vehicle vehicle;

    public TextField drName;
    public TextField driverIdNamer;
    public TextField address;
    public TextField driLicenseNo;
    public TextField contact;
    public TextField drop;
    public TextArea drDes;
    public Button backNo;
    public Button mainNo;
    public Button deleteNo;
    public Button updateNo;
    public ImageView drVehi;
    public TextField vehicleNo;
    public TextField vehiType;
    public TextField vehiColour;
    public TextField chasisNumber;
    public TextField engineNo;
    public TextField owner;
    public TextField vhiLiceNo;
    public TextArea des;
    public ImageView vehiImage;
    public Label nameLabel;
    public Label idNumberLabel;
    public Label addressLabel;
    public Label licenseLabel;
    public Label dobLabel;
    public Label contactLabel;

    @FXML
    public void initialize(){
        vehiImage.setImage(vehicle.getVehiPhoto());
        vehicleNo.setText(vehicle.getVehiNo());
        vehiType.setText(vehicle.getVehiType());
        vehiColour.setText(vehicle.getVehiColour());
        chasisNumber.setText(vehicle.getChassiNo());
        engineNo.setText(vehicle.getEngineNo());
        owner.setText(vehicle.getOwner());
        vhiLiceNo.setText(vehicle.getVehiLicenceNo());

        if (vehicle.getDriver() == null){
            addressLabel.setVisible(false);
            nameLabel.setVisible(false);
            idNumberLabel.setVisible(false);
            licenseLabel.setVisible(false);
            dobLabel.setVisible(false);
            contactLabel.setVisible(false);
            return;
        }
        drVehi.setImage(vehicle.getDriver().getImage());
        drName.setText(vehicle.getDriver().getName());
        driverIdNamer.setText(vehicle.getDriver().getId());
        address.setText(vehicle.getDriver().getAddress());
        driLicenseNo.setText(vehicle.getDriver().getLicenceNo());
        drop.setText(vehicle.getDriver().getBirthDay());

    }


    public void backNoOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/TrafficIdSearchForm.fxml")));
    }

    public void mainNoOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/TrafficMainMenuForm.fxml")));
    }

    public void deleteNoOnAction(ActionEvent actionEvent) {

    }

    public void updateNoOnAction(ActionEvent actionEvent) {

    }
}
