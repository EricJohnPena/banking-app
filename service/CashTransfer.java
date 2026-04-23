package service;

import model.Accounts;

public interface CashTransfer {
    Accounts cashTransfer(int userID, String recipientNumber, double amount);
}
