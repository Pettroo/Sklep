package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class rejestracjaController extends GoTo {

    @FXML
    private TextField nazwaU;


    public void back(ActionEvent actionEvent) {
        goTo(actionEvent, "/FXML/logowanie.fxml");
    }

    public void utworz(ActionEvent actionEvent) {

        if (nazwaU.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("");
            alert.setContentText("Puste pola login lub hasło");

            alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Utworzenie konta");
            alert.setContentText("Konto: " + nazwaU.getText() + "  zostało utworzone");

            alert.showAndWait();
            goTo(actionEvent, "/FXML/logowanie.fxml");
        }
    }
}
