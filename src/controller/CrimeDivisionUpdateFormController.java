package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import model.CrimeType;
import model.MoType;

import java.io.IOException;
import java.sql.SQLException;

public class CrimeDivisionUpdateFormController {
    public ComboBox caseTypeComboBoxId;
    public JFXTextField dateTextFieldId;
    public ComboBox locationId;
    public JFXButton saveBtn;
    public JFXTextArea descriptionTextAreaId;
    public JFXButton backBtn;
    public JFXButton mainBtn;
    public JFXTextField caseId;

    @FXML
    public void initialize(){
        caseTypeComboBoxId.getItems().addAll(
                "Credit Card Fraud",
                "Embezzlement (above rs.500000)",
                "Identity Theft",
                "Homicide",
                "Insurance Fraud (above Rs 500000)",
                "Theft of property above a certain Rs500000 value (grand theft)",
                "Money Laundering",
                "Pyramid Schemes",
                "Robbery above Rs50000",
                "Securities Fraud",
                "Other"
        );
        locationId.getItems().addAll(
                "Mount Lavinia",
                "Piliyandala",
                "Angulana",
                "Dehiwala",
                "Kohuwala",
                "Moratuwa",
                "Kahathuduwa",
                "Moratumulla",
                "Egoda Uyana",
                "Kasbewa",
                "Other"
        );
    }

    public void mainBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalMainMenuForm.fxml")));
    }

    public void saveBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if (caseTypeComboBoxId.getValue() != null && dateTextFieldId.getText().length() > 0 && locationId.getValue() != null && descriptionTextAreaId.getText().length() > 0 && caseId.getText().length() > 0) {
            CrimeType crimeType = new CrimeType();

            CrimeType tempCrimeType = CrimeType.searchCrimeType(caseId.getText());
            if (tempCrimeType != null ){
                new Alert(Alert.AlertType.ERROR,
                        "Case Id duplicate",
                        ButtonType.OK).show();
                return;
            }

            crimeType.setCaseId(caseId.getText());
            crimeType.setCaseType((String) caseTypeComboBoxId.getValue());
            crimeType.setDescription(descriptionTextAreaId.getText());
            crimeType.setLocation((String) locationId.getValue());
            crimeType.setDate(dateTextFieldId.getText());

            CrimeType.addCrimeType(crimeType);
            CrimeType.addCrimeNic(CriminalDetailsFormController.criminal.getNic() , crimeType.getCaseId());

            new Alert(Alert.AlertType.INFORMATION,
                    "Add Data Success",
                    ButtonType.OK).show();

            LoginMainFormController.mainMenuRootCopy.getChildren().clear();
            LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalDetailsForm.fxml")));

        }else{
            new Alert(Alert.AlertType.ERROR,
                    "Fill All Data Fields",
                    ButtonType.OK).show();
        }
    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalDetailsForm.fxml")));
    }
}

