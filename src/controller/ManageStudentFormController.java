package controller;

import bo.BOFactory;
import bo.custom.impl.ProgramBOImpl;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import view.tm.ProgramTM;
import view.tm.StudentTM;

import java.sql.SQLException;

public class ManageStudentFormController {
    public AnchorPane studentPane;
    public TableView<StudentTM> tblStudentDeatils;
    public TableView<ProgramTM> tblProgramDeatils;
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
    public JFXTextField txtSearch;


    StudentBOImpl studentBO = (StudentBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.STUDENT);
    ProgramBOImpl programBO = (ProgramBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.PROGRAM);
    public void serachOnKeyReleased(KeyEvent keyEvent) {
        ObservableList<StudentTM> search =studentBO.search(txtSearch.getText());
        tblStudentDeatils.setItems(search);
    }

    public void showDeatilsOnTable() throws SQLException, ClassNotFoundException {

        ObservableList<StudentTM> list = studentBO.find();

        colRegNo.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        colRegName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colconnectNumber.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));


        tblStudentDeatils.setItems(list);
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        StudentTM selectedItem = tblStudentDeatils.getSelectionModel().getSelectedItem();
        String regNumber = selectedItem.getRegNo();
        ObservableList<ProgramTM> studentProgram = programBO.findStudentProgram(regNumber);
        tblProgramDeatils.setItems(studentProgram);

        colPId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgramme.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

    }
    public void initialize() {
        try {
            showDeatilsOnTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
