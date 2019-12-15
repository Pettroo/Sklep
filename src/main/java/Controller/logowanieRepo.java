package Controller;

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

public class logowanieRepo {

    EntityManagerFactory fabryka = Persistence.createEntityManagerFactory("Test");


    public List<Users> getAllUsers() {
        EntityManager em = fabryka.createEntityManager();
        List<Users> l = em.createQuery("SELECT u FROM Users u", Users.class).getResultList();
        return l;
    }
}
