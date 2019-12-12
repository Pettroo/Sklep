package entity;

import javafx.scene.control.CheckBox;

import java.util.Date;
import java.util.Set;

public class Orders {
    private int id;
    private Date data;
    private String status;
    private Users customer;
    private Set<Orders_positions> pozycje;

    private String login;       //display
    private String imie;        //display
    private String nazwisko;    //display

    private CheckBox zaznacz;   //display

    public Orders() {
        this.zaznacz=new CheckBox();
    }

    public CheckBox getZaznacz() {
        return zaznacz;
    }

    public void setZaznacz(CheckBox zaznacz) {
        this.zaznacz = zaznacz;
    }

    public String getLogin() {
        login=customer.getLogin();
        return login;
    }



    public String getImie() {
        imie=customer.getImie();
        return imie;
    }



    public String getNazwisko() {
        nazwisko=customer.getNazwisko();
        return nazwisko;
    }



    public Set<Orders_positions> getPozycje() {
        return pozycje;
    }

    public void setPozycje(Set<Orders_positions> pozycje) {
        this.pozycje = pozycje;
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
