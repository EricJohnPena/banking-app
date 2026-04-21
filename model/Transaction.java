package model;

import java.util.Date;

public class Transaction {
    int id;
    double amount;
    String name;
    int accountID;
    Date date;
    int transferToID;
    int transferFromID;
    
    public Transaction(int id, double amount, String name, int accountID, Date date, int transferToID,
            int transferFromID) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.accountID = accountID;
        this.date = date;
        this.transferToID = transferToID;
        this.transferFromID = transferFromID;
    }
    public int getId() {
        return id;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAccountID() {
        return accountID;
    }
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getTransferToID() {
        return transferToID;
    }
    public void setTransferToID(int transferToID) {
        this.transferToID = transferToID;
    }
    public int getTransferFromID() {
        return transferFromID;
    }
    public void setTransferFromID(int transferFromID) {
        this.transferFromID = transferFromID;
    }
}
