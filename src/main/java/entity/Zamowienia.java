package entity;

import javafx.scene.control.CheckBox;
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

    public String getData() {
        String formattedDate = String.format("%1$te/%1$tm/%1$tY", data);
        return formattedDate;
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


    public String toString1() {
        return
                "Numer: " + numerZ +"\n"+
                "Data: " + String.format("%1$te/%1$tm/%1$tY", data) +"\n"+
                "login: " + login +"\n"+
                "Imię: " + imie +"\n"+
                "Nazwisko: " + nazwisko +"\n"+
                "Status: " + status
                ;
    }
    public String toString2() {
        return
                "Numer: " + numerZ +"\n"+
                "Data: " + String.format("%1$te/%1$tm/%1$tY", data) +"\n"+
                "Status: " + status
                ;
    }
    public Zamowienia(int numerZ, Date data, String login, String imie, String nazwisko, String status) {
        this.numerZ = numerZ;
        this.data = data;
        this.login = login;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.status = status;
        this.zaznacz = new CheckBox();
    }

    public Zamowienia(int numerZ, Date data, String status) {
        this.numerZ = numerZ;
        this.data = data;
        this.status = status;
    }
}
