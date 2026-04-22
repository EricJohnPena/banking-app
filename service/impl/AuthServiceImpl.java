package service.impl;

import dao.UserDAO;
import model.User;
import service.AuthService;

public class AuthServiceImpl implements AuthService{
    private UserDAO userDAO;
    public AuthServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public User login(String number, int pin){
       User user = userDAO.findUser(number, pin);
       if (user != null) {
           System.out.println("Login successful for user: " + user.getName());
           return user;
       } else {
           System.out.println("Login failed. Invalid number or PIN.");
           return null;
       }
    }

    public User register(String name, String email, String number, int pin){
        User user = userDAO.createUser(name, email, number, pin);
        if (user != null) {
           System.out.println("Register successful for new user: " + user.getName());
           return user;
       } else {
           System.out.println("Login failed. Invalid number or PIN.");
           return null;
       }
    }
}
