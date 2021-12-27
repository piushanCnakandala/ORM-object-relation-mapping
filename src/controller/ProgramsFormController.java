package controller;

import bo.BOFactory;
import bo.custom.impl.ProgramBOImpl;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import view.tm.ProgramTM;

import java.sql.SQLException;

public class ProgramsFormController {
    public AnchorPane programsPane;
    public JFXTextField txtProgramId;
    public JFXTextField txtProgramName;
    public JFXTextField programDuration;
    public JFXTextField txtFee;
    public TableView<ProgramTM> tblPrograms;
    public TableColumn colProgramId;
    public TableColumn colProgram;
    public TableColumn colDuration;
    public TableColumn colFee;
    public JFXTextField txtSearch;


    ProgramBOImpl programBO = (ProgramBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.PROGRAM);

    public void registerOnAction(ActionEvent actionEvent) {
        ProgramDTO programDTO = new ProgramDTO(
                txtProgramId.getText(),
                txtProgramName.getText(),
                programDuration.getText(),
                Double.parseDouble(txtFee.getText())
        );
        if (programBO.add(programDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "PROGRAMME ADDED").show();
            try {
               showProgrammeOnTable();
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
        ProgramTM selectedItem = tblPrograms.getSelectionModel().getSelectedItem();
        String programId = selectedItem.getProgramId();

        ProgramDTO program = new ProgramDTO(
                txtProgramId.getText(),
                txtProgramName.getText(),
               programDuration.getText(),
                Double.parseDouble(txtFee.getText())
        );
        if (programBO.update(program)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Program Updated").show();
            try {
              showProgrammeOnTable();
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

    public void removeOnAction(ActionEvent actionEvent) {
        ProgramTM selectedItem = tblPrograms.getSelectionModel().getSelectedItem();
        String programId = selectedItem.getProgramId();

        if (programBO.delete(programId)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Programme is removed").show();

            try {
              showProgrammeOnTable();
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

    public void clearOnAction(ActionEvent actionEvent) {
       clear();
    }

    public void clear(){
        txtProgramId.clear();
        txtFee.clear();
        txtProgramName.clear();
        programDuration.clear();
    }

    public void searchOnKeyReleased(KeyEvent keyEvent) {
        ObservableList<ProgramTM>search=programBO.search(txtSearch.getText());
        tblPrograms.setItems(search);
    }

    public void showProgrammeOnTable() throws SQLException, ClassNotFoundException {

        ObservableList<ProgramTM> list = programBO.find();

        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        tblPrograms.setItems(list);
    }

    public void initialize() {
        try {
            showProgrammeOnTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void tableOnMouseClicked(MouseEvent mouseEvent) {
        try {
            ProgramTM selectedItem = tblPrograms.getSelectionModel().getSelectedItem();
            txtProgramId.setText(selectedItem.getProgramId());
            txtProgramName.setText(selectedItem.getProgramName());
            programDuration.setText(selectedItem.getDuration());
            txtFee.setText(String.valueOf(selectedItem.getFee()));
        }catch (Exception e) {

        }

    }
}
