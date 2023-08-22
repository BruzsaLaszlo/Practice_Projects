package dictionary;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class DictionaryRepository {

    EntityManagerFactory factory;

    public DictionaryRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveAll(List<EnglishHungarian> list) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        list.forEach(entityManager::persist);

        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public List<EnglishHungarian> loadAll() {
        EntityManager entityManager = factory.createEntityManager();

        var result = entityManager.createQuery(
                        "select t from EnglishHungarian t",
                        EnglishHungarian.class)
                .getResultList();

        entityManager.close();
        return result;
    }
}
