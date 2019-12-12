package entity;

import java.util.Set;

public class Shoes {
    private int id;
    private String nazwa;
    private Double cena;
    private Set<Products> rozmiary;

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
