package Controller;

import entity.Orders;
import entity.Orders_positions;
import entity.Shoes;
import entity.Zamowienia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;

public class pracownikController extends GoTo{

    private String login;

    @FXML
    private TableView T_zamowienia;
    @FXML
    private TextArea text;

    Configuration conf=new Configuration().configure();
    SessionFactory factory=conf.buildSessionFactory();
    Session s=factory.openSession();

    ObservableList<Orders> data3 = FXCollections.observableArrayList();

//    final ObservableList<Zamowienia> data3 = FXCollections.observableArrayList(
//            new Zamowienia(1, new Date(2011,2,14),"Ad","Adam","Kowasli","Zrealizowane"),
//            new Zamowienia(2, new Date(2014,2,4),"ADam","Marcin","Nowak","Anulowane")
//    );

    public void initialize(){
        System.out.println(login);

        List<Orders> l=s.createQuery("SELECT p FROM Orders p",Orders.class).getResultList();
        data3.addAll(l);





        TableColumn numer = new TableColumn("Numer zamówienia");
        TableColumn datA = new TableColumn("Data");
        TableColumn login = new TableColumn("Login");
        TableColumn imie = new TableColumn("Imię");
        TableColumn nazwisko = new TableColumn("Nazwisko");

        TableColumn status = new TableColumn("Status");
        TableColumn zaznacz = new TableColumn("Zaznacz");




        T_zamowienia.getColumns().addAll(numer, datA, login,imie,nazwisko,status,zaznacz);
        numer.setCellValueFactory(
                new PropertyValueFactory<Orders, String>("id")
        );
        datA.setCellValueFactory(
                new PropertyValueFactory<Orders, String>("data")
        );
        status.setCellValueFactory(
                new PropertyValueFactory<Orders, String>("status")
        );
        login.setCellValueFactory(
                new PropertyValueFactory<Orders, String>("login")
        );
        imie.setCellValueFactory(
                new PropertyValueFactory<Orders, String>("imie")
        );
        nazwisko.setCellValueFactory(
                new PropertyValueFactory<Orders, String>("nazwisko")
        );
       zaznacz.setCellValueFactory(
                new PropertyValueFactory<Orders, CheckBox>("zaznacz")
       );


        T_zamowienia.setItems(data3);
    }



    public void wyloguj(ActionEvent actionEvent) {
        goTo(actionEvent,"/FXML/logowanie.fxml");
    }

    public void anulowanie(ActionEvent actionEvent) {
        for(Orders z:data3){
            if(z.getZaznacz().isSelected()){
                z.setStatus("Anulowane");
            }
        }
        T_zamowienia.refresh();
    }

    public void zrealizowane(ActionEvent actionEvent) {
        for(Orders z:data3){
            if(z.getZaznacz().isSelected()){
                z.setStatus("Zrealizowane");
            }
        }
        T_zamowienia.refresh();
    }

    public void szczegoly(ActionEvent actionEvent) {
        Orders o=(Orders) T_zamowienia.getSelectionModel().getSelectedItem();
        String s="";
        if(o!=null) {

            for(Orders_positions p: o.getPozycje()){
                s+=(p.toString());
                s+="d";
            }


            text.setText(s);

        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
