package controller;

import Util.Validation;
import bo.BOFactory;
import bo.custom.impl.ProgramBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import view.tm.ProgramTM;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

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
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnRemove;
    public JFXButton btnCleaar;


    ProgramBOImpl programBO = (ProgramBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.PROGRAM);
    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern courserIdPattern = Pattern.compile("^(C)[-]?[0-9]{3}$");
    Pattern courserNamePattern = Pattern.compile("^[A-z ]{1,30}$");
    Pattern courserDurationPattern = Pattern.compile("^[A-z 0-9 ]{1,10}$");
    Pattern courserFeePattern = Pattern.compile("^[0-9 ]{1,30}$");

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
            storeValidations();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void storeValidations() {
        map.put(txtProgramId,courserIdPattern);
        map.put(txtProgramName,courserNamePattern);
        map.put(programDuration,courserDurationPattern);
        map.put(txtFee,courserFeePattern);
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

    public void onKeyReleased(KeyEvent keyEvent) {
        btnAdd.setDisable(true);
        Object response = Validation.validate(map,btnAdd,"Green");
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
