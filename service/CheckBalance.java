package service;

import model.Accounts;

public interface CheckBalance {
    Accounts check(int userID);
}
