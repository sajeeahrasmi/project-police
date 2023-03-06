package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.jdbc.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.*;

public class LoginMainFormController {
    public JFXButton loginButton;

    public JFXTextField userNameTextField;
    public JFXPasswordField passwordNameTextField;

    public JFXCheckBox checkBoxCriminal;
    public JFXCheckBox checkBoxTraffic;

    public AnchorPane mainMenuRoot;
    public static AnchorPane mainMenuRootCopy;

    public void btnLoginOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException, IOException {

        if (checkBoxCriminal.isSelected() ^ checkBoxTraffic.isSelected()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projectPolice", "root", "1234");

            PreparedStatement psLogin = con.prepareStatement("SELECT * FROM loginTable");

            ResultSet rs = psLogin.executeQuery();

            rs.next();

            if (rs.getString("userName").equals(userNameTextField.getText()) && rs.getString("password").equals(passwordNameTextField.getText())){
                mainMenuRootCopy = mainMenuRoot;
                if (checkBoxCriminal.isSelected()){
                    mainMenuRoot.getChildren().clear();
                    mainMenuRoot.getChildren().add(FXMLLoader.load(getClass().getResource("../view/CriminalMainMenuForm.fxml")));


                }else{
                    mainMenuRoot.getChildren().clear();
                    mainMenuRoot.getChildren().add(FXMLLoader.load(getClass().getResource("../view/TrafficMainMenuForm.fxml")));
                }


            }else{
                new Alert(Alert.AlertType.ERROR ,
                        "Wrong User Name or Password !" ,
                        ButtonType.OK).show();
                    userNameTextField.setText("");
                    passwordNameTextField.setText("");
            }

        }else{
            new Alert(Alert.AlertType.ERROR ,
                    "Select A Department !" ,
                    ButtonType.OK).show();
        }



    }

    public void userNameTextFieldOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        btnLoginOnAction(new ActionEvent());
    }

    public void passwordNameTextFieldOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        btnLoginOnAction(new ActionEvent());
    }

    public void checkBoxCriminalOnKeyPressed(KeyEvent keyEvent) throws SQLException, ClassNotFoundException, IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            btnLoginOnAction(new ActionEvent());
        }
    }

    public void checkBoxTrafficOnKeyPressed(KeyEvent keyEvent) throws SQLException, ClassNotFoundException, IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            btnLoginOnAction(new ActionEvent());
        }
    }





}
