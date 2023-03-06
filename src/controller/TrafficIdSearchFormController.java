package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Vehicle;

import java.io.IOException;
import java.sql.SQLException;

public class TrafficIdSearchFormController {
    public JFXTextField searchIdTextField;
    public JFXButton searchBtn;
    public JFXButton mainMenuBtn;

    public void searchIdTextFieldOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        searchBtnOnAction(new ActionEvent());
    }

    public void searchBtnOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        if (searchIdTextField.getText().length() == 0){
            new Alert(Alert.AlertType.ERROR,
                    "Enter vehicle no or driver id",
                    ButtonType.OK).show();
        return;
        }

        Vehicle vehicle = Vehicle.searchVehicle(searchIdTextField.getText());
        if (vehicle == null ){
            new Alert(Alert.AlertType.ERROR,
                    "Vehicle no or id not found",
                    ButtonType.OK).show();
            searchIdTextField.setText("");
        }else {
            VehicleShowDataFormController.vehicle = vehicle;
            LoginMainFormController.mainMenuRootCopy.getChildren().clear();
            LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/VehicleShowDataForm.fxml")));
        }

    }

    public void mainMenuBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/TrafficMainMenuForm.fxml")));
    }
}
