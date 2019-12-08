package Controller;

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

import java.util.Date;

public class pracownikController extends GoTo{

    private String login;

    @FXML
    private TableView T_zamowienia;
    @FXML
    private TextArea text;


    final ObservableList<Zamowienia> data3 = FXCollections.observableArrayList(
            new Zamowienia(1, new Date(2011,2,14),"Ad","Adam","Kowasli","Zrealizowane"),
            new Zamowienia(2, new Date(2014,2,4),"ADam","Marcin","Nowak","Anulowane")
    );

    public void initialize(){
        System.out.println(login);

        TableColumn numer = new TableColumn("Numer zamówienia");
        TableColumn datA = new TableColumn("Data");
        TableColumn login = new TableColumn("Login");
        TableColumn imie = new TableColumn("Imię");
        TableColumn nazwisko = new TableColumn("Nazwisko");

        TableColumn status = new TableColumn("Status");
        TableColumn zaznacz = new TableColumn("Zaznacz");




        T_zamowienia.getColumns().addAll(numer, datA, login,imie,nazwisko,status,zaznacz);
        numer.setCellValueFactory(
                new PropertyValueFactory<Zamowienia, String>("numerZ")
        );
        datA.setCellValueFactory(
                new PropertyValueFactory<Zamowienia, String>("data")
        );
        status.setCellValueFactory(
                new PropertyValueFactory<Zamowienia, String>("status")
        );
        login.setCellValueFactory(
                new PropertyValueFactory<Zamowienia, String>("login")
        );
        imie.setCellValueFactory(
                new PropertyValueFactory<Zamowienia, String>("imie")
        );
        nazwisko.setCellValueFactory(
                new PropertyValueFactory<Zamowienia, String>("nazwisko")
        );
        zaznacz.setCellValueFactory(
                new PropertyValueFactory<Zamowienia, CheckBox>("zaznacz")
        );


        T_zamowienia.setItems(data3);
    }



    public void wyloguj(ActionEvent actionEvent) {
        goTo(actionEvent,"/FXML/logowanie.fxml");
    }

    public void anulowanie(ActionEvent actionEvent) {
        for(Zamowienia z:data3){
            if(z.getZaznacz().isSelected()){
                z.setStatus("Anulowane");
            }
        }
        T_zamowienia.refresh();
    }

    public void zrealizowane(ActionEvent actionEvent) {
        for(Zamowienia z:data3){
            if(z.getZaznacz().isSelected()){
                z.setStatus("Zrealizowane");
            }
        }
        T_zamowienia.refresh();
    }

    public void szczegoly(ActionEvent actionEvent) {
        Zamowienia z=(Zamowienia) T_zamowienia.getSelectionModel().getSelectedItem();
        if(z!=null) text.setText(z.toString1());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
