package Controller;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static java.lang.Double.parseDouble;

public class managerController extends GoTo {

    private String login;
    private managerRepo repo = new managerRepo();
    @FXML
    private TableView T_pracownicy;
    @FXML
    private TableView T_modyfikacja;
    @FXML
    private TableView T_dostepmosc;
    @FXML
    private TextField nazwa_input;
    @FXML
    private TextField cena_input;
    @FXML
    private TextField nazwa_modyfikacja;
    @FXML
    private TextField cena_modyfikacja;


    public void initialize() {
        System.out.println(login);

        //Tabela pracowników
        TableColumn imie = new TableColumn("Imię");
        TableColumn nazwisko = new TableColumn("Nazwisko");
        TableColumn login = new TableColumn("Login");
        TableColumn uprawnienia_actual = new TableColumn("Uprawnienia");
        TableColumn uprawnienia_change = new TableColumn("Zmień uprawnienia");
        imie.setCellValueFactory(new PropertyValueFactory<Users, String>("imie"));

        nazwisko.setCellValueFactory(new PropertyValueFactory<Users, String>("nazwisko"));
        login.setCellValueFactory(new PropertyValueFactory<Users, String>("login"));
        uprawnienia_actual.setCellValueFactory(new PropertyValueFactory<Users, String>("rolaDisp"));
        uprawnienia_change.setCellValueFactory(new PropertyValueFactory<Users, ComboBox>("uprawnienia"));
        T_pracownicy.getColumns().addAll(imie, nazwisko, login, uprawnienia_actual, uprawnienia_change);

        //Tabela modyfikacji
        TableColumn name = new TableColumn("Nazwa");
        TableColumn cena = new TableColumn("Cena");

        name.setCellValueFactory(new PropertyValueFactory<Shoes, String>("nazwa"));
        cena.setCellValueFactory(new PropertyValueFactory<Shoes, String>("cena"));
        T_modyfikacja.getColumns().addAll(name, cena);

        //Tabela zmiany dostępności
        TableColumn name3 = new TableColumn("Nazwa");
        TableColumn cena3 = new TableColumn("Cena");
        TableColumn rozmiar = new TableColumn("Rozmiar");
        TableColumn status_actual = new TableColumn("Status");
        TableColumn status_new = new TableColumn("Zemień status");

        name3.setCellValueFactory(new PropertyValueFactory<Products, String>("name"));
        cena3.setCellValueFactory(new PropertyValueFactory<Products, String>("value"));
        rozmiar.setCellValueFactory(new PropertyValueFactory<Products, String>("size"));
        status_actual.setCellValueFactory(new PropertyValueFactory<Products, String>("status_disp"));
        status_new.setCellValueFactory(new PropertyValueFactory<Products, ComboBox>("status_zmien"));
        T_dostepmosc.getColumns().addAll(name3, cena3, rozmiar, status_actual, status_new);

    }


    public void zapisz_modyfikacja(ActionEvent actionEvent) {

    }

    public void modyfikacja(ActionEvent actionEvent) {
        Shoes p = (Shoes) T_modyfikacja.getSelectionModel().getSelectedItem();
        if (p != null) {
            nazwa_modyfikacja.setText(p.getNazwa());
            String s = "" + p.getCena();
            cena_modyfikacja.setText(s);
        }

    }

    public void dodaj(ActionEvent actionEvent) {
        repo.addShoe(nazwa_input.getText(), parseDouble(cena_input.getText()));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Dodano nowy produkt:" + nazwa_input.getText());
        alert.setContentText(nazwa_input.getText() + " " + parseDouble(cena_input.getText()));
        alert.showAndWait();
    }

    public void zapisz_uprawnienia(ActionEvent actionEvent) {
    }

    public void tab_modyfikacja(Event event) {
        ObservableList<Shoes> data = FXCollections.observableArrayList();
        data.addAll(repo.getAllShoes());
        T_modyfikacja.setItems(data);
    }

    public void tab_dostepnosc(Event event) {
        ObservableList<Products> data = FXCollections.observableArrayList();
        data.addAll(repo.getAllProducts());
        T_dostepmosc.setItems(data);

    }

    public void tab_pracownicy(Event event) {
        ObservableList<Users> data = FXCollections.observableArrayList();
        data.addAll(repo.getAllUsers());
        T_pracownicy.setItems(data);

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void wyloguj(ActionEvent actionEvent) {
        goTo(actionEvent, "/FXML/logowanie.fxml");
    }

}