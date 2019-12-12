package entity;

import java.util.Date;

public class Orders_positions {
    private int id;
    private Orders order;
    private Products produkt;
    private Integer ilosc;
    private Double cena;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Products getProdukt() {
        return produkt;
    }

    public void setProdukt(Products produkt) {
        this.produkt = produkt;
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Orders_positions{" +
                "id=" + id +
                ", order=" + order +
                ", produkt=" + produkt +
                ", ilosc=" + ilosc +
                ", cena=" + cena +
                '}';
    }
}
