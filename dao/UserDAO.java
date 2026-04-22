package dao;

import model.User;

public interface UserDAO {
    
    User createUser(String name, String email, String number, String pin);
    User findUser(String number, String pin);
    void updatePin(User user, String newPin);
}
