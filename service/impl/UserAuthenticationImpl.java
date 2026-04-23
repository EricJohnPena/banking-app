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
       return userDAO.findUser(number, pin);

    }

    public void register(String name, String email, String number, String pin){
        if(userDAO.isExist("users","email",email))
            throw new RuntimeException("Email already exists");
        if (userDAO.isExist("users","number",number))
            throw new RuntimeException("Mobile number already exists");
        userDAO.createUser(name, email, number, pin);
    }

    public void changePin(User user, String newPin) {
        userDAO.updatePin(user, newPin);

    }

    public void logout(User user){
        user = null;
    }
}
