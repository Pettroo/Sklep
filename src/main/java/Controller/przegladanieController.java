package Controller;

import entity.Shoes;
import entity.ShoesRepo;
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

    public void initialize() throws SQLException {
//        EntityManagerFactory fabryka= Persistence.createEntityManagerFactory("Test");
//        EntityManager menadzer=fabryka.createEntityManager();
//        List<Shoes> l=menadzer.createQuery("SELECT s FROM Shoes s", Shoes.class).getResultList();

        ObservableList<Shoes> data = FXCollections.observableArrayList();
        ShoesRepo rep=new ShoesRepo();
        data.addAll(rep.allShoes());


//        Configuration conf=new Configuration().configure();
//        SessionFactory factory=conf.buildSessionFactory();
//        Session s=factory.openSession();
//
//        List<Shoes> l=s.createQuery("SELECT s FROM Shoes s", Shoes.class).getResultList();
//        ObservableList<Shoes> data = FXCollections.observableArrayList();
//        data.addAll(l);


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
