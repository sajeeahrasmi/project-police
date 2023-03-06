package controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.CaseTypeAll;
import model.CrimeType;
import model.Criminal;
import model.WomenAndChildType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class CrimeWindowFormController {
    private static Criminal criminal;

    public TextField firstNameId;
    public Button mainBtn;
    public Button backBtn;
    public ImageView imageView;
    public Button saveBtn;
    public TextField lastNameId;
    public TextField nic;
    public TextField dob;
    public TextField addressId;
    public TextField birthPlace;
    public TextField date;
    public RadioButton male;
    public RadioButton female;
    public TextArea des;
    public TextField caseId;
    public ComboBox locationComboBoxId;
    public ComboBox caseTypeComboBoxId;

    @FXML
    public void initialize(){
        criminal = new Criminal();
        criminal.setCaseTypeAll(new CaseTypeAll());
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
        locationComboBoxId.getItems().addAll(
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

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/AddCriminalDataForm.fxml")));
    }

    public void imageViewOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            FileInputStream in = new FileInputStream(selectedFile);
            BufferedImage bufferedImage = ImageIO.read(in);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null );
            criminal.setPhoto(image);
            imageView.setImage(image);

        }
    }

    public void saveBtnOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        if (imageView.getImage() != null && firstNameId.getText().length() > 0 && lastNameId.getText().length() > 0 && nic.getText().length() > 0 && dob.getText().length() > 0 && addressId.getText().length() > 0 && birthPlace.getText().length() > 0 && date.getText().length() > 0 && (male.isSelected() ^ female.isSelected()) && des.getText().length() >0 && caseId.getText().length() > 0 && locationComboBoxId.getValue() != null && caseTypeComboBoxId.getValue() != null){
            criminal.setFullName(firstNameId.getText()+" "+lastNameId.getText());
            criminal.setNic(nic.getText());
            String temp="";
            if (male.isSelected()){
                temp="male";
            }else if (female.isSelected()){
                temp="male";
            }else {
                temp="other";
            }
            criminal.setGender(temp);
            criminal.setBirthDay(dob.getText());
            criminal.setAddress(addressId.getText());
            criminal.setBirthPlace(birthPlace.getText());

            CrimeType crimeType = new CrimeType();

            crimeType.setCaseId(caseId.getText());
            crimeType.setCaseType((String) caseTypeComboBoxId.getValue());
            crimeType.setDescription(des.getText());
            crimeType.setLocation((String) locationComboBoxId.getValue());
            crimeType.setDate(date.getText());

            criminal.setCaseTypeAll(crimeType);

            Criminal tempCriminal = Criminal.searchCriminal(criminal.getNic());
            WomenAndChildType tempCase = WomenAndChildType.searchWomenAndChildType(caseId.getText());
            if (tempCriminal != null || tempCase != null){
                new Alert(Alert.AlertType.INFORMATION,
                        "Duplicate data Entry",
                        ButtonType.OK).show();
                return;
            }

            Criminal.addCriminal(criminal);
            CrimeType.addCrimeType(crimeType);
            CrimeType.addCrimeNic(criminal.getNic() , crimeType.getCaseId());

            new Alert(Alert.AlertType.INFORMATION,
                    "Data has been successfully installed",
                    ButtonType.OK).show();

            LoginMainFormController.mainMenuRootCopy.getChildren().clear();
            LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/AddNewCaseWindowForm.fxml")));

        }else {
            new Alert(Alert.AlertType.INFORMATION,
                    "Fill All data correctly",
                    ButtonType.OK).show();
        }

    }
}
