package com.upgrad.imagehostler.repository;

import com.upgrad.imagehostler.Model.Image;
import com.upgrad.imagehostler.Model.User;
import com.upgrad.imagehostler.Model.comment;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
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
    public comment savecomment(HttpSession session,int id,String text){
        User user = (User) session.getAttribute("user");
    Image im = getSingleImages(id);
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        comment c = new comment();

        try {
            transaction.begin();
 c.setImage(im);
 c.setUser(user);
 c.setText(text);
            entityManager.persist(c);
            transaction.commit();

             return c;

        }catch(Exception e) {
            transaction.rollback();
            return c;

        }

    }
    public boolean deleteImage(int id){
        System.out.println("++++++++++++++++++++++++ " + id);
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
     public Image editNewImage(Image newImage){
         EntityManager entityManager = emf.createEntityManager();
         EntityTransaction transaction = entityManager.getTransaction();
          try {
             transaction.begin();
              entityManager.merge(newImage);
             transaction.commit();
         }catch (Exception e){
             transaction.rollback();
         }
         return newImage;
     }
}


