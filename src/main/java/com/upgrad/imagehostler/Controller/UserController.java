package com.upgrad.imagehostler.Controller;

import com.upgrad.imagehostler.Model.User;
import com.upgrad.imagehostler.Model.UserProfile;
import com.upgrad.imagehostler.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //This controller method is called when the request pattern is of type 'users/registration'
    @RequestMapping("users/registration")
    public String registration(Model model) {
        User user = new User();
        UserProfile profile = new UserProfile();
        user.setProfile(profile);
        model.addAttribute("User", user);
        return "users/registration";
    }

    //This controller method is called when the request pattern is of type 'users/registration' and also the incoming request is of POST type
    @RequestMapping(value = "users/registration", method = RequestMethod.POST)
    public String registerUser(User user,Model model) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
         if(user.getPassword().matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])([a-zA-Z0-9@#$%^&+=!]+)$")){
             userService.registerUser(user);

             return "redirect:/users/login";

         }else{
             String error = "Password must contain atleast 1 alphabet, 1 number & 1 special character";

System.out.println(error);

             model.addAttribute("passwordTypeError",error);
             User user1 = new User();
             UserProfile profile = new UserProfile();
             user1.setProfile(profile);
             model.addAttribute("User", user1);
             return "users/registration";

         }
    }

    //This controller method is called when the request pattern is of type 'users/login'
    @RequestMapping("users/login")
    public String login() {
        return "users/login";
    }

    //This controller method is called when the request pattern is of type 'users/login' and also the incoming request is of POST type
    @RequestMapping(value = "users/login", method = RequestMethod.POST)
    public String loginUser(HttpSession session,User user,Model model) {

        boolean userExists = userService.login(user,session);
        if (userExists) {
            return "redirect:/images";
        } else {
            return "users/login";
        }
    }
}
