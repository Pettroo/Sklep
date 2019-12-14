package Controller;

import entity.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class pracownikController extends GoTo {

    private String login;

    @FXML
    private TableView T_zamowienia;
    @FXML
    private TextArea text;
    pracownikRepo repo = new pracownikRepo();
    ObservableList<Orders> data = FXCollections.observableArrayList();

    public void initialize() {
        System.out.println(login);

        TableColumn numer = new TableColumn("Numer zamówienia");
        TableColumn datA = new TableColumn("Data");
        TableColumn login = new TableColumn("Login");
        TableColumn imie = new TableColumn("Imię");
        TableColumn nazwisko = new TableColumn("Nazwisko");
        TableColumn status = new TableColumn("Status");
        TableColumn zaznacz = new TableColumn("Zaznacz");

        numer.setCellValueFactory(new PropertyValueFactory<Orders, String>("id"));
        datA.setCellValueFactory(new PropertyValueFactory<Orders, String>("data"));
        status.setCellValueFactory(new PropertyValueFactory<Orders, String>("status"));
        login.setCellValueFactory(new PropertyValueFactory<Orders, String>("login"));
        imie.setCellValueFactory(new PropertyValueFactory<Orders, String>("imie"));
        nazwisko.setCellValueFactory(new PropertyValueFactory<Orders, String>("nazwisko"));
        zaznacz.setCellValueFactory(new PropertyValueFactory<Orders, CheckBox>("zaznacz"));
        T_zamowienia.getColumns().addAll(numer, datA, login, imie, nazwisko, status, zaznacz);

        data.addAll(repo.getAllOrders());
        T_zamowienia.setItems(data);
    }


    public void wyloguj(ActionEvent actionEvent) {
        goTo(actionEvent, "/FXML/logowanie.fxml");
    }

    public void anulowanie(ActionEvent actionEvent) {
        for (Orders z : data) {
            if (z.getZaznacz().isSelected()) {
                z.setStatus("Anulowane");
            }
        }
        T_zamowienia.refresh();
    }

    public void zrealizowane(ActionEvent actionEvent) {
        for (Orders z : data) {
            if (z.getZaznacz().isSelected()) {
                z.setStatus("Zrealizowane");
            }
        }
        T_zamowienia.refresh();
    }

    public void szczegoly(ActionEvent actionEvent) {
        Orders o = (Orders) T_zamowienia.getSelectionModel().getSelectedItem();
        String s = "";
        if (o != null) {

            //   for(Orders_positions p: o.getPozycje()){
            //        s+=(p.toString());
            //        s+="d";
            //   }
            // nie działa bo nie ma połaczenia z order do order_positions

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
