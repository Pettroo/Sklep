package Controller;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

        //Tabela pracowników
        TableColumn imie = new TableColumn("Imię");
        TableColumn nazwisko = new TableColumn("Nazwisko");
        TableColumn login = new TableColumn("Login");
        TableColumn uprawnienia_actual = new TableColumn("Uprawnienia");
        TableColumn uprawnienia_change = new TableColumn("Zmień uprawnienia");
        imie.setCellValueFactory(new PropertyValueFactory<Users, String>("imie"));
        nazwisko.setSortType(TableColumn.SortType.ASCENDING);
        imie.setSortType(TableColumn.SortType.ASCENDING);

        nazwisko.setCellValueFactory(new PropertyValueFactory<Users, String>("nazwisko"));
        login.setCellValueFactory(new PropertyValueFactory<Users, String>("login"));
        uprawnienia_actual.setCellValueFactory(new PropertyValueFactory<Users, String>("rolaDisp"));
        uprawnienia_change.setCellValueFactory(new PropertyValueFactory<Users, ComboBox>("uprawnienia"));
        T_pracownicy.getColumns().addAll(imie, nazwisko, login, uprawnienia_actual, uprawnienia_change);
        T_pracownicy.getSortOrder().setAll(nazwisko,imie);

        //Tabela modyfikacji
        TableColumn name = new TableColumn("Nazwa");
        TableColumn cena = new TableColumn("Cena");
        name.setMinWidth(200);
        name.setSortType(TableColumn.SortType.ASCENDING);

        name.setCellValueFactory(new PropertyValueFactory<Shoes, String>("nazwa"));
        cena.setCellValueFactory(new PropertyValueFactory<Shoes, String>("cena"));
        T_modyfikacja.getSortOrder().setAll(name);

        T_modyfikacja.getColumns().addAll(name, cena);

        //Tabela zmiany dostępności
        TableColumn name3 = new TableColumn("Nazwa");
        TableColumn cena3 = new TableColumn("Cena");
        TableColumn rozmiar = new TableColumn("Rozmiar");
        TableColumn status_actual = new TableColumn("Status");
        TableColumn status_new = new TableColumn("Zemień status");
        name3.setMinWidth(200);
        status_new.setMinWidth(200);
        name3.setSortType(TableColumn.SortType.ASCENDING);
        rozmiar.setSortType(TableColumn.SortType.ASCENDING);

        name3.setCellValueFactory(new PropertyValueFactory<Products, String>("name"));
        cena3.setCellValueFactory(new PropertyValueFactory<Products, String>("value"));
        rozmiar.setCellValueFactory(new PropertyValueFactory<Products, String>("size"));
        status_actual.setCellValueFactory(new PropertyValueFactory<Products, String>("status_disp"));
        status_new.setCellValueFactory(new PropertyValueFactory<Products, ComboBox>("status_zmien"));
        T_dostepmosc.getSortOrder().setAll(name3,rozmiar);
        T_dostepmosc.getColumns().addAll(name3, cena3, rozmiar, status_actual, status_new);

    }

    public void zapisz_dostepnosc(ActionEvent actionEvent) {
        for (Products p : (List<Products>) T_dostepmosc.getItems()) {

            if (p.getStatus_zmien().getSelectionModel().getSelectedItem() != null) {
                String selected = (String) p.getStatus_zmien().getSelectionModel().getSelectedItem();
                if (!selected.equals(p.getStatus_disp())) {
                    repo.setProductAvilable(p.getId());
                }
            }

        }
        tab_dostepnosc(actionEvent);
    }

    public void zapisz_modyfikacja(ActionEvent actionEvent) {
        Shoes p = (Shoes) T_modyfikacja.getSelectionModel().getSelectedItem();
        if (p != null) {
            repo.setShoeName(p.getId(), nazwa_modyfikacja.getText());
            repo.setShoeValue(p.getId(), parseDouble(cena_modyfikacja.getText()));

        }
        tab_modyfikacja(actionEvent);

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
        alert.setTitle("Dodano nowy produkt");
        alert.setContentText(nazwa_input.getText() + " " + parseDouble(cena_input.getText()));
        alert.showAndWait();
    }

    public void zapisz_uprawnienia(ActionEvent actionEvent) {

        for (Users u : (List<Users>) T_pracownicy.getItems()) {

            if (u.getUprawnienia().getSelectionModel().getSelectedItem() != null) {
                String selected = (String) u.getUprawnienia().getSelectionModel().getSelectedItem();
                if (!u.getUprawnienia().getSelectionModel().getSelectedItem().equals(u.getRolaDisp())) {
                    if (selected.equals("Menadżer")) repo.setUserRole(u.getId(), "MANAGER");

                    if (selected.equals("Użytkownik")) repo.setUserRole(u.getId(), "USER");

                    if (selected.equals("Pracownik")) repo.setUserRole(u.getId(), "EMPLOYE");

                }

            }

        }
        tab_pracownicy(actionEvent);

    }

    public void tab_modyfikacja(Event event) {
        ObservableList<Shoes> data = FXCollections.observableArrayList();
        data.addAll(repo.getAllShoes());
        SortedList<Shoes> sortedData = new SortedList<>(data);
        sortedData.comparatorProperty().bind(T_modyfikacja.comparatorProperty());
        T_modyfikacja.setItems(sortedData);
    }

    public void tab_dostepnosc(Event event) {
        ObservableList<Products> data = FXCollections.observableArrayList();
        data.addAll(repo.getAllProducts());
        SortedList<Products> sortedData = new SortedList<>(data);
        sortedData.comparatorProperty().bind(T_dostepmosc.comparatorProperty());
        T_dostepmosc.setItems(sortedData);

    }

    public void tab_pracownicy(Event event) {
        ObservableList<Users> data = FXCollections.observableArrayList();
        data.addAll(repo.getAllUsers());
        SortedList<Users> sortedData = new SortedList<>(data);
        sortedData.comparatorProperty().bind(T_pracownicy.comparatorProperty());
        T_pracownicy.setItems(sortedData);

    }

    public void wyloguj(ActionEvent actionEvent) {
        goTo(actionEvent, "/FXML/logowanie.fxml");
    }

    public void setLogin(String login) {
        this.login = login;
    }
}