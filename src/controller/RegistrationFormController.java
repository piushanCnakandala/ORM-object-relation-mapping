package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class RegistrationFormController {
    public AnchorPane registrationPane;
    public JFXTextField txtRegNumber;
    public JFXTextField txtEmail;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtAge;
    public JFXTextField txtContact;
    public JFXComboBox cmdProgrammId1;
    public JFXTextField txtFee1;
    public JFXTextField txtDuration1;
    public TableView tblRegister;
    public JFXTextField txtProgramName1;
    public JFXComboBox cmdProgrammId2;
    public JFXComboBox cmdProgrammId3;
    public JFXTextField txtProgramName2;
    public JFXTextField txtProgramName3;
    public JFXTextField txtDuration2;
    public JFXTextField txtFee2;
    public JFXTextField txtDuration3;
    public JFXTextField txtFee3;
    public RadioButton genderMale;
    public RadioButton genderFemale;

    public void registerOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void removeOnAction(ActionEvent actionEvent) {
    }

    public void searchOnKeyRelesed(KeyEvent keyEvent) {
    }
}
