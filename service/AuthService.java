package service;

import model.User;

public interface AuthService {
    User login(String number, int pin);
    
}
