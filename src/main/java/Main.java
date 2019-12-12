import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        System.out.println("Start");
        launch(args);
        System.out.println("Stop");

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/FXML/logowanie.fxml"));
        Pane pane = loader.load();

        Scene scene=new Scene(pane);

        stage.setScene(scene);
        stage.setTitle("Sklep Bucior");
        stage.show();
    }
}

