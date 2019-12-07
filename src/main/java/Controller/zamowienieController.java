package Controller;

import entity.Produkty;
import entity.Zamowienia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class zamowienieController extends GoTo implements Initializable {


    @FXML
    private TextArea text;

    private Zamowienia zamowienie;
    private pracownikController pracownikController;


    public zamowienieController(Zamowienia z,pracownikController con) {
        this.zamowienie = z;
        this.pracownikController=con;



    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       text.setText(zamowienie.toString());


    }


    public void back(ActionEvent actionEvent) {

        FXMLLoader loader = new FXMLLoader();

        loader.setController(pracownikController);



        Pane pane = null;
        try {
            pane =  loader.load(getClass().getResource("/FXML/pracownik.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene actual_scene = new Scene(pane);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(actual_scene);

    }

}
