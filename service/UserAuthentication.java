package service;

import model.User;

public interface UserAuthentication {
    User login(String number, String pin);
    void register(String name, String email, String number, String pin);
    void changePin(User user, String newPin);
    void logout(User user);
}
