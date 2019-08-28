package com.upgrad.imagehostler.Service;

 import com.upgrad.imagehostler.Model.Image;
 import com.upgrad.imagehostler.Model.User;
 import com.upgrad.imagehostler.repository.userRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
import  com.upgrad.imagehostler.repository.userRepository;

 import javax.servlet.http.HttpSession;

@Service
public class UserService {
    @Autowired
    userRepository userRepository;
    //We are not currently storing the details of the user anywhere
    //We will be storing the user details in the Database & ORMs part
    public User registerUser(User newUser) {


        return userRepository.registerUser(newUser);
    }

    //Since we do not have any user in the database, therefore the user with username 'upgrad' and password 'password' is hard-coded
    //This method returns true if the username is 'upgrad' and password is 'password'
    public boolean login(User user, HttpSession session) {
       User user2 = userRepository.userLogin(user);
      if(user2 != null){
          System.out.println(user2.getId());
           session.setAttribute("user",user2);

          return true;
      }else{
          return false;

      }
    }



}
