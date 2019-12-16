package Controller;

import entity.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class logowanieController extends GoTo {
    private logowanieRepo repo = new logowanieRepo();

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
           return;
        }
        for (Users u : repo.getAllUsers()) {
            if (nazwaU.getText().equals(u.getLogin()) && password.getText().equals(u.getHaslo())) {
                Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                app_stage.setTitle(nazwaU.getText());
                if (u.getRola().getKod().equals("MANAGER"))
                    goToManager(actionEvent, "/FXML/manager.fxml", u.getLogin());
                if (u.getRola().getKod().equals("USER"))
                    goToUzywkownicy(actionEvent, "/FXML/uzytkownik.fxml", u.getLogin());
                if (u.getRola().getKod().equals("EMPLOYE"))
                    goToPracownik(actionEvent, "/FXML/pracownik.fxml", u.getLogin());
            }
        }
        if (nazwaU.getText().equals("a") && password.getText().equals("a"))
            goToUzywkownicy(actionEvent, "/FXML/uzytkownik.fxml", "a");
        if (nazwaU.getText().equals("b") && password.getText().equals("b"))
            goToPracownik(actionEvent, "/FXML/pracownik.fxml", "b");
        if (nazwaU.getText().equals("c") && password.getText().equals("c"))
            goToManager(actionEvent, "/FXML/manager.fxml", "c");
    }

    public void rejestracja(ActionEvent actionEvent) {
        goTo(actionEvent, "/FXML/rejestracja.fxml");
    }

    public void przegladanie(ActionEvent actionEvent) {
        goTo(actionEvent, "/FXML/przegladanie.fxml");
    }
}
