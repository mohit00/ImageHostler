package com.upgrad.imagehostler.repository;

import com.upgrad.imagehostler.Model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class userRepository {
    @PersistenceUnit(unitName = "imagehostler")

    private EntityManagerFactory emf;

    public User registerUser(User user){

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
         return user;
    }
    public boolean userLogin(User user){
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createQuery("select u from User u where u.username = :user AND  u.password = :password");
        query.setParameter("user",user.getUsername());
        query.setParameter("password",user.getPassword());
        List resultList = query.getResultList();
            if(resultList.size() > 0){
                return true;
            }else{
                return false;
            }
    }
}
