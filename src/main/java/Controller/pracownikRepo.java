package Controller;

import entity.Orders;
import entity.Orders_positions;
import entity.Products;
import entity.Shoes;
import org.hibernate.Session;
import org.hibernate.annotations.Type;
import org.hibernate.internal.SessionImpl;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class pracownikRepo {
    EntityManagerFactory fabryka = Persistence.createEntityManagerFactory("Test");

    public List<Orders> getAllOrders() {
        EntityManager em = fabryka.createEntityManager();

        List<Orders> l = em.createQuery("SELECT p FROM Orders p", Orders.class).getResultList();
        return l;
    }

    public List<Orders_positions> getOrderPositions(int id) {
        EntityManager em = fabryka.createEntityManager();
        List<Orders_positions> l = em.createQuery("SELECT p FROM Orders_positions p WHERE order_id=" + id, Orders_positions.class).getResultList();
        return l;
    }

    public Products getProduct(int id) {
        EntityManager em = fabryka.createEntityManager();
        Products l = em.createQuery("SELECT p FROM Products p WHERE id=" + id, Products.class).getSingleResult();
        return l;
    }

    public void setOrderStatus(int id, String status) {
        EntityManager em = fabryka.createEntityManager();

        Session sesion = (Session) em.getDelegate();
        Connection con = ((SessionImpl) sesion).connection();
        PreparedStatement callableStatement = null;
        try {
            callableStatement = con.prepareStatement("CALL s_orders.set_order_status(?,?)");
            callableStatement.setInt(1, id);
            callableStatement.setString(2, status);

            callableStatement.execute();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
