package Controller;

import entity.Orders;
import entity.Orders_positions;
import entity.Products;
import entity.Shoes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

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
    ObservableList<Shoes> dataKoszyk = FXCollections.observableArrayList();

    public void initialize() {
        System.out.println(login);

        //table produkty
        TableColumn name = new TableColumn("Nazwa");
        TableColumn cena = new TableColumn("Cena");
        TableColumn do_koszyka = new TableColumn("Dodaj do koszyka");
        name.setCellValueFactory(new PropertyValueFactory<Shoes, String>("nazwa"));
        cena.setCellValueFactory(new PropertyValueFactory<Shoes, Double>("cena"));
        do_koszyka.setCellValueFactory(new PropertyValueFactory<Shoes, CheckBox>("zaznacz"));
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
        int flag = 0;

        for (Shoes z : (List<Shoes>) T_koszyk.getItems()) {
            if (z.getZaznacz().isSelected()) flag++;
        }
        if (flag == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Błąd");
            alert.setContentText("Musisz wybrać przynajmniej jeden artykół by złożyć zamówienie");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Zamówienie");
            alert.setContentText("Zamówienie na wybrane produkty zostało złożone");
            alert.showAndWait();

            int order_id = repo.addOrder(repo.getUser(login).getId());

            for (Shoes z : (List<Shoes>) T_koszyk.getItems()) {
                if (z.getZaznacz().isSelected()) {
                    List<Products> list = repo.getProductFromShoe(z.getId());
                    for (Products p : list) {
                        if (p.getSize().equals((String) z.getRozmiar().getSelectionModel().getSelectedItem())) {
                            repo.addOrderPosition(order_id, p.getId(), z.getIlosc().getValue());
                        }
                    }

                }
            }
        }

    }

    public void dodaj_do_koszyka(ActionEvent actionEvent) {

        for (Shoes z : (List<Shoes>) T_produkty.getItems()) {
            if (z.getZaznacz().isSelected()) {
                z.getZaznacz().setSelected(false);
                dataKoszyk.add(z);
            }
        }
        T_koszyk.setItems(dataKoszyk);
    }

    public void usun(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Usuwanie");
        alert.setContentText("Wybrane artykóły zostały usunięte");
        alert.showAndWait();
        dataKoszyk = FXCollections.observableArrayList();

        for (Shoes z : (List<Shoes>) T_koszyk.getItems()) {
            if (!z.getZaznacz().isSelected()) {
                dataKoszyk.add(z);
            }
        }
        T_koszyk.setItems(dataKoszyk);
        tab_oferta(actionEvent);

    }

    public void szczegoly(ActionEvent actionEvent) {
        Orders z = (Orders) T_zamowienia.getSelectionModel().getSelectedItem();
        String s = "";
        if (z != null) {
            List<Orders_positions> lista = repo.getOrderPositions(z.getId());
            for (Orders_positions pos : lista) {

                s += repo.getProduct(pos.getProduktId()).getName() + "  Cena: " + pos.getCena() + "  Rozmiar: " + repo.getProduct(pos.getProduktId()).getSize() + "  Ilość: " + pos.getIlosc() + "\n";
            }
            text.setText(s);
        }
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
    }

    public void tab_zamowienia(Event event) {
        ObservableList<Orders> data = FXCollections.observableArrayList();
        data.addAll(repo.getUserOrders(repo.getUser(login).getId()));
        T_zamowienia.setItems(data);
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
