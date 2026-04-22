package model;

public class Accounts {
    private int id;
    private double amount;
    private int userID;

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

    public void deposit(double amount) {
        if(amount > 0) {
            this.amount += amount;
        }else {
            System.out.println("Deposit amount must be positive.");
        }
    }
    public void withdraw(double amount) {
        if(amount > 0 && amount <= this.amount) {
            this.amount -= amount;
        }else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

}
