package service;

import model.Accounts;

public interface CashTransfer {
    void cashTransfer(int userID, String recipientNumber, String senderNumber, double amount);
}
