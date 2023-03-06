package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import model.DrugsType;
import model.MoType;
import model.WomenAndChildType;

import java.io.IOException;
import java.sql.SQLException;

public class WcDivisionUpdateFormController {
    public JFXButton saveBtn;
    public RadioButton womenId;
    public RadioButton childId;
    public ComboBox caseTypeId;
    public JFXTextField descriptionId;
    public ComboBox locationId;
    public JFXTextField dateId;
    public ComboBox sOrCId;
    public JFXButton mainBtn;
    public JFXButton backBtn;
    public JFXTextField caseId;

    @FXML
    public void initialize(){
        caseTypeId.getItems().addAll(
                "Petty theft, including shoplifting",
                "Minor or simple assault",
                "Trespassing",
                "Vandalism",
                "Resisting arrest",
                "Some cybercrimes, including stalking or bullying",
                "Theft of property above a certain Rs50000 value (but below the amount necessary for grand theft)",
                "Aiding and Abetting/Accessory ",
                "Arson",
                "Criminal Attempt",
                "Bribery",
                "Tax Evasion",
                "Jewelry Theft",
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

    public void saveBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if (caseTypeId.getValue() != null && dateId.getText().length() > 0 && locationId.getValue() != null && descriptionId.getText().length() > 0 && caseId.getText().length() > 0 && sOrCId.getValue() != null && (womenId.isSelected() ^ childId.isSelected() )) {
            WomenAndChildType womenAndChildType = new WomenAndChildType();

            WomenAndChildType temp = WomenAndChildType.searchWomenAndChildType(caseId.getText());
            if (temp != null ){
                new Alert(Alert.AlertType.ERROR,
                        "Case Id duplicate",
                        ButtonType.OK).show();
                return;
            }

            womenAndChildType.setCaseId(caseId.getText());
            womenAndChildType.setCaseType((String) caseTypeId.getValue());
            womenAndChildType.setDescription(descriptionId.getText());
            womenAndChildType.setLocation((String) locationId.getValue());
            womenAndChildType.setDate(dateId.getText());

            if (womenId.isSelected()){
                womenAndChildType.setWomenOrChild("women");
            }else {
                womenAndChildType.setWomenOrChild("child");
            }
            womenAndChildType.setsOrC((String) sOrCId.getValue());

            womenAndChildType.addWomenAndChildType(womenAndChildType);
            WomenAndChildType.addWcNic(CriminalDetailsFormController.criminal.getNic() , womenAndChildType.getCaseId());

            new Alert(Alert.AlertType.INFORMATION,
                    "Add Data Success",
                    ButtonType.OK).show();

            LoginMainFormController.mainMenuRootCopy.getChildren().clear();
            LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalDetailsForm.fxml")));
        } else{
            new Alert(Alert.AlertType.ERROR,
                    "Fill All Data Fields",
                    ButtonType.OK).show();
        }
    }

    public void mainBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalMainMenuForm.fxml")));
    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalDetailsForm.fxml")));
    }
}
