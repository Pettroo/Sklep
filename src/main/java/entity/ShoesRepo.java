package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ShoesRepo {
    EntityManagerFactory fabryka= Persistence.createEntityManagerFactory("Test");
    EntityManager em=fabryka.createEntityManager();

    public List<Shoes> allShoes(){
        return em.createQuery("SELECT s FROM Shoes s", Shoes.class).getResultList();
    }


}
