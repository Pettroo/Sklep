package entity;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name", nullable = false)
    private String nazwa;
    @Column(name = "value", nullable = false)
    private Double cena;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shoe")
    private Set<Products> rozmiary;

    @Transient
    private Button koszyk;
    @Transient
    private Spinner ilosc;
    @Transient
    private ComboBox rozmiar;
    @Transient
    private CheckBox zaznacz;

    public Button getKoszyk() {
        return koszyk;
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

    public void setKoszyk(Button koszyk) {
        this.koszyk = koszyk;
    }

    public Shoes() {
        this.koszyk = new Button("Dodaj do koszyka");
        this.ilosc = new Spinner(1, 100, 1);
        this.rozmiar = new ComboBox();
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
