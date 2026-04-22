package service.impl;

import dao.AccountDAO;
import service.CheckBalance;


public class CheckBalanceImpl implements CheckBalance {
    private AccountDAO accountDAO;
    public CheckBalanceImpl(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    @Override
    public String checkBalance(int userID) {
        return "";
    }
}
