package Controller;

import entity.Shoes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class przegladanieRepo {
    EntityManagerFactory fabryka = Persistence.createEntityManagerFactory("Test");
    EntityManager em = fabryka.createEntityManager();

    public List<Shoes> getAllShoes() {
        return em.createQuery("SELECT s FROM Shoes s", Shoes.class).getResultList();
    }
}
