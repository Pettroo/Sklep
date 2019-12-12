package Controller;

import entity.Produkty;
import entity.Shoes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class przegladanieController extends GoTo {

    @FXML
    private TableView T_produkty;

    public void initialize() {
        Configuration conf=new Configuration().configure();
        SessionFactory factory=conf.buildSessionFactory();
        Session s=factory.openSession();

        List<Shoes> l=s.createQuery("SELECT s FROM Shoes s", Shoes.class).getResultList();
        ObservableList<Shoes> data = FXCollections.observableArrayList();
        data.addAll(l);


        TableColumn name = new TableColumn("Nazwa");
        TableColumn cena = new TableColumn("Cena");


        T_produkty.getColumns().addAll(name, cena);


        name.setCellValueFactory(
                new PropertyValueFactory<Shoes, String>("nazwa")
        );

        cena.setCellValueFactory(
                new PropertyValueFactory<Shoes, String>("cena")
        );

        T_produkty.setItems(data);

    }

    public void back(ActionEvent actionEvent) {
        goTo(actionEvent,"/FXML/logowanie.fxml");

    }

}
