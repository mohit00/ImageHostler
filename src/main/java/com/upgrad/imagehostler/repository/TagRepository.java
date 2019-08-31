package com.upgrad.imagehostler.repository;

 import com.upgrad.imagehostler.Model.tags;
 import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class TagRepository {
    @PersistenceUnit(unitName = "imagehostler")
    private EntityManagerFactory emf;

    public tags createTag(tags tag) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(tag);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return tag;
    }

    public tags findTag(String tagName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<tags> typedQuery = em.createQuery("SELECT t from tags t where t.tag_name =:tagName", tags.class).setParameter("tagName", tagName);
            return typedQuery.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
