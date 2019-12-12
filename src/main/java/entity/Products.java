package entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.Set;

public class Products {
    private int id;
    private Shoes shoe_id;
    private String size;
    private boolean status;

    private String name;       //display
    private Double value;       //display
    private ComboBox status_zmien;      //display
    private String status_disp;         //diplay

    public String getStatus_disp() {
        if(status) status_disp="Dostępny";
        else status_disp="Niedostępny";
        return status_disp;
    }

    public void setStatus_disp(String status_disp) {

        this.status_disp = status_disp;
    }

    public Products() {
        ObservableList<String> options = FXCollections.observableArrayList("Niedostępny","Dostępny");
        this.status_zmien = new ComboBox(options);
    }

    public ComboBox getStatus_zmien() {
        return status_zmien;
    }

    public void setStatus_zmien(ComboBox status_zmien) {
        this.status_zmien = status_zmien;
    }

    public String getName() {
        name=shoe_id.getNazwa();
        return name;
    }

    public void setName(String name) {
        name=shoe_id.getNazwa();
        this.name = name;
    }

    public Double getValue() {
        value=shoe_id.getCena();
        return value;
    }

    public void setValue(Double value) {
        value=shoe_id.getCena();

        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shoes getShoe_id() {
        return shoe_id;
    }

    public void setShoe_id(Shoes shoe_id) {
        this.shoe_id = shoe_id;
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
