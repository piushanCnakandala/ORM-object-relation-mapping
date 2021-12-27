package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class ProgramsFormController {
    public AnchorPane programsPane;
    public JFXTextField txtProgramId;
    public JFXTextField txtProgramName;
    public JFXTextField programDuration;
    public JFXTextField txtFee;
    public TableView tblPrograms;
    public TableColumn colProgramId;
    public TableColumn colProgram;
    public TableColumn colDuration;
    public TableColumn colFee;

    public void registerOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void removeOnAction(ActionEvent actionEvent) {
    }

    public void clearOnAction(ActionEvent actionEvent) {
    }

    public void searchOnKeyReleased(KeyEvent keyEvent) {
    }
}
