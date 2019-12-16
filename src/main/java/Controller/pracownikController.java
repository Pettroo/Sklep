package Controller;

import entity.Orders;
import entity.Orders_positions;
import entity.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

    public void initialize() {
        System.out.println(login);

        TableColumn numer = new TableColumn("Numer zamówienia");
        TableColumn datA = new TableColumn("Data");
        TableColumn login = new TableColumn("Login");
        TableColumn imie = new TableColumn("Imię");
        TableColumn nazwisko = new TableColumn("Nazwisko");
        TableColumn status = new TableColumn("Status");
        TableColumn zaznacz = new TableColumn("Zaznacz");
        numer.setSortType(TableColumn.SortType.ASCENDING);

        numer.setCellValueFactory(new PropertyValueFactory<Orders, String>("id"));
        datA.setCellValueFactory(new PropertyValueFactory<Orders, String>("data"));
        status.setCellValueFactory(new PropertyValueFactory<Orders, String>("status"));
        login.setCellValueFactory(new PropertyValueFactory<Orders, String>("login"));
        imie.setCellValueFactory(new PropertyValueFactory<Orders, String>("imie"));
        nazwisko.setCellValueFactory(new PropertyValueFactory<Orders, String>("nazwisko"));
        zaznacz.setCellValueFactory(new PropertyValueFactory<Orders, CheckBox>("zaznacz"));
        T_zamowienia.getColumns().addAll(numer, datA, login, imie, nazwisko, status, zaznacz);
        T_zamowienia.getSortOrder().setAll(numer);

        tab_zamowienia();
    }

    public void tab_zamowienia() {
        ObservableList<Orders> data = FXCollections.observableArrayList();

        data.addAll(repo.getAllOrders());
        SortedList<Orders> sortedData = new SortedList<>(data);
        sortedData.comparatorProperty().bind(T_zamowienia.comparatorProperty());
        T_zamowienia.setItems(sortedData);

    }

    public void wyloguj(ActionEvent actionEvent) {
        goTo(actionEvent, "/FXML/logowanie.fxml");
    }

    public void anulowanie(ActionEvent actionEvent) {
        for (Orders z : (List<Orders>) T_zamowienia.getItems()) {
            if (z.getZaznacz().isSelected()) {
                //   z.setStatus("Anulowane");
                repo.setOrderStatus(z.getId(), "Anulowane");
            }
        }
        tab_zamowienia();
        T_zamowienia.refresh();
    }

    public void zrealizowane(ActionEvent actionEvent) {
        for (Orders z : (List<Orders>) T_zamowienia.getItems()) {
            if (z.getZaznacz().isSelected()) {
                // z.setStatus("Zrealizowane");
                repo.setOrderStatus(z.getId(), "Zrealizowane");
            }
        }
        tab_zamowienia();
        T_zamowienia.refresh();
    }

    public void szczegoly(ActionEvent actionEvent) {
        Orders o = (Orders) T_zamowienia.getSelectionModel().getSelectedItem();
        String s = "";
        if (o != null) {

            List<Orders_positions> lista = repo.getOrderPositions(o.getId());
            for (Orders_positions pos : lista) {

                s += repo.getProduct(pos.getProduktId()).getName() + "  Cena: " + pos.getCena() + "  Rozmiar: " + repo.getProduct(pos.getProduktId()).getSize() + "  Ilość: " + pos.getIlosc() + "\n";
            }
            text.setText(s);
        }
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
