package entity;

import javafx.scene.control.ComboBox;


public class pracownicy {


    private String imie;
    private String nazwisko;

    private String login;
    private ComboBox uprawnienia;


    public pracownicy(String imie, String nazwisko, String login) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.login = login;
        this.uprawnienia=new ComboBox();

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

    public ComboBox getUprawnienia() {
        return uprawnienia;
    }

    public void setUprawnienia(ComboBox uprawnienia) {
        this.uprawnienia = uprawnienia;
    }

}
