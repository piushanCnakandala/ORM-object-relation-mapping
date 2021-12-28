package controller;

import bo.BOFactory;
import bo.custom.ProgramBO;
import bo.custom.impl.ProgramBOImpl;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOFactory;
import dao.custom.impl.ProgramDAOImpl;
import dto.ProgramDTO;
import dto.StudentDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import view.tm.ProgramTM;
import view.tm.StudentTM;

import java.sql.SQLException;
import java.util.List;

public class RegistrationFormController {
    public AnchorPane registrationPane;
    public JFXTextField txtRegNumber;
    public JFXTextField txtEmail;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtAge;
    public JFXTextField txtContact;
    public JFXComboBox<String>  cmdProgrammId1;
    public JFXTextField txtFee1;
    public JFXTextField txtDuration1;
    public TableView tblRegister;
    public JFXTextField txtProgramName1;
    public JFXComboBox<String> cmdProgrammId2;
    public JFXComboBox<String>  cmdProgrammId3;
    public JFXTextField txtProgramName2;
    public JFXTextField txtProgramName3;
    public JFXTextField txtDuration2;
    public JFXTextField txtFee2;
    public JFXTextField txtDuration3;
    public JFXTextField txtFee3;
    public RadioButton genderMale;
    public RadioButton genderFemale;
    public TableColumn colRegNo;
    public TableColumn colName;
    public TableColumn colAge;
    public TableColumn colContactNum;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableColumn colGender;
    public ToggleGroup gender;
    public JFXCheckBox check1;
    public JFXCheckBox check2;
    public JFXCheckBox check3;

    StudentBOImpl studentBO = (StudentBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.STUDENT);
    private final ProgramBOImpl programBO  = (ProgramBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.PROGRAM);


    public String selectGender() {
        if (genderMale.isSelected()) {
            return "Male";
        } else if (genderFemale.isSelected()) {
            return "female";
        } else {
            return null;
        }
    }

    public void registerOnAction(ActionEvent actionEvent) {

        StudentDTO studentDTO = new StudentDTO(
                txtRegNumber.getText(),
                txtName.getText(),
                Integer.parseInt(txtAge.getText()),
                txtContact.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                selectGender()

        );
        if (studentBO.add(studentDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "STUDENT REGISTERED").show();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Try Again");
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {

    }

    public void removeOnAction(ActionEvent actionEvent) {
    }

    public void searchOnKeyRelesed(KeyEvent keyEvent) {
    }

    public void initialize() {

        loadProgramId();
        setDisable();

        cmdProgrammId1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtProgramName1,txtDuration1,txtFee1,newValue);
        });

        cmdProgrammId2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtProgramName2,txtDuration2,txtFee2,newValue);
        });

        cmdProgrammId3.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtProgramName3,txtDuration3,txtFee3,newValue);
        });
    }

   /*public void showProgrammeOnTable() throws SQLException, ClassNotFoundException {

        ObservableList<StudentTM> list = studentBO.find();

        colRegNo.setCellValueFactory(new PropertyValueFactory<>("regNo"));
     colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colContactNum.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));


        tblRegister.setItems(list);
    }*/

    private void loadProgramId(){
        List<String> allProgramIds =programBO.getAllProgramIds();
       cmdProgrammId1.getItems().addAll(allProgramIds);
        cmdProgrammId2.getItems().addAll(allProgramIds);
        cmdProgrammId3.getItems().addAll(allProgramIds);
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        if (check2.isSelected()){
            cmdProgrammId2.setDisable(false);
            txtProgramName2.setDisable(false);
            txtDuration2.setDisable(false);
            txtFee2.setDisable(false);
        }else{
            cmdProgrammId2.setDisable(true);
            txtProgramName2.setDisable(true);
            txtDuration2.setDisable(true);
            txtFee2.setDisable(true);
        }

        if (check3.isSelected()){
            cmdProgrammId3.setDisable(false);
            txtProgramName3.setDisable(false);
            txtDuration3.setDisable(false);
            txtFee3.setDisable(false);
        }else{
            cmdProgrammId3.setDisable(true);
            txtProgramName3.setDisable(true);
            txtDuration3.setDisable(true);
            txtFee3.setDisable(true);
        }
    }
    private  void setDisable(){

        cmdProgrammId3.setDisable(true);
        txtProgramName3.setDisable(true);
        txtDuration3.setDisable(true);
        txtFee3.setDisable(true);



        cmdProgrammId2.setDisable(true);
        txtProgramName2.setDisable(true);
        txtDuration2.setDisable(true);
        txtFee2.setDisable(true);

    }
    private void setProgramData(JFXTextField enterProgram, JFXTextField enterDuration, JFXTextField enterFee, String ProgramID) {
            ProgramDTO programDetails = programBO.getProgrsmDetails(ProgramID);

        if (programDetails == null) {
        } else {
            enterProgram.setText(programDetails.getProgramName());
            enterDuration.setText(programDetails.getDuration());
            enterFee.setText(programDetails.getFee() + "");
        }
    }
}