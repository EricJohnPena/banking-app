package service;

import model.Accounts;

public interface CashInOut {
    Accounts moveCash(int userID, double amount);
}
