package facades;

import entities.Category;
import entities.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author asgerhs
 */
public class RequestFacade {

    private static RequestFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private RequestFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static RequestFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RequestFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Category> getAllCategories() {
        List<Category> categories = getEntityManager().createQuery("SELECT c FROM Category c").getResultList();
        for (Category cat : categories) {
            cat.setTimestamp(null);
        }
        return categories;
    }

    public Category getSingleCategory(String name) throws NoResultException {
        return getEntityManager().createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public long getRequestCountByCategory(String name) {
        List<Category> cats = new ArrayList();
        cats.add(getSingleCategory(name));

        return getEntityManager().createQuery("SELECT r FROM Request r WHERE r.categories IN :category")
                .setParameter("category", cats)
                .getResultList().size();

    }

    public List<Timestamp> getAllRequests() {
        return getEntityManager().createQuery("SELECT r FROM Request r").getResultList();
    }

    public void createRequest(List<Category> categoires) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Timestamp(categoires, new Date()));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
