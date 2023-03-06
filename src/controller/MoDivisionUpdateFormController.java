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
import model.Criminal;
import model.DrugsType;
import model.MoType;

import java.io.IOException;
import java.sql.SQLException;

public class MoDivisionUpdateFormController {
    public JFXButton backBtn;
    public JFXButton mainBtn;
    public ComboBox caseTypeId;
    public JFXTextField dateTextFieldId;
    public ComboBox locationId;
    public JFXButton saveBtn;
    public JFXTextArea descriptionTextAreaId;
    public JFXTextField caseId;

    @FXML
    public void initialize(){
        caseTypeId.getItems().addAll(
                "Domestic Violence",
                "Child Abandonment ",
                "Child Abuse ",
                "Child Pornography ",
                "Kidnapping",
                "Involve minors in community service",
                "Get chemical dependency treatment and help for minors",
                "Prostitution",
                "Rape Crimes",
                "Sexual Assault",
                "Statutory Rape",
                "other"
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

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalDetailsForm.fxml")));
    }

    public void mainBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalMainMenuForm.fxml")));
    }

    public void saveBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if (caseTypeId.getValue() != null && dateTextFieldId.getText().length() > 0 && locationId.getValue() != null && descriptionTextAreaId.getText().length() > 0 && caseId.getText().length() > 0){
            MoType moType = new MoType();

            MoType temp = MoType.searchMoType(caseId.getText());
            if (temp != null ){
                new Alert(Alert.AlertType.ERROR,
                        "Case Id duplicate",
                        ButtonType.OK).show();
                return;
            }

            moType.setCaseId(caseId.getText());
            moType.setCaseType((String) caseTypeId.getValue());
            moType.setDescription(descriptionTextAreaId.getText());
            moType.setLocation((String) locationId.getValue());
            moType.setDate(dateTextFieldId.getText());

            MoType.addMoType(moType);
//            MoType.addMoNic(CriminalDetailsFormController.criminal.getNic() , moType.getCaseId());

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

