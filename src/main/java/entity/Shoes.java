package entity;

import Controller.uzytkownikRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SHOES")
public class Shoes {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name", nullable = false)
    private String nazwa;
    @Column(name = "value", nullable = false)
    private Double cena;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shoe")
    private Set<Products> rozmiary;


    @Transient
    private Spinner ilosc;
    @Transient
    private ComboBox rozmiar;
    @Transient
    private CheckBox zaznacz;


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


    public Shoes() {
        ObservableList<String> options = FXCollections.observableArrayList("36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48");

        this.ilosc = new Spinner(1, 100, 1);
        this.rozmiar = new ComboBox(options);
        this.zaznacz = new CheckBox();    }

    public Set<Products> getRozmiary() {
        return rozmiary;
    }

    public void setRozmiary(Set<Products> rozmiary) {
        this.rozmiary = rozmiary;
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

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }
}
