package model;

import java.util.Date;

public class Transaction {
    private int id;
    private double amount;
    private String type;
    private int accountID;
    private Date date;
    private String transferToNumber;
    private String transferFromNumber;
    
    public Transaction(int id, double amount, String type, int accountID, Date date, String transferToNumber,
                       String transferFromNumber) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.accountID = accountID;
        this.date = date;
        this.transferToNumber = transferToNumber;
        this.transferFromNumber = transferFromNumber;
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
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
    public String getTransferToNumber() {
        return transferToNumber;
    }
    public void setTransferToNumber(String transferToNumber) {
        this.transferToNumber = transferToNumber;
    }
    public String getTransferFromNumber() {
        return transferFromNumber;
    }
    public void setTransferFromNumber(String transferFromNumber) {
        this.transferFromNumber = transferFromNumber;
    }
}
