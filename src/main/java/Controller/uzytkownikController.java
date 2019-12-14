package Controller;

import entity.Orders;
import entity.Produkty;
import entity.Shoes;
import entity.Zamowienia;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.ls.LSOutput;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class uzytkownikController extends GoTo {

    private String login;
    @FXML
    TableView T_produkty;
    @FXML
    TableView T_koszyk;
    @FXML
    TableView T_zamowienia;
    @FXML
    private TextArea text;
    private uzytkownikRepo repo = new uzytkownikRepo();

//
//    final ObservableList<Produkty> data = FXCollections.observableArrayList(
//            new Produkty("Adidasy", 23.50),
//            new Produkty("Sandały", 70.60),
//            new Produkty("Kozaki", 230.99)
//    );
//    final ObservableList<Produkty> data2 = FXCollections.observableArrayList(
//            new Produkty("Adidasy", 23.50),
//            new Produkty("Sandały", 70.60),
//            new Produkty("Kozaki", 230.99)
//    );
//    final ObservableList<Zamowienia> data3 = FXCollections.observableArrayList(
//            new Zamowienia(1, new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "Zrealizowane"),
//            new Zamowienia(2, new GregorianCalendar(2016, Calendar.JANUARY, 20).getTime(), "Anulowane"),
//            new Zamowienia(3, new GregorianCalendar(2018, Calendar.JUNE, 1).getTime(), "W trakcie")
//    );

    public void initialize() {
        System.out.println(login);

        //table produkty
        TableColumn name = new TableColumn("Nazwa");
        TableColumn cena = new TableColumn("Cena");
        TableColumn do_koszyka = new TableColumn("Dodaj do koszyka");
        name.setCellValueFactory(new PropertyValueFactory<Shoes, String>("nazwa"));
        cena.setCellValueFactory(new PropertyValueFactory<Shoes, Double>("cena"));
        do_koszyka.setCellValueFactory(new PropertyValueFactory<Shoes, Button>("koszyk"));
        T_produkty.getColumns().addAll(name, cena, do_koszyka);


        //table koszyk
        TableColumn name2 = new TableColumn("Nazwa");
        TableColumn cena2 = new TableColumn("Cena");
        TableColumn rozmiar2 = new TableColumn("Rozmiar");
        TableColumn ilosc2 = new TableColumn("Ilośc");
        TableColumn zaznacz2 = new TableColumn("Zaznacz");
        name2.setCellValueFactory(new PropertyValueFactory<Shoes, String>("nazwa"));
        cena2.setCellValueFactory(new PropertyValueFactory<Shoes, String>("cena"));
        rozmiar2.setCellValueFactory(new PropertyValueFactory<Shoes, ComboBox>("rozmiar"));
        ilosc2.setCellValueFactory(new PropertyValueFactory<Shoes, Spinner>("ilosc"));
        zaznacz2.setCellValueFactory(new PropertyValueFactory<Shoes, CheckBox>("zaznacz"));
        T_koszyk.getColumns().addAll(name2, cena2, rozmiar2, ilosc2, zaznacz2);

        //table zamowienia
        TableColumn numer = new TableColumn("Numer zamówienia");
        TableColumn datA = new TableColumn("Data");
        TableColumn status = new TableColumn("Status");
        numer.setCellValueFactory(new PropertyValueFactory<Orders, String>("id"));
        datA.setCellValueFactory(new PropertyValueFactory<Orders, String>("data"));
        status.setCellValueFactory(new PropertyValueFactory<Orders, String>("status"));
        T_zamowienia.getColumns().addAll(numer, datA, status);

    }

    public void zamow(ActionEvent actionEvent) {
        //  Produkty p = (Produkty) T_koszyk.getSelectionModel().getSelectedItem();
        //  System.out.println(p.getCena());

    }

    public void usun(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Usuwanie");
        alert.setContentText("Wybrane artykóły zostały usunięte");
        alert.showAndWait();
    }

    public void szczegoly(ActionEvent actionEvent) {
        Orders z = (Orders) T_zamowienia.getSelectionModel().getSelectedItem();
        if (z != null) text.setText(z.toString());
    }

    public void wyloguj(ActionEvent actionEvent) {
        goTo(actionEvent, "/FXML/logowanie.fxml");

    }

    public void tab_oferta(Event event) {
        ObservableList<Shoes> data = FXCollections.observableArrayList();
        data.addAll(repo.getAllShoes());
        T_produkty.setItems(data);
    }

    public void tab_koszyk(Event event) {
        ObservableList<Shoes> data = FXCollections.observableArrayList();
        data.addAll(repo.getAllShoes());
        T_koszyk.setItems(data);
    }

    public void tab_zamowienia(Event event) {
        ObservableList<Orders> data = FXCollections.observableArrayList();
        data.addAll(repo.getAllOrders());
        T_zamowienia.setItems(data);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
