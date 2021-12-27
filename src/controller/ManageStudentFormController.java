package controller;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class ManageStudentFormController {
    public AnchorPane studentPane;
    public TableView tblStudentDeatils;
    public TableView tblProgramDeatils;
    public TableColumn colRegNo;
    public TableColumn colRegName;
    public TableColumn colAge;
    public TableColumn colconnectNumber;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableColumn colGender;
    public TableColumn colPId;
    public TableColumn colProgramme;
    public TableColumn colDuration;
    public TableColumn colFee;

    public void serachOnKeyReleased(KeyEvent keyEvent) {
    }
}
