package entity;

import Controller.pracownikRepo;
import javafx.scene.control.CheckBox;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "ORDERS")
public class Orders {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "date")
    private Date data;
    @Column(name = "status", nullable = false)
    private String status;
    @ManyToOne
    @JoinColumn(name = "customer", nullable = false)
    private Users customer;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "order")
    private Set<Orders_positions> pozycje;

    public Set<Orders_positions> getPozycje() {
        return pozycje;
    }

    public void setPozycje(Set<Orders_positions> pozycje) {
        this.pozycje = pozycje;
    }

    @Transient
    private String login;
    @Transient
    private String imie;
    @Transient
    private String nazwisko;
    @Transient
    private CheckBox zaznacz;

    public Orders() {
        this.zaznacz = new CheckBox();
    }

    public CheckBox getZaznacz() {
        return zaznacz;
    }

    public void setZaznacz(CheckBox zaznacz) {
        this.zaznacz = zaznacz;
    }

    public String getLogin() {
        login = customer.getLogin();
        return login;
    }


    public String getImie() {
        imie = customer.getImie();
        return imie;
    }


    public String getNazwisko() {
        nazwisko = customer.getNazwisko();
        return nazwisko;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Users getCustomer() {
        return customer;
    }

    public void setCustomer(Users customer) {
        this.customer = customer;
    }
}
