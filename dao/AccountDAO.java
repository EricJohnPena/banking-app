package dao;

import model.Accounts;

public interface AccountDAO {
    Accounts findAccount(int userID);
    Accounts updateCash(int userID, double amount);
    Accounts cashOut(int userID, double amount);
}
