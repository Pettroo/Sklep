package Controller;

import entity.Products;
import entity.Shoes;
import entity.Users;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class managerRepo {

    EntityManagerFactory fabryka = Persistence.createEntityManagerFactory("Test");

    public void addShoe(String nazwa, Double cena) {
        EntityManager em = fabryka.createEntityManager();

        Session sesion = (Session) em.getDelegate();
        Connection con = ((SessionImpl) sesion).connection();
        PreparedStatement callableStatement = null;
        try {
            callableStatement = con.prepareStatement("CALL s_products.add_shoe(?,?)");
            callableStatement.setString(1, nazwa);
            callableStatement.setDouble(2, cena);
            callableStatement.execute();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Shoes> getAllShoes() {
        EntityManager em = fabryka.createEntityManager();
        List<Shoes> l = em.createQuery("SELECT p FROM Shoes p", Shoes.class).getResultList();
        return l;
    }

    public List<Products> getAllProducts() {
        EntityManager em = fabryka.createEntityManager();

        List<Products> l = em.createQuery("SELECT p FROM Products p", Products.class).getResultList();
        return l;
    }

    public List<Users> getAllUsers() {
        EntityManager em = fabryka.createEntityManager();

        List<Users> l = em.createQuery("SELECT u FROM Users u", Users.class).getResultList();
        return l;
    }

    public void setUserRole(int id,String code){
        EntityManager em = fabryka.createEntityManager();

        Session sesion = (Session) em.getDelegate();
        Connection con = ((SessionImpl) sesion).connection();
        PreparedStatement callableStatement = null;
        try {
            callableStatement = con.prepareStatement("CALL s_users.set_user_role(?,?)");
            callableStatement.setInt(1, id);
            callableStatement.setString(2, code);
            callableStatement.execute();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setShoeName(int id,String name){
        EntityManager em = fabryka.createEntityManager();

        Session sesion = (Session) em.getDelegate();
        Connection con = ((SessionImpl) sesion).connection();
        PreparedStatement callableStatement = null;
        try {
            callableStatement = con.prepareStatement("CALL s_products.set_shoe_name(?,?)");
            callableStatement.setInt(1, id);
            callableStatement.setString(2, name);
            callableStatement.execute();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setShoeValue(int id,Double cena){
        EntityManager em = fabryka.createEntityManager();

        Session sesion = (Session) em.getDelegate();
        Connection con = ((SessionImpl) sesion).connection();
        PreparedStatement callableStatement = null;
        try {
            callableStatement = con.prepareStatement("CALL s_products.set_shoe_value(?,?)");
            callableStatement.setInt(1, id);
            callableStatement.setDouble(2, cena);
            callableStatement.execute();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setProductAvilable(int id){
        EntityManager em = fabryka.createEntityManager();

        Session sesion = (Session) em.getDelegate();
        Connection con = ((SessionImpl) sesion).connection();
        PreparedStatement callableStatement = null;
        try {
            callableStatement = con.prepareStatement("CALL s_products.set_product_available(?)");
            callableStatement.setInt(1, id);
            callableStatement.execute();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
