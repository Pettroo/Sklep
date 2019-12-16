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
    public void addOrder(int id){
        EntityManager em = fabryka.createEntityManager();

        Session sesion = (Session) em.getDelegate();
        Connection con = ((SessionImpl) sesion).connection();
        PreparedStatement callableStatement = null;
        try {
            callableStatement = con.prepareStatement("CALL s_orders.add_order(?)");
            callableStatement.setInt(1, id);
            callableStatement.execute();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addOrderPosition(int order_id,int product_id,int quantity){
        EntityManager em = fabryka.createEntityManager();

        Session sesion = (Session) em.getDelegate();
        Connection con = ((SessionImpl) sesion).connection();
        PreparedStatement callableStatement = null;
        try {
            callableStatement = con.prepareStatement("CALL s_orders.add_order_position(?,?,?)");
            callableStatement.setInt(1, order_id);
            callableStatement.setInt(2, product_id);
            callableStatement.setInt(3, quantity);
            callableStatement.execute();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
