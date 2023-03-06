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

public class WomenAndChildAddDataFormController {
    private static Criminal criminal;

    public RadioButton minorCase;
    public RadioButton majorCase;
    public TextField firstName;
    public TextField nic;
    public TextField dateOfBirth;
    public TextField birthPlace;
    public TextField lastName;
    public TextField date;
    public TextArea description;
    public RadioButton male;
    public RadioButton female;
    public RadioButton child;
    public RadioButton women;
    public TextArea address;
    public TextField caseId;
    public ComboBox caseTypeId;
    public ComboBox locationId;
    public Button saveBtn;
    public ImageView imageViewId;
    public Button backBtn;
    public Button mainBtn;

    @FXML
    public void initialize(){
        criminal = new Criminal();
        criminal.setCaseTypeAll(new CaseTypeAll());
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

    public void saveBtnOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        if (imageViewId.getImage() != null && firstName.getText().length() > 0 && lastName.getText().length() > 0 && nic.getText().length() > 0 && dateOfBirth.getText().length() > 0 && address.getText().length() > 0 && birthPlace.getText().length() > 0 && date.getText().length() > 0 && (male.isSelected() ^ female.isSelected()) && description.getText().length() > 0 && caseId.getText().length() > 0 && locationId.getValue() != null && caseTypeId.getValue() != null && (women.isSelected() ^ child.isSelected())) {
            criminal.setFullName(firstName.getText()+" "+lastName.getText());
            criminal.setNic(nic.getText());

            String temp="";
            if (male.isSelected()){
                temp="male";
            }else {
                temp="male";
            }
            criminal.setGender(temp);
            if (women.isSelected()){
                temp="women";
            }else {
                temp = "child";
            }
            criminal.setBirthDay(dateOfBirth.getText());
            criminal.setAddress(address.getText());
            criminal.setBirthPlace(birthPlace.getText());

            WomenAndChildType womenAndChildType = new WomenAndChildType();

            womenAndChildType.setWomenOrChild(temp);
            womenAndChildType.setCaseId(caseId.getText());
            womenAndChildType.setCaseType((String) caseTypeId.getValue());
            womenAndChildType.setDescription(description.getText());
            womenAndChildType.setLocation((String) locationId.getValue());
            womenAndChildType.setDate(date.getText());

            criminal.setCaseTypeAll(womenAndChildType);
            if (imageViewId.getImage() ==null){
                new Alert(Alert.AlertType.INFORMATION,
                        "Fill All data correctly",
                        ButtonType.OK).show();
                return;
            }
            Criminal tempCriminal = Criminal.searchCriminal(criminal.getNic());
            WomenAndChildType tempCase = WomenAndChildType.searchWomenAndChildType(caseId.getText());
            if (tempCriminal != null || tempCase != null){
                new Alert(Alert.AlertType.INFORMATION,
                        "Duplicate data Entry",
                        ButtonType.OK).show();
                return;
            }
            Criminal.addCriminal(criminal);
            WomenAndChildType.addWomenAndChildType(womenAndChildType);
            WomenAndChildType.addWcNic(criminal.getNic() , womenAndChildType.getCaseId());

            new Alert(Alert.AlertType.INFORMATION,
                    "Data has been successfully installed",
                    ButtonType.OK).show();

            AddNewCaseWindowFormController.department = "WomenAndChildAddDataForm.fxml";
            LoginMainFormController.mainMenuRootCopy.getChildren().clear();
            LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/AddNewCaseWindowForm.fxml")));


        }else {
            new Alert(Alert.AlertType.INFORMATION,
                    "Fill All data correctly",
                    ButtonType.OK).show();
        }
    }

    public void imageViewOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            FileInputStream in = new FileInputStream(selectedFile);
            BufferedImage bufferedImage = ImageIO.read(in);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null );
            criminal.setPhoto(image);
            imageViewId.setImage(image);
        }
    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/AddCriminalDataForm.fxml")));
    }

    public void mainBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalMainMenuForm.fxml")));
    }
}
