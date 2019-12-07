package Controller;

import entity.Produkty;
import entity.Zamowienia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class uzytkownikController extends GoTo {

    @FXML
    TableView T_produkty;
    @FXML
    TableView T_koszyk;
    @FXML
    TableView T_zamowienia;


    public void wyloguj(ActionEvent actionEvent) {
        goTo(actionEvent, "/FXML/logowanie.fxml");

    }


    public void initialize() {
        TableColumn name = new TableColumn("Nazwa");
        TableColumn cena = new TableColumn("Cena");

        TableColumn do_koszyka = new TableColumn("Dodaj do koszyka");


        TableColumn name2 = new TableColumn("Nazwa");
        TableColumn cena2 = new TableColumn("Cena");
        TableColumn rozmiar2 = new TableColumn("Rozmiar");
        TableColumn ilosc2 = new TableColumn("Ilośc");
        TableColumn zaznacz2 = new TableColumn("Zaznacz");

        T_produkty.getColumns().addAll(name, cena, do_koszyka);
        T_koszyk.getColumns().addAll(name2, cena2, rozmiar2, ilosc2, zaznacz2);

        final ObservableList<Produkty> data = FXCollections.observableArrayList(
                new Produkty("Adidasy", 23.50),
                new Produkty("Sandały", 70.60),
                new Produkty("Kozaki", 230.99)
        );
        final ObservableList<Produkty> data2 = FXCollections.observableArrayList(
                new Produkty("Adidasy", 23.50),
                new Produkty("Sandały", 70.60),
                new Produkty("Kozaki", 230.99)
        );
        name.setCellValueFactory(
                new PropertyValueFactory<Produkty, String>("nazwa")
        );

        cena.setCellValueFactory(
                new PropertyValueFactory<Produkty, String>("cena")
        );



        do_koszyka.setCellValueFactory(
                new PropertyValueFactory<Produkty, Button>("koszyk")
        );

        name2.setCellValueFactory(
                new PropertyValueFactory<Produkty, String>("nazwa")
        );

        cena2.setCellValueFactory(
                new PropertyValueFactory<Produkty, String>("cena")
        );
        rozmiar2.setCellValueFactory(
                new PropertyValueFactory<Produkty, ComboBox>("rozmiar")
        );
        ilosc2.setCellValueFactory(
                new PropertyValueFactory<Produkty, Spinner>("ilosc")
        );


        zaznacz2.setCellValueFactory(
                new PropertyValueFactory<Produkty, CheckBox>("zaznacz")
        );

        T_produkty.setItems(data);
        T_koszyk.setItems(data2);



        TableColumn numer = new TableColumn("Numer zamówienia");
        TableColumn datA = new TableColumn("Data");
        TableColumn status = new TableColumn("Status");




        T_zamowienia.getColumns().addAll(numer, datA, status);
        numer.setCellValueFactory(
                new PropertyValueFactory<Zamowienia, String>("numerZ")
        );
        datA.setCellValueFactory(
                new PropertyValueFactory<Zamowienia, String>("data")
        );


        status.setCellValueFactory(
                new PropertyValueFactory<Zamowienia, String>("status")
        );

        final ObservableList<Zamowienia> data3 = FXCollections.observableArrayList(
                new Zamowienia(1, new Date(2011,2,14),"Zrealizowane"),
                new Zamowienia(2, new Date(2014,2,4),"Anulowane"),
                new Zamowienia(3, new Date(2016,6,28),"W trakcie")
        );

        T_zamowienia.setItems(data3);


    }

    public void zamow(ActionEvent actionEvent) {
      //  Produkty p = (Produkty) T_koszyk.getSelectionModel().getSelectedItem();
     //  System.out.println(p.getCena());

    }

    public void usun(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Usuwanie");
        alert.setContentText("Usuwanie");

        alert.showAndWait();
    }

    public void szczegoly(ActionEvent actionEvent) {
//
//        Zamowienia z=(Zamowienia) T_zamowienia.getSelectionModel().getSelectedItem();
//        if(z!=null)
//        goToZamowienia(actionEvent,"/FXML/zamowienie.fxml",z);
    }
}
