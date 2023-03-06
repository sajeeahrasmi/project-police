package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class AddNewCaseWindowFormController {
    public static String department;
    public void btnAddNewOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/"+department)));
    }

    public void btnMainOnAction(ActionEvent actionEvent) {

    }
}
