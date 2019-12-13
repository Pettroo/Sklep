package entity;

import javax.persistence.*;






public class ManagerRepo {
    EntityManagerFactory fabryka= Persistence.createEntityManagerFactory("Test");
    EntityManager em=fabryka.createEntityManager();

    public void dodajButa(){
        StoredProcedureQuery query = em
                .createNamedStoredProcedureQuery("s");

     //   query.registerStoredProcedureParameter("p_name",String.class,ParameterMode.IN);
     //   query.registerStoredProcedureParameter("p_value",Double.class,ParameterMode.IN);

        query.setParameter("p_name","a");
        query.setParameter("p_value",99.99);
     //   query.execute();

    }
}
