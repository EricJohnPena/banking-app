package dao;

public interface TransactionDAO {
    void insertTransaction(double amount, String type, String toNumber, String fromNumber, int userId);

}
