package task7;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("restaurantMenuPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void closeEntityManager(EntityManager em) {
        if (em != null) {
            em.close();
        }
    }
}
