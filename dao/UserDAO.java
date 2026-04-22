package dao;

import model.User;

public interface UserDAO {
    
    User createUser(String name, String email, String number, int pin);
    User findUser(String number, int pin);
    void changePin(int userId, int newPin);
}
