package com.upgrad.imagehostler.repository;

import com.upgrad.imagehostler.Model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

@Repository
public class imageRepositor {
    @PersistenceUnit(unitName = "imagehostler")

    EntityManagerFactory emf;
    public Image uploadImage(Image newImage){
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(newImage);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        return newImage;
    }
}
