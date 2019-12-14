package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GoTo {
    public void goTo(ActionEvent actionEvent, String adres) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(adres));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene actual_scene = new Scene(pane);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(actual_scene);
    }

    public void goToUzywkownicy(ActionEvent actionEvent, String adres, String name) {
        uzytkownikController c = new uzytkownikController();
        c.setLogin(name);

        FXMLLoader loader = new FXMLLoader();
        loader.setController(c);
        loader.setLocation(this.getClass().getResource(adres));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene actual_scene = new Scene(pane);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(actual_scene);

    }

    public void goToPracownik(ActionEvent actionEvent, String adres, String name) {
        pracownikController c = new pracownikController();
        c.setLogin(name);

        FXMLLoader loader = new FXMLLoader();
        loader.setController(c);
        loader.setLocation(this.getClass().getResource(adres));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene actual_scene = new Scene(pane);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(actual_scene);

    }
    public void goToManager(ActionEvent actionEvent, String adres, String name) {
        managerController c = new managerController();
        c.setLogin(name);

        FXMLLoader loader = new FXMLLoader();
        loader.setController(c);
        loader.setLocation(this.getClass().getResource(adres));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene actual_scene = new Scene(pane);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(actual_scene);

    }

}
