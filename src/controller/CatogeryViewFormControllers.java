package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Text;
import model.CaseTypeAll;
import model.CriminalCategorizedType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CatogeryViewFormControllers {
    public static String caseType;
    public static String locattion;
    public static ArrayList<CriminalCategorizedType> catogerizedArray;
    public static CriminalCategorizedType categorizedType1;
    public static int round;
    public static CriminalCategorizedType[][] ar;

    public JFXTextField fullNameId1;
    public JFXTextField addressId1;
    public JFXTextField nicId1;
    public JFXTextField age1;
    public JFXTextField count1;
    public JFXTextField fullNameId2;
    public JFXTextField nicId2;
    public JFXTextField addressId2;
    public JFXTextField age2;
    public JFXTextField count2;
    public JFXTextField fullNameId3;
    public JFXTextField nicId3;
    public JFXTextField addressId3;
    public JFXTextField age3;
    public JFXTextField count3;
    public JFXButton backBtn;
    public JFXButton nextBtn;
    public ImageView imageView1;
    public ImageView imageView2;
    public ImageView imageView3;
    public Button backBtnMain;
    public Button mainBtn;
    public Text fullNameLabel1;
    public Text nicLabel1;
    public Text addressLabel1;
    public Text birthday1;
    public Text countLabel1;
    public Text fullNameLabel2;
    public Text nicLabel2;
    public Text addressLabel2;
    public Text birthday2;
    public Text fullNameLabel3;
    public Text nicLabel3;
    public Text addressLabel3;
    public Text birthday3;
    public Text countLabel3;
    public Text countLabel2;

    @FXML
    public void initialize() throws SQLException, IOException, ClassNotFoundException {
        fullNameId1.setEditable(false);
        fullNameId2.setEditable(false);
        fullNameId3.setEditable(false);
        nicId1.setEditable(false);
        nicId2.setEditable(false);
        nicId3.setEditable(false);
        addressId1.setEditable(false);
        addressId2.setEditable(false);
        addressId3.setEditable(false);
        age1.setEditable(false);
        age2.setEditable(false);
        age3.setEditable(false);
        count1.setEditable(false);
        count2.setEditable(false);
        count3.setEditable(false);

        catogerizedArray = CaseTypeAll.catogerized(caseType , locattion);
        setInvisible();
        setColumnsForward();

        if (catogerizedArray.size()<=3){
            backBtn.setVisible(false);
        }




    }

    public void backBtnOnAction(ActionEvent actionEvent) {
        setColumnsBackWard();
    }

    public void nextBtnOnAction(ActionEvent actionEvent) {
        setInvisible();
        setColumnsForward();

    }

    public void backBtnMainOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CatogeryForm.fxml")));
    }

    public void mainBtnOnAction(ActionEvent actionEvent) throws IOException {
        LoginMainFormController.mainMenuRootCopy.getChildren().clear();
        LoginMainFormController.mainMenuRootCopy.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalMainMenuForm.fxml")));
    }




    public void setVisible(int numberOfColumns){
        if (numberOfColumns == 1){
            imageView1.setVisible(true);
            fullNameId1.setVisible(true);
            nicId1.setVisible(true);
            addressId1.setVisible(true);
            age1.setVisible(true);
            count1.setVisible(true);

            fullNameLabel1.setVisible(true);
            nicLabel1.setVisible(true);
            addressLabel1.setVisible(true);
            birthday1.setVisible(true);
            countLabel1.setVisible(true);
        }else if (numberOfColumns == 2){
            imageView1.setVisible(true);
            imageView2.setVisible(true);
            fullNameId1.setVisible(true);
            fullNameId2.setVisible(true);
            nicId1.setVisible(true);
            nicId2.setVisible(true);
            addressId1.setVisible(true);
            addressId2.setVisible(true);
            age1.setVisible(true);
            age2.setVisible(true);
            count1.setVisible(true);
            count2.setVisible(true);

            fullNameLabel1.setVisible(true);
            fullNameLabel2.setVisible(true);
            nicLabel1.setVisible(true);
            nicLabel2.setVisible(true);
            addressLabel1.setVisible(true);
            addressLabel2.setVisible(true);
            birthday1.setVisible(true);
            birthday2.setVisible(true);
            countLabel1.setVisible(true);
            countLabel2.setVisible(true);
        }else {
            imageView1.setVisible(true);
            imageView2.setVisible(true);
            imageView3.setVisible(true);
            fullNameId1.setVisible(true);
            fullNameId2.setVisible(true);
            fullNameId3.setVisible(true);
            nicId1.setVisible(true);
            nicId2.setVisible(true);
            nicId3.setVisible(true);
            addressId1.setVisible(true);
            addressId2.setVisible(true);
            addressId3.setVisible(true);
            age1.setVisible(true);
            age2.setVisible(true);
            age3.setVisible(true);
            count1.setVisible(true);
            count2.setVisible(true);
            count3.setVisible(true);

            fullNameLabel1.setVisible(true);
            fullNameLabel2.setVisible(true);
            fullNameLabel3.setVisible(true);
            nicLabel1.setVisible(true);
            nicLabel2.setVisible(true);
            nicLabel3.setVisible(true);
            addressLabel1.setVisible(true);
            addressLabel2.setVisible(true);
            addressLabel3.setVisible(true);
            birthday1.setVisible(true);
            birthday2.setVisible(true);
            birthday3.setVisible(true);
            countLabel1.setVisible(true);
            countLabel2.setVisible(true);
            countLabel3.setVisible(true);
        }


    }
    public void setInvisible(){
        imageView1.setVisible(false);
        imageView2.setVisible(false);
        imageView3.setVisible(false);
        fullNameId1.setVisible(false);
        fullNameId2.setVisible(false);
        fullNameId3.setVisible(false);
        nicId1.setVisible(false);
        nicId2.setVisible(false);
        nicId3.setVisible(false);
        addressId1.setVisible(false);
        addressId2.setVisible(false);
        addressId3.setVisible(false);
        age1.setVisible(false);
        age2.setVisible(false);
        age3.setVisible(false);
        count1.setVisible(false);
        count2.setVisible(false);
        count3.setVisible(false);

        fullNameLabel1.setVisible(false);
        fullNameLabel2.setVisible(false);
        fullNameLabel3.setVisible(false);
        nicLabel1.setVisible(false);
        nicLabel2.setVisible(false);
        nicLabel3.setVisible(false);
        addressLabel1.setVisible(false);
        addressLabel2.setVisible(false);
        addressLabel3.setVisible(false);
        birthday1.setVisible(false);
        birthday2.setVisible(false);
        birthday3.setVisible(false);
        countLabel1.setVisible(false);
        countLabel2.setVisible(false);
        countLabel3.setVisible(false);

    }
    public void setColumnsForward(){
        int temp = (round+3);
        for (int i=1; round < temp ; round++,i++ ){

            if (round==catogerizedArray.size()){
                nextBtn.setVisible(false);
                return;
            }else {
                categorizedType1 = catogerizedArray.get(round);
            }

            setVisible(i);
            iterating(i, categorizedType1.getImage(), categorizedType1.getFullName(), categorizedType1.getNic(), categorizedType1.getAddress(), categorizedType1.getBirthDay(), (categorizedType1.getCount() + ""));
            nextBtn.setVisible(true);
        }

    }

    public void setColumnsBackWard(){
        int temp = (round-3);
        for (int i=3; round > temp; round--,i-- ){
            if ((round-1)==-1){
                backBtn.setVisible(false);
                return;
            }else {
                categorizedType1 = catogerizedArray.get(round);
            }
            iterating(i, categorizedType1.getImage(), categorizedType1.getFullName(), categorizedType1.getNic(), categorizedType1.getAddress(), categorizedType1.getBirthDay(), (categorizedType1.getCount() + ""));



        }
        if (round>3) {
            backBtn.setVisible(true);
        }
    }
    public void iterating(int x , WritableImage image , String fullName ,String nic , String address , String birthDay , String count){
        if ( x == 1) {
            imageView1.setImage(image);
            fullNameId1.setText(fullName);
            nicId1.setText(nic);
            addressId1.setText(address);
            age1.setText(birthDay);
            count1.setText(count);
        }else if ( x == 2) {
            imageView2.setImage(image);
            fullNameId2.setText(fullName);
            nicId2.setText(nic);
            addressId2.setText(address);
            age2.setText(birthDay);
            count2.setText(count);
        }else {
            imageView3.setImage(image);
            fullNameId3.setText(fullName);
            nicId3.setText(nic);
            addressId3.setText(address);
            age3.setText(birthDay);
            count3.setText(count);
        }


    }
}
