package entity;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import javax.persistence.*;
import java.sql.*;


public class ManagerRepo {
    EntityManagerFactory fabryka= Persistence.createEntityManagerFactory("Test");
    EntityManager em=fabryka.createEntityManager();

    public void dodajButa(String nazwa,Double cena)  {

        Session sesion=(Session)em.getDelegate();
        Connection con =((SessionImpl)sesion).connection();

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
}
