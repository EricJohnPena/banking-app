package service.impl;

import dao.UserDAO;
import model.User;
import service.UserAuthentication;

public class UserAuthenticationImpl implements UserAuthentication {
    private UserDAO userDAO;
    public UserAuthenticationImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public User login(String number, String pin){
       User user = userDAO.findUser(number, pin);
       if (user != null) {
           System.out.println("LoginForm successful for user: " + user.getName());
           return user;
       } else {
           System.out.println("LoginForm failed. Invalid number or PIN.");
           return null;
       }
    }

    public void register(String name, String email, String number, String pin){
        userDAO.createUser(name, email, number, pin);
    }

    public void changePin(User user, String newPin) {
       if (user.isValidPin(newPin))
        userDAO.updatePin(user, newPin);
       else
        System.out.println("Invalid PIN input");
    }

    public void logout(User user){
        user = null;
    }
}
