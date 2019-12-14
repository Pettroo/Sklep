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
    EntityManager em = fabryka.createEntityManager();

    public void addShoe(String nazwa, Double cena) {
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
        List<Shoes> l = em.createQuery("SELECT p FROM Shoes p", Shoes.class).getResultList();
        return l;
    }

    public List<Products> getAllProducts() {
        List<Products> l = em.createQuery("SELECT p FROM Products p", Products.class).getResultList();
        return l;
    }

    public List<Users> getAllUsers() {
        List<Users> l = em.createQuery("SELECT u FROM Users u", Users.class).getResultList();
        return l;
    }
}
