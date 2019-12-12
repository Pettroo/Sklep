package entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.Set;

public class Users {
    private int id;
    private String imie;
    private String nazwisko;
    private String login;
    private String haslo;
    private Roles rola;
    private Set<Orders> zamowienia;


    private ComboBox uprawnienia;      //display
    private String rolaDisp;           //display


    public Users() {
        ObservableList<String> options = FXCollections.observableArrayList("Użytkownik", "Menadżer", "Pracownik");
        this.uprawnienia = new ComboBox(options);
    }

    public ComboBox getUprawnienia() {
        return uprawnienia;
    }

    public void setUprawnienia(ComboBox uprawnienia) {
        this.uprawnienia = uprawnienia;
    }


    public Roles getRola() {
        return rola;
    }

    public void setRola(Roles rola) {
        this.rola = rola;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getRolaDisp() {
        rolaDisp = rola.getNazwa();
        return rolaDisp;
    }

    public void setRolaDisp(String rolaDisp) {
        rolaDisp = rola.getNazwa();
        this.rolaDisp = rolaDisp;
    }

    public Set<Orders> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(Set<Orders> zamowienia) {
        this.zamowienia = zamowienia;
    }


}
