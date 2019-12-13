package entity;

import javax.persistence.*;
import java.util.Set;
@NamedStoredProcedureQuery(
        name="s",
        procedureName="Dodaj",
        parameters = {@StoredProcedureParameter(mode=ParameterMode.IN, type = String.class,name="p_name"),
                @StoredProcedureParameter(mode=ParameterMode.IN, type = Double.class,name="p_value")
        }

)

@Entity
@Table(name="SHOES")
public class Shoes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="name", nullable=false)
    private String nazwa;
    @Column(name="value", nullable = false)
    private Double cena;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "shoe")
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
