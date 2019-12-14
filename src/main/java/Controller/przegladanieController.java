package Controller;

import entity.Shoes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.sql.SQLException;

public class przegladanieController extends GoTo {

    @FXML
    private TableView T_produkty;
    przegladanieRepo rep = new przegladanieRepo();

    public void initialize() throws SQLException {

        TableColumn name = new TableColumn("Nazwa");
        TableColumn cena = new TableColumn("Cena");

        name.setCellValueFactory(new PropertyValueFactory<Shoes, String>("nazwa"));
        cena.setCellValueFactory(new PropertyValueFactory<Shoes, String>("cena"));
        T_produkty.getColumns().addAll(name, cena);

        ObservableList<Shoes> data = FXCollections.observableArrayList();
        data.addAll(rep.getAllShoes());
        T_produkty.setItems(data);

    }

    public void back(ActionEvent actionEvent) {
        goTo(actionEvent, "/FXML/logowanie.fxml");

    }

}
