package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class logowanieController extends GoTo {

    @FXML
    private TextField nazwaU;

    @FXML
    private PasswordField password;

    @FXML
    public void initialize() {

    }

    public void zaloguj(ActionEvent actionEvent) {
        if (nazwaU.getText().equals("") || password.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("");
            alert.setContentText("Puste pola login lub hasło");

            alert.showAndWait();
        }

        if (nazwaU.getText().equals("a") && password.getText().equals("a")) goToUzywkownicy(actionEvent, "/FXML/uzytkownik.fxml","a");
        if (nazwaU.getText().equals("b") && password.getText().equals("b")) goTo(actionEvent, "/FXML/pracownik.fxml");
        if (nazwaU.getText().equals("c") && password.getText().equals("c")) goTo(actionEvent, "/FXML/manager.fxml");


    }

    public void rejestracja(ActionEvent actionEvent) {
        goTo(actionEvent, "/FXML/rejestracja.fxml");
    }

    public void przegladanie(ActionEvent actionEvent) {
        goTo(actionEvent, "/FXML/przegladanie.fxml");
    }
}
