package entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PRODUCTS")
@IdClass(ProductsId.class)
public class Products {
    @Column(name = "id")
    private int id;
    @Id
    @Column(name = "shoe_id", insertable = false, updatable = false)
    private int shoe_id;
    @Id
    @Column(name = "size", insertable = false, updatable = false)
    private String size;
    @Column(name = "available_status", nullable = false)
    private boolean status;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "shoe_id", referencedColumnName = "id")
    private Shoes shoe;


//    @OneToMany(fetch = FetchType.EAGER,mappedBy = "produkt")
//    private Set<Orders_positions> lista;
//
//    public Set<Orders_positions> getLista() {
//        return lista;
//    }
//
//    public void setLista(Set<Orders_positions> lista) {
//        this.lista = lista;
//    }



    public int getShoe_id() {
        return shoe_id;
    }

    public void setShoe_id(int shoe_id) {
        this.shoe_id = shoe_id;
    }


    public Shoes getShoe() {
        return shoe;
    }

    public void setShoe(Shoes shoe) {
        this.shoe = shoe;
    }


    @Transient
    private String name;
    @Transient
    private Double value;
    @Transient
    private ComboBox status_zmien;
    @Transient
    private String status_disp;

    public String getStatus_disp() {
        if (status) status_disp = "Dostępny";
        else status_disp = "Niedostępny";
        return status_disp;
    }

    public void setStatus_disp(String status_disp) {

        this.status_disp = status_disp;
    }

    public Products() {
        ObservableList<String> options = FXCollections.observableArrayList("Niedostępny", "Dostępny");
        this.status_zmien = new ComboBox(options);
    }

    public ComboBox getStatus_zmien() {
        return status_zmien;
    }

    public void setStatus_zmien(ComboBox status_zmien) {
        this.status_zmien = status_zmien;
    }

    public String getName() {
        name = shoe.getNazwa();
        return name;
    }

    public void setName(String name) {
        name = shoe.getNazwa();
        this.name = name;
    }

    public Double getValue() {
        value = shoe.getCena();
        return value;
    }

    public void setValue(Double value) {
        value = shoe.getCena();

        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
