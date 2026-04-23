package dao;

import model.Accounts;

public interface AccountDAO {
    Accounts findAccount(int userID);
    Accounts updateCash(double amount,  String type, int userID, int toID, int fromID);
    Accounts cashOut(int userID, double amount);
}
