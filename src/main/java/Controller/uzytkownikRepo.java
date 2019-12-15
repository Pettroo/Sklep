package Controller;

import entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;


public class uzytkownikRepo {
    EntityManagerFactory fabryka = Persistence.createEntityManagerFactory("Test");


    public List<Shoes> getAllShoes() {
        EntityManager em = fabryka.createEntityManager();
        List<Shoes> l = em.createQuery("SELECT p FROM Shoes p", Shoes.class).getResultList();
        return l;
    }

    public List<Orders> getAllOrders() {
        EntityManager em = fabryka.createEntityManager();
        List<Orders> l = em.createQuery("SELECT p FROM Orders p", Orders.class).getResultList();
        return l;
    }

    public List<Orders> getUserOrders(int id) {
        EntityManager em = fabryka.createEntityManager();
        List<Orders> l = em.createQuery("SELECT p FROM Orders p WHERE customer ="+ id, Orders.class).getResultList();
        return l;
    }
    public Users getUser(String login) {
        EntityManager em = fabryka.createEntityManager();
        Users l = em.createQuery("SELECT p FROM Users p WHERE login = '"+login+"'", Users.class).getSingleResult();
        return l;
    }
    public Products getProduct(int id) {
        EntityManager em = fabryka.createEntityManager();
        Products l = em.createQuery("SELECT p FROM Products p WHERE id=" + id, Products.class).getSingleResult();
        return l;
    }
    public List<Orders_positions> getOrderPositions(int id) {
        EntityManager em = fabryka.createEntityManager();
        List<Orders_positions> l = em.createQuery("SELECT p FROM Orders_positions p WHERE order_id=" + id, Orders_positions.class).getResultList();
        return l;
    }
}
