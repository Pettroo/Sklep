package entity;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;

import java.util.Date;

public class Zamowienia {
    private int numerZ;
    private Date data;
    private String login;
    private String imie;
    private String nazwisko;
    private String status;
    private CheckBox zaznacz;

    public int getNumerZ() {
        return numerZ;
    }

    public void setNumerZ(int numerZ) {
        this.numerZ = numerZ;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CheckBox getZaznacz() {
        return zaznacz;
    }

    public void setZaznacz(CheckBox zaznacz) {
        this.zaznacz = zaznacz;
    }

    @Override
    public String toString() {
        return "Zamowienia{" +
                "numerZ=" + numerZ +
                ", data=" + data +
                ", login='" + login + '\'' +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Zamowienia(int numerZ, Date data, String login, String imie, String nazwisko, String status) {
        this.numerZ = numerZ;
        this.data = data;
        this.login = login;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.status = status;
        this.zaznacz=new CheckBox();
    }

    public Zamowienia(int numerZ, Date data, String status) {
        this.numerZ = numerZ;
        this.data = data;
        this.status = status;
    }
}
