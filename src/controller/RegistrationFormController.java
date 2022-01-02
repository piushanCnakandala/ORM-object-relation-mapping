package controller;

import Util.Validation;
import bo.BOFactory;
import bo.custom.impl.ProgramBOImpl;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOFactory;
import dao.custom.impl.StudentDAOImpl;
import dto.ProgramDTO;
import entity.Student;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import view.tm.StudentTM;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class RegistrationFormController {
    private final ProgramBOImpl programBO = (ProgramBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.PROGRAM);
    public AnchorPane registrationPane;
    public JFXTextField txtRegNumber;
    public JFXTextField txtEmail;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtAge;
    public JFXTextField txtContact;
    public JFXComboBox<String> cmdProgrammId1;
    public JFXTextField txtFee1;
    public JFXTextField txtDuration1;
    public TableView<StudentTM> tblRegister;
    public JFXTextField txtProgramName1;
    public JFXComboBox<String> cmdProgrammId2;
    public JFXComboBox<String> cmdProgrammId3;
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
    public JFXTextField txtSearch;
    public JFXButton btnRegister;
    String cmb1;
    String cmb2;
    String cmb3;
    StudentBOImpl studentBO = (StudentBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.STUDENT);
    StudentDAOImpl studentDAO = (StudentDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern studentIdPattern = Pattern.compile("^(R)[-]?[0-9]{3}$");
    Pattern studentNamePattern = Pattern.compile("^[A-z ]{1,30}$");
    Pattern studentAddressPattern = Pattern.compile("^[A-z0-9/]{6,30}$");
    Pattern studentTeleNumberPattern = Pattern.compile("^[0-9]{10}$");
    Pattern studentAgePattern = Pattern.compile("^[0-9]{2}$");
    Pattern studentEmailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");



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

        Student student1 = new Student();
        student1.setRegNo(txtRegNumber.getText());
        student1.setName(txtName.getText());
        student1.setAge(Integer.parseInt(txtAge.getText()));
        student1.setContactNo(txtContact.getText());
        student1.setAddress(txtAddress.getText());
        student1.setEmail(txtEmail.getText());
        student1.setGender(selectGender());


        if (studentDAO.register(student1, cmb1, cmb2, cmb3)) {
            new Alert(Alert.AlertType.CONFIRMATION, "STUDENT REGISTERED").show();
            try {
                showDeatilsOnTable();
                clear();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Try Again");
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
if (studentDAO.update(txtRegNumber.getText(),cmb1)){
    new Alert(Alert.AlertType.CONFIRMATION,"PROGRAMME ADDED..");
    clear();
}else {
    new Alert(Alert.AlertType.WARNING,"Try again.....");
}
    }

    public void removeOnAction(ActionEvent actionEvent) {

        StudentTM selectedItem = tblRegister.getSelectionModel().getSelectedItem();
        String studentId = selectedItem.getRegNo();


        if (studentBO.delete(studentId)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            try {
                showDeatilsOnTable();
                clear();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void searchOnKeyRelesed(KeyEvent keyEvent) {
        ObservableList<StudentTM> search =studentBO.search(txtSearch.getText());
        tblRegister.setItems(search);
    }

    public void initialize() {

        loadProgramId();
        setDisable();
        storeValidations();
        try {
            showDeatilsOnTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmdProgrammId1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtProgramName1, txtDuration1, txtFee1, newValue);
            cmb1 = newValue;
        });

        cmdProgrammId2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtProgramName2, txtDuration2, txtFee2, newValue);
            cmb2 = newValue;
        });

        cmdProgrammId3.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtProgramName3, txtDuration3, txtFee3, newValue);
            cmb3 = newValue;
        });
    }

    public void showDeatilsOnTable() throws SQLException, ClassNotFoundException {

        ObservableList<StudentTM> list = studentBO.find();

        colRegNo.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colContactNum.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));


        tblRegister.setItems(list);
    }

    private void loadProgramId() {
        List<String> allProgramIds = programBO.getAllProgramIds();
        cmdProgrammId1.getItems().addAll(allProgramIds);
        cmdProgrammId2.getItems().addAll(allProgramIds);
        cmdProgrammId3.getItems().addAll(allProgramIds);
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        if (check2.isSelected()) {
            cmdProgrammId2.setDisable(false);
            txtProgramName2.setDisable(false);
            txtDuration2.setDisable(false);
            txtFee2.setDisable(false);
        } else {
            cmdProgrammId2.setDisable(true);
            txtProgramName2.setDisable(true);
            txtDuration2.setDisable(true);
            txtFee2.setDisable(true);
        }

        if (check3.isSelected()) {
            cmdProgrammId3.setDisable(false);
            txtProgramName3.setDisable(false);
            txtDuration3.setDisable(false);
            txtFee3.setDisable(false);
        } else {
            cmdProgrammId3.setDisable(true);
            txtProgramName3.setDisable(true);
            txtDuration3.setDisable(true);
            txtFee3.setDisable(true);
        }
    }

    private void setDisable() {

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

    public void tblOnMouseClick(MouseEvent mouseEvent) {
        try {
            StudentTM selectedItem = tblRegister.getSelectionModel().getSelectedItem();
            txtRegNumber.setText(selectedItem.getRegNo());
            txtName.setText(selectedItem.getName());
            txtAge.setText(String.valueOf(selectedItem.getAge()));
            txtAddress.setText(selectedItem.getAddress());
            txtEmail.setText(selectedItem.getEmail());
            txtContact.setText(selectedItem.getContactNo());

            if (selectedItem.getGender().equals("Male")) {
                genderMale.setSelected(true);
            } else if (selectedItem.getGender().equals("female")) {
                genderFemale.setSelected(true);
            }

        } catch (Exception e) {

        }
    }

    public void clearOnAction(ActionEvent actionEvent) {


    }

    public void clear(){
        txtContact.clear();
        txtEmail.clear();
        txtAddress.clear();
        txtSearch.clear();
        txtAge.clear();
        txtName.clear();
        txtRegNumber.clear();
        txtProgramName2.clear();
        txtDuration1.clear();
        txtDuration2.clear();
        txtDuration3.clear();
        txtProgramName1.clear();
        txtProgramName3.clear();
        txtFee2.clear();
        txtFee3.clear();
        txtFee1.clear();
        cmdProgrammId1.setValue("");
        cmdProgrammId2.setValue("");
        cmdProgrammId3.setValue("");

    }

    private void storeValidations() {
        map.put(txtRegNumber,studentIdPattern);
        map.put(txtName,studentNamePattern);
        map.put(txtAge,studentAgePattern);
        map.put(txtContact,studentTeleNumberPattern );
        map.put(txtAddress,studentAddressPattern );
        map.put(txtEmail,studentEmailPattern);
    }

    public void OnKeyReleased(KeyEvent keyEvent) {
       btnRegister.setDisable(true);
        Object response = Validation.validate(map,btnRegister,"Green");
        if (keyEvent.getCode()== KeyCode.ENTER) {
            if (response instanceof TextField){
                TextField error  = (TextField) response;
                error.requestFocus();
            }else if (response instanceof Boolean){
                new Alert(Alert.AlertType.CONFIRMATION, "Done").show();
            }
        }
    }
}