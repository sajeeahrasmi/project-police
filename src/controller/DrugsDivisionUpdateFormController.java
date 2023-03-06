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
import model.DrugsType;
import model.MoType;

import java.io.IOException;
import java.sql.SQLException;

public class DrugsDivisionUpdateFormController {
    public JFXButton mainBtn;
    public JFXButton backBtn;
    public JFXButton saveBtn;
    public JFXTextField dateId;
    public JFXTextField drugsTypeId;
    public JFXTextField drugsAmoutId;
    public JFXTextField getFromWhomId;
    public JFXTextField toWhomId;
    public ComboBox caseTypeId;
    public ComboBox sOrCId;
    public ComboBox locationId;
    public JFXTextArea descriptionId;
    public JFXTextField caseId;

    @FXML
    public void initialize(){
        caseTypeId.getItems().addAll(
                "Minor drug offenses, such as possession",
                "Drug Cultivation",
                "Drug Trafficking and Drug Distribution",
                "Delivery of a Controlled Substance and Possession with Intent to Distribute",
                "Manufacturing drugs",
                "drugs importing",
                "Drugs selling for  school children",
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
        sOrCId.getItems().addAll(
                "minor case",
                "major case"
        );
    }

    public void mainBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalMainMenuForm.fxml")));
    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalDetailsForm.fxml")));
    }

    public void saveBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if (caseTypeId.getValue() != null && dateId.getText().length() > 0 && locationId.getValue() != null && descriptionId.getText().length() > 0 && caseId.getText().length() > 0 && sOrCId.getValue() != null && drugsTypeId.getText().length() > 0 && drugsAmoutId.getText().length() > 0 && getFromWhomId.getText().length() > 0 && toWhomId.getText().length() > 0 ) {
            DrugsType drugsType = new DrugsType();

            DrugsType temp = DrugsType.searchDrugsType(caseId.getText());
            if (temp != null ){
                new Alert(Alert.AlertType.ERROR,
                        "Case Id duplicate",
                        ButtonType.OK).show();
                return;
            }

            drugsType.setCaseId(caseId.getText());
            drugsType.setCaseType((String) caseTypeId.getValue());
            drugsType.setDescription(descriptionId.getText());
            drugsType.setLocation((String) locationId.getValue());
            drugsType.setDate(dateId.getText());
            drugsType.setsOrC((String) sOrCId.getValue());
            drugsType.setDrugsType(drugsTypeId.getText());
            drugsType.setDrugsAmount(drugsAmoutId.getText());
            drugsType.setGetFromWhom(getFromWhomId.getText());
            drugsType.setToWhom(toWhomId.getText());

            DrugsType.addDrugsType(drugsType);
            DrugsType.addDrugsNic(CriminalDetailsFormController.criminal.getNic() , drugsType.getCaseId());

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
}
