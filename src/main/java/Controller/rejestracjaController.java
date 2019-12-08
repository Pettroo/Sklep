package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class rejestracjaController extends GoTo {

    @FXML
    private PasswordField password1;

    @FXML
    private TextField nazwaU;

    @FXML
    private PasswordField password2;

    @FXML
    private TextField imie;

    @FXML
    private TextField nazwisko;


    public void back(ActionEvent actionEvent) {
        goTo(actionEvent, "/FXML/logowanie.fxml");
    }

    public void utworz(ActionEvent actionEvent) {

        if (nazwaU.getText().equals("") || password1.getText().equals("") || password2.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("");
            alert.setContentText("Puste pola login lub hasło");
            alert.showAndWait();
        } else if (!password1.getText().equals(password2.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("");
            alert.setContentText("Hasła muszą być identyczne");
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
