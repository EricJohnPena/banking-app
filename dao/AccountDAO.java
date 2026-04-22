package dao;

import model.Accounts;

public interface AccountDAO {
    Accounts findAccount(int userID);
}
