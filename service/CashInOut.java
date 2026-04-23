package service;

import model.Accounts;

public interface CashInOut {
    void moveCash(int userID, double amount);
}
