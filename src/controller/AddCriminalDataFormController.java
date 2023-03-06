package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

import java.io.IOException;

public class AddCriminalDataFormController {
    public JFXButton btnAdd;
    public JFXButton btnMain;
    public ComboBox departmentComboBox;

    @FXML
    public void initialize(){
        departmentComboBox.getItems().addAll(
                "Mo Department",
                "Crime Department",
                "Drugs Department",
                "WomenAndChild Department"
        );
    }

    public void btnMainOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalMainMenuForm.fxml")));
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        if (departmentComboBox.getValue() == null){
            new Alert(Alert.AlertType.WARNING ,
                    "Select a department" ,
                    ButtonType.OK).show();
            return;
        }
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        switch ((String) departmentComboBox.getValue()){
            case "Mo Department" :

                LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/MoWindowForm.fxml")));
                break;
            case "Crime Department" :
                LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CrimeWindowForm.fxml")));
                break;
            case "Drugs Department" :
                LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/DrugsAddDataForm.fxml")));
                break;
            case "WomenAndChild Department" :
                LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/WomenAndChildAddDataForm.fxml")));
                break;
        }
    }
}
