package Controller;

import entity.Orders;
import entity.Shoes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class pracownikRepo {
    EntityManagerFactory fabryka = Persistence.createEntityManagerFactory("Test");
    EntityManager em = fabryka.createEntityManager();

    public List<Orders> getAllOrders() {
        List<Orders> l = em.createQuery("SELECT p FROM Orders p", Orders.class).getResultList();
        return l;
    }
}
