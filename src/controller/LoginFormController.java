package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginFormController {
    public AnchorPane loginPane;

    public void btnlogonAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/Dashboardform.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) loginPane.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
    }
}
