package entity;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS_POSITIONS")
@IdClass(Orders_positionsId.class)
public class Orders_positions {
    @Column(name = "id")
    private int id;
    @Id
    @Column(name = "order_id", insertable = false, updatable = false)
    private int orderId;
    @Id
    @Column(name = "product_id", insertable = false, updatable = false)
    private int produktId;
    @Column(name = "quantity", nullable = false)
    private Integer ilosc;
    @Column(name = "single_price", nullable = false)
    private Double cena;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProduktId() {
        return produktId;
    }

    public void setProduktId(int produktId) {
        this.produktId = produktId;
    }

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders order;
//    @ManyToOne
//    @PrimaryKeyJoinColumn(name = "product_id", referencedColumnName = "id")
//    private Products produkt;
//
//    public Products getProdukt() {
//        return produkt;
//    }
//
//    public void setProdukt(Products produkt) {
//        this.produkt = produkt;
//    }


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


}
