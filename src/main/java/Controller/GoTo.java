package Controller;

import entity.Produkty;
import entity.Zamowienia;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GoTo {
    public void goTo(ActionEvent actionEvent, String adres)  {
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

    public void goToZamowieniaFromPracownik(ActionEvent actionEvent, String adres, Zamowienia z,pracownikController con)  {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(adres));

        zamowienieController controler=new zamowienieController(z,con);

        loader.setController(controler);


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
