package Controller;

import entity.*;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class uzytkownikRepo {
    EntityManagerFactory fabryka = Persistence.createEntityManagerFactory("Test");
    EntityManager em = fabryka.createEntityManager();

       public List<Shoes> getAllShoes() {
        List<Shoes> l = em.createQuery("SELECT p FROM Shoes p", Shoes.class).getResultList();
        return l;
    }

    public List<Orders> getAllOrders() {
        List<Orders> l = em.createQuery("SELECT p FROM Orders p", Orders.class).getResultList();
        return l;
    }

}
