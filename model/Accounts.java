package model;

public class Accounts {
    int id;
    double amount;
    int userID;

    public Accounts(int id, double amount, int userID) {
        this.id = id;
        this.amount = amount;
        this.userID = userID;
    }
    
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

}
