package Controller;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

import static java.lang.Double.parseDouble;

public class managerController extends GoTo {

    private String login;

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

    Configuration conf=new Configuration().configure();
    SessionFactory factory=conf.buildSessionFactory();
    Session s=factory.openSession();

    public void wyloguj(ActionEvent actionEvent) {
        goTo(actionEvent, "/FXML/logowanie.fxml");
    }


    public void initialize() {
        System.out.println(login);

     //Tabela pracowników
        TableColumn imie = new TableColumn("Imię");
        TableColumn nazwisko = new TableColumn("Nazwisko");
        TableColumn login = new TableColumn("Login");
        TableColumn uprawnienia_actual = new TableColumn("Uprawnienia");
        TableColumn uprawnienia_change = new TableColumn("Zmień uprawnienia");




        imie.setCellValueFactory(
                new PropertyValueFactory<Users, String>("imie")
        );

        nazwisko.setCellValueFactory(
                new PropertyValueFactory<Users, String>("nazwisko")
        );
        login.setCellValueFactory(
                new PropertyValueFactory<Users, String>("login")
        );

        uprawnienia_actual.setCellValueFactory(
                new PropertyValueFactory<Users, String>("rolaDisp")
        );
        uprawnienia_change.setCellValueFactory(
                new PropertyValueFactory<Users, ComboBox>("uprawnienia")
        );

        T_pracownicy.getColumns().addAll(imie, nazwisko, login, uprawnienia_actual,uprawnienia_change);




     //Tabela modyfikacji


        List<Shoes> l=s.createQuery("SELECT p FROM Shoes p",Shoes.class).getResultList();

        ObservableList<Shoes> data2 = FXCollections.observableArrayList();

        data2.addAll(l);


        TableColumn name = new TableColumn("Nazwa");
        TableColumn cena = new TableColumn("Cena");


        T_modyfikacja.getColumns().addAll(name, cena);


        name.setCellValueFactory(
                new PropertyValueFactory<Shoes, String>("nazwa")
        );

        cena.setCellValueFactory(
                new PropertyValueFactory<Shoes, String>("cena")
        );



        T_modyfikacja.setItems(data2);

    //Tabela zmiany dostępności
        TableColumn name3 = new TableColumn("Nazwa");
        TableColumn cena3 = new TableColumn("Cena");
        TableColumn rozmiar = new TableColumn("Rozmiar");
        TableColumn status_actual = new TableColumn("Status");
        TableColumn status_new = new TableColumn("Zemień status");


        name3.setCellValueFactory(
                new PropertyValueFactory<Products, String>("name")
        );

        cena3.setCellValueFactory(
                new PropertyValueFactory<Products, String>("value")
        );


        rozmiar.setCellValueFactory(
                new PropertyValueFactory<Products, String>("size")
        );

        status_actual.setCellValueFactory(
                new PropertyValueFactory<Products, String>("status_disp")
        );
        status_new.setCellValueFactory(
                new PropertyValueFactory<Products, ComboBox>("status_zmien")
        );
        T_dostepmosc.getColumns().addAll(name3, cena3, rozmiar,status_actual,status_new);

    }




    public void zapisz_modyfikacja(ActionEvent actionEvent) {

    }

    public void modyfikacja(ActionEvent actionEvent) {
        Shoes p=(Shoes) T_modyfikacja.getSelectionModel().getSelectedItem();
        if(p!=null) {
            nazwa_modyfikacja.setText(p.getNazwa());
            String s=""+p.getCena();
            cena_modyfikacja.setText(s);
        }

    }

    public void dodaj(ActionEvent actionEvent) {
     //   s.beginTransaction();
     //   s.save(new Produkty(20,nazwa_input.getText(),parseDouble(cena_input.getText())));
     //   s.getTransaction().commit();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Dodano nowy produkt");
        alert.setContentText(nazwa_input.getText()+" "+parseDouble(cena_input.getText()));

        alert.showAndWait();


    }

    public void zapisz_uprawnienia(ActionEvent actionEvent) {
    }

    public void tab_modyfikacja(Event event) {
        List<Shoes> l=s.createQuery("SELECT p FROM Shoes p",Shoes.class).getResultList();
        ObservableList<Shoes> data2 = FXCollections.observableArrayList();
        data2.addAll(l);
        T_modyfikacja.setItems(data2);
    }

    public void tab_dostepnosc(Event event) {
        List<Products> l=s.createQuery("SELECT p FROM Products p",Products.class).getResultList();
        ObservableList<Products> data = FXCollections.observableArrayList();
        data.addAll(l);
        T_dostepmosc.setItems(data);

    }
    public void tab_pracownicy(Event event) {
        List<Users> l_u=s.createQuery("SELECT u FROM Users u",Users.class).getResultList();

        ObservableList<Users> data = FXCollections.observableArrayList();

        data.addAll(l_u);
        T_pracownicy.setItems(data);

    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}