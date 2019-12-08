package Controller;

import entity.Zamowienia;
import entity.pracownicy;
import entity.Produkty;
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
        TableColumn imie = new TableColumn("Imię");
        TableColumn nazwisko = new TableColumn("Nazwisko");
        TableColumn login = new TableColumn("Login");
        TableColumn uprawnienia = new TableColumn("Uprawnienia");


        T_pracownicy.getColumns().addAll(imie, nazwisko, login, uprawnienia);


        final ObservableList<pracownicy> data = FXCollections.observableArrayList(
                new pracownicy("Adam", "Nowak", "Adam123"),
                new pracownicy("Marcin", "Kowalski", "mArCiN"),
                new pracownicy("Stefan", "Górki", "xXStefanXx")
        );

        imie.setCellValueFactory(
                new PropertyValueFactory<Produkty, String>("imie")
        );

        nazwisko.setCellValueFactory(
                new PropertyValueFactory<Produkty, String>("nazwisko")
        );
        login.setCellValueFactory(
                new PropertyValueFactory<Produkty, ComboBox>("login")
        );

        uprawnienia.setCellValueFactory(
                new PropertyValueFactory<Produkty, Button>("uprawnienia")
        );


        T_pracownicy.setItems(data);


        //-----------------------tabela modyfikacja----------------------//

//
//        TableColumn name2 = new TableColumn("Nazwa");
//        TableColumn cena2 = new TableColumn("Cena");
//        TableColumn rozmiar2 = new TableColumn("Rozmiar");
//        TableColumn ilosc2 = new TableColumn("Ilośc");
//        TableColumn zaznacz2 = new TableColumn("Zaznacz");
//
//        T_modyfikacja.getColumns().addAll(name2, cena2, rozmiar2, ilosc2, zaznacz2);
//
//
//        final ObservableList<Produkty> data2 = FXCollections.observableArrayList(
//                new Produkty("Adidasy", 23.50),
//                new Produkty("Sandały", 70.60),
//                new Produkty("Kozaki", 230.99)
//        );
//
//
//        name2.setCellValueFactory(
//                new PropertyValueFactory<Produkty, String>("nazwa")
//        );
//
//        cena2.setCellValueFactory(
//                new PropertyValueFactory<Produkty, String>("cena")
//        );
//        rozmiar2.setCellValueFactory(
//                new PropertyValueFactory<Produkty, ComboBox>("rozmiar")
//        );
//        ilosc2.setCellValueFactory(
//                new PropertyValueFactory<Produkty, Spinner>("ilosc")
//        );
//
//
//        zaznacz2.setCellValueFactory(
//                new PropertyValueFactory<Produkty, CheckBox>("zaznacz")
//        );
//
//        T_modyfikacja.setItems(data2);

        //---------------------------------------------//


        List<Produkty> l=s.createQuery("SELECT p FROM Produkty p",Produkty.class).getResultList();

        ObservableList<Produkty> data2 = FXCollections.observableArrayList();

        data2.addAll(l);


        TableColumn name = new TableColumn("Nazwa");
        TableColumn cena = new TableColumn("Cena");


        T_modyfikacja.getColumns().addAll(name, cena);


        name.setCellValueFactory(
                new PropertyValueFactory<Produkty, String>("nazwa")
        );

        cena.setCellValueFactory(
                new PropertyValueFactory<Produkty, String>("cena")
        );



        T_modyfikacja.setItems(data2);

    //-------------//
        TableColumn name3 = new TableColumn("Nazwa");
        TableColumn cena3 = new TableColumn("Cena");
        TableColumn rozmiar = new TableColumn("Rozmiar");
        TableColumn status = new TableColumn("Status");


        T_dostepmosc.getColumns().addAll(name3, cena3, rozmiar,status);
        name3.setCellValueFactory(
                new PropertyValueFactory<Produkty, String>("nazwa")
        );

        cena3.setCellValueFactory(
                new PropertyValueFactory<Produkty, String>("cena")
        );


        rozmiar.setCellValueFactory(
                new PropertyValueFactory<Produkty, ComboBox>("rozmiar")
        );

        status.setCellValueFactory(
                new PropertyValueFactory<Produkty, ComboBox>("status")
        );
    }




    public void zapisz_modyfikacja(ActionEvent actionEvent) {

    }

    public void modyfikacja(ActionEvent actionEvent) {
        Produkty p=(Produkty) T_modyfikacja.getSelectionModel().getSelectedItem();
        if(p!=null) {
            nazwa_modyfikacja.setText(p.getNazwa());
            String s=""+p.getCena();
            cena_modyfikacja.setText(s);
        }

    }

    public void dodaj(ActionEvent actionEvent) {
        s.beginTransaction();
        s.save(new Produkty(20,nazwa_input.getText(),parseDouble(cena_input.getText())));
        s.getTransaction().commit();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Dodano nowy produkt");
        alert.setContentText(nazwa_input.getText()+" "+parseDouble(cena_input.getText()));

        alert.showAndWait();


    }

    public void zapisz_uprawnienia(ActionEvent actionEvent) {
    }

    public void tab_modyfikacja(Event event) {
        List<Produkty> l=s.createQuery("SELECT p FROM Produkty p",Produkty.class).getResultList();
        ObservableList<Produkty> data2 = FXCollections.observableArrayList();
        data2.addAll(l);
        T_modyfikacja.setItems(data2);
    }

    public void tab_dostepnosc(Event event) {

        final ObservableList<Produkty> data = FXCollections.observableArrayList(
                new Produkty("Adidasy", 23.50),
                new Produkty("Sandały", 70.60),
                new Produkty("Kozaki", 230.99)
        );




        T_dostepmosc.setItems(data);

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}