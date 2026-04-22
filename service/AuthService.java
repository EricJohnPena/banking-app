package service;

import model.User;

public interface AuthService {
    User login(String number, int pin);
    User register(String name, String email, String number, int pin);
}
