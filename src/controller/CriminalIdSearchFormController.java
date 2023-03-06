package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.Criminal;

import java.io.IOException;
import java.sql.SQLException;

public class CriminalIdSearchFormController {

    public Button btnSearch;
    public Button btnMain;
    public TextField idOrNameTextField;

    public void btnSeachOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        if (idOrNameTextField.getText().length() > 0){
            Criminal criminal = Criminal.searchCriminal(idOrNameTextField.getText());
            if (criminal != null) {
                CriminalDetailsFormController.criminal = criminal;

                LoginMainFormController.mainMenuRootCopy.getChildren().clear();
                LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalDetailsForm.fxml")));
            }else{
                new Alert(Alert.AlertType.INFORMATION ,
                        "Id or Name Not Found" ,
                        ButtonType.OK).show();

            }
        }else{
            new Alert(Alert.AlertType.INFORMATION ,
                    "Enter Full Name or NIC (ID)" ,
                    ButtonType.OK).show();
        }
    }

    public void btnMainOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalMainMenuForm.fxml")));
    }

    public void idOrNameTextField(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        btnSeachOnAction(new ActionEvent());
    }
}
