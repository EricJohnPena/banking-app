package service;

import model.User;

public interface AuthService {
    User login(String number, String pin);
    User register(String name, String email, String number, String pin);
    void changePin(User user, String newPin);
}
