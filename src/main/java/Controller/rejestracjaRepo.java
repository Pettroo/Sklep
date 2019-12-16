package Controller;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class rejestracjaRepo {
    EntityManagerFactory fabryka = Persistence.createEntityManagerFactory("Test");

    public void addUser(String login, String password, String forename, String name) {
        EntityManager em = fabryka.createEntityManager();

        Session sesion = (Session) em.getDelegate();
        Connection con = ((SessionImpl) sesion).connection();
        PreparedStatement callableStatement = null;
        try {
            callableStatement = con.prepareStatement("CALL s_users.add_user(?,?,?,?)");
            callableStatement.setString(1, login);
            callableStatement.setString(2, password);
            callableStatement.setString(3, forename);
            callableStatement.setString(4, name);

            callableStatement.execute();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
