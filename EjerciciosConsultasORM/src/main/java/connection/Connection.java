package connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {

    public EntityManagerFactory emf;
    public EntityManager em;

    public Connection() {
        this.emf = Persistence.createEntityManagerFactory("Ud03BDInm");
        this.em = emf.createEntityManager();
    }

    public EntityManager getConnection(){
        return em;
    }

    public void disconnect(){
        em.close();
        emf.close();
    }
}
