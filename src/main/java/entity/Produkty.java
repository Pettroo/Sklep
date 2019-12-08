package entity;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;

import javax.persistence.Table;


@Table(name="produkty")
public class Produkty {

    private int id;
    private String nazwa;
    private double cena;
    private Button koszyk;
    private Spinner ilosc;
    private ComboBox rozmiar;
    private CheckBox zaznacz;
    private ComboBox status;

    public ComboBox getStatus() {
        return status;
    }

    public void setStatus(ComboBox status) {
        this.status = status;
    }

    public Produkty() {

    }


    public Produkty(int i,String nazwa, double cena) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.id=i;
    }
    public Produkty(String nazwa, double cena) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.koszyk = new Button("Dodaj do koszyka");
        this.ilosc = new Spinner(1, 10, 1);
        this.rozmiar = new ComboBox();
        this.zaznacz = new CheckBox();
        this.status = new ComboBox();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Button getKoszyk() {
        return koszyk;
    }

    public void setKoszyk(Button koszyk) {
        this.koszyk = koszyk;
    }

    public Spinner getIlosc() {
        return ilosc;
    }

    public void setIlosc(Spinner ilosc) {
        this.ilosc = ilosc;
    }

    public ComboBox getRozmiar() {
        return rozmiar;
    }

    public void setRozmiar(ComboBox rozmiar) {
        this.rozmiar = rozmiar;
    }

    public CheckBox getZaznacz() {
        return zaznacz;
    }

    public void setZaznacz(CheckBox zaznacz) {
        this.zaznacz = zaznacz;
    }

}
