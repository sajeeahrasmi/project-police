package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class TrafficAddNewCaseWindowFormController {
    public void btnAddNewOnAction(ActionEvent actionEvent) {

    }

    public void btnMainOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/TrafficMainMenuForm.fxml")));
    }
}
