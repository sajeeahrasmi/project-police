package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import model.CaseTypeAll;

import java.io.IOException;
import java.sql.SQLException;

public class CatogeryFormController {
    public ComboBox typeOfCaseId;
    public ComboBox areaId;
    public Button searchBtnId;
    public Button mainBtn;

    @FXML
    public void initialize(){
        typeOfCaseId.getItems().addAll(
                "Credit Card Fraud", //crime /mo /drugs /wc
                "Embezzlement (above rs.500000)",
                "Identity Theft",
                "Homicide",
                "Insurance Fraud (above Rs 500000)",
                "Theft of property above a certain Rs500000 value (grand theft)",
                "Money Laundering",
                "Pyramid Schemes",
                "Robbery above Rs50000",
                "Securities Fraud",

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

                "Minor drug offenses, such as possession",
                "Drug Cultivation",
                "Drug Trafficking and Drug Distribution",
                "Delivery of a Controlled Substance and Possession with Intent to Distribute",
                "Manufacturing drugs",
                "drugs importing",
                "Drugs selling for  school children",

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
        areaId.getItems().addAll(
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

    public void searchBtnIdOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        if (typeOfCaseId.getValue() != null && areaId.getValue() != null) {
            CatogeryViewFormControllers.caseType = (String) typeOfCaseId.getValue();
            CatogeryViewFormControllers.locattion = (String) areaId.getValue();

            CatogeryViewFormControllers.catogerizedArray = CaseTypeAll.catogerized(CatogeryViewFormControllers.caseType , CatogeryViewFormControllers.locattion);
            if (CatogeryViewFormControllers.catogerizedArray.size() == 0){
                new Alert(Alert.AlertType.INFORMATION ,
                        "No Criminals found in this case type in this Area" ,
                        ButtonType.OK).show();
                return;
            }

            LoginMainFormController.mainMenuRootCopy.getChildren().clear();
            LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CatogeryViewForm.fxml")));
        }else {
            new Alert(Alert.AlertType.INFORMATION ,
                    "Select case type and Area" ,
                    ButtonType.OK).show();
        }

    }

    public void mainBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalMainMenuForm.fxml")));
    }
}


