package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashBoardformController {
    public AnchorPane allLodePane;

    public void registrationOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/RegistrationForm.fxml");
        Parent load = FXMLLoader.load(resource);
        allLodePane.getChildren().clear();
        allLodePane.getChildren().add(load);
    }

    public void programsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ProgramsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        allLodePane.getChildren().clear();
        allLodePane.getChildren().add(load);
    }

    public void studentManageOnActiom(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageStudentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        allLodePane.getChildren().clear();
        allLodePane.getChildren().add(load);
    }

    public void exitOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) allLodePane.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
    }
}
