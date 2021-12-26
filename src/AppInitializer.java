import Util.FactoryConfiguration;
import entity.Student;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
       primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/LogInForm.fxml"))));
        primaryStage.setTitle("Sipsewana institute ");
        primaryStage.centerOnScreen();
        primaryStage.show();


    }
}
