package com.upgrad.imagehostler.repository;

import com.upgrad.imagehostler.Model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

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
    public List<Image> getAllImages(){
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createQuery("select i from Image i");
        return query.getResultList();


    }
    public Image getSingleImages(int id){
        EntityManager entityManager = emf.createEntityManager();

        return  entityManager.find(Image.class,id);
    }
    public boolean deleteImage(int id){
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Image image = entityManager.find(Image.class, id);
            entityManager.remove(image);
             transaction.commit();
            return true;

        }catch(Exception e) {
            transaction.rollback();
            return false;

        }
     }
}


