package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.CaseTypeAll;
import model.Criminal;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CriminalDetailsFormController {
    public static Criminal criminal;
    public static Criminal tempCriminal;

    public JFXButton addCaseBtn;
    public JFXButton updateBtn;
    public JFXTextField nicId;
    public JFXTextField dateOfBirthId;
    public JFXTextField addressId;
    public JFXTextField birthPlaceId;
    public JFXButton mainBtn;
    public JFXButton backBtn;
    public Slider minorCaseSlider;
    public Slider majorCaseSlider;
    public JFXButton getPdfBtn;
    public ComboBox selectDepartMentComboBox;
    public ImageView photoId;
    public JFXTextField genderId;
    public JFXTextField fullNameTextFieldId;

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        fullNameTextFieldId.setText(criminal.getFullName());
        nicId.setText(criminal.getNic());
        genderId.setText(criminal.getGender());
        dateOfBirthId.setText(criminal.getBirthDay());
        addressId.setText(criminal.getAddress());
        birthPlaceId.setText(criminal.getBirthPlace());
        photoId.setImage(criminal.getPhoto());
        tempCriminal = new Criminal();

        addCaseBtn.setVisible(false);
        selectDepartMentComboBox.getItems().addAll(
                "Crime Department",
                "MO Department" ,
                "Women And Child Department" ,
                "Drugs Department"
        );

        minorCaseSlider.setValue(CaseTypeAll.searchCriminalMinorCases(criminal.getNic()).size());
        majorCaseSlider.setValue(CaseTypeAll.searchCriminalMajorCases(criminal.getNic()).size());

        minorCaseSlider.setDisable(true);
        majorCaseSlider.setDisable(true);
    }

    public void addCaseBtnOnAction(ActionEvent actionEvent) throws IOException {
        String department = (String) selectDepartMentComboBox.getValue();
        switch (department){
            case "Crime Department" :
                LoginMainFormController.mainMenuRootCopy.getChildren().clear();
                LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CrimeDivisionUpdateForm.fxml")));
                break;
            case "MO Department" :
                LoginMainFormController.mainMenuRootCopy.getChildren().clear();
                LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/MoDivisionUpdateForm.fxml")));
                break;
            case "Women And Child Department" :
                LoginMainFormController.mainMenuRootCopy.getChildren().clear();
                LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/WcDivisionUpdateForm.fxml")));
                break;
            case "Drugs Department" :
                LoginMainFormController.mainMenuRootCopy.getChildren().clear();
                LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/DrugsDivisionUpdateForm.fxml")));
                break;
        }

    }

    public void updateBtnOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        if (getPdfBtn.isVisible()){
            fullNameTextFieldId.setEditable(true);
            nicId.setEditable(true);
            genderId.setEditable(true);
            dateOfBirthId.setEditable(true);
            addressId.setEditable(true);
            birthPlaceId.setEditable(true);

            addCaseBtn.setVisible(false);
            selectDepartMentComboBox.setVisible(false);
            getPdfBtn.setVisible(false);

        }else{
            if (fullNameTextFieldId.getText().length() > 0 && nicId.getText().length() > 0 && genderId.getText().length() > 0 && dateOfBirthId.getText().length() > 0 && addressId.getText().length() > 0 && birthPlaceId.getText().length() > 0 && photoId.getImage() != null) {
                fullNameTextFieldId.setEditable(false);
                nicId.setEditable(false);
                genderId.setEditable(false);
                dateOfBirthId.setEditable(false);
                addressId.setEditable(false);
                birthPlaceId.setEditable(false);

                selectDepartMentComboBox.setVisible(true);
                getPdfBtn.setVisible(true);

                tempCriminal.setFullName(fullNameTextFieldId.getText());
                tempCriminal.setNic(nicId.getText());
                tempCriminal.setGender(genderId.getText());
                tempCriminal.setBirthDay(dateOfBirthId.getText());
                tempCriminal.setAddress(addressId.getText());
                tempCriminal.setBirthPlace(birthPlaceId.getText());

                if (tempCriminal.getPhoto() == null){
                    WritableImage imageWritable = (WritableImage) photoId.getImage();
                    tempCriminal.setPhoto(imageWritable);
                }else{

                }

                Criminal.updateCriminal(tempCriminal , criminal.getNic());
                criminal = tempCriminal;
                tempCriminal = new Criminal();
                new Alert(Alert.AlertType.INFORMATION,
                        "Update Success",
                        ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.ERROR ,
                        "Fill All Data" ,
                        ButtonType.OK).show();
            }
        }

    }

    public void mainBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalMainMenuForm.fxml")));

    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalIdSearchForm.fxml")));

    }

    public void getPdfBtnOnAction(ActionEvent actionEvent) {

    }

    public void selectDepartMentComboBoxOnAction(ActionEvent actionEvent) {
        addCaseBtn.setVisible(true);
    }

    public void photoIdOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        if (getPdfBtn.isVisible()){

        }else{
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(new Stage());

            if (selectedFile != null) {
                FileInputStream in = new FileInputStream(selectedFile);
                BufferedImage bufferedImage = ImageIO.read(in);
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null );
                tempCriminal.setPhoto(image);
                photoId.setImage(image);

            }
        }
    }

}
