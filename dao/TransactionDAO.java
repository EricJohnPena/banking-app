package dao;

import model.Transaction;


import java.util.List;

public interface TransactionDAO {
    void insertTransaction(double amount, String type, String toNumber, String fromNumber, int userId);
    List<Transaction> viewUserTransactions(int id);
}
