package com.zsy.demo.unitTest.sample;

/**
 * Created by zhuoshangyi on 2016/11/12.
 */
public class Account {
    private String owner;
    private int balance;

    public Account(String owner, int balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public String getOwner() { return owner; }
    public int getBalance() { return balance; }
    public boolean isOverdrawn() { return balance < 0; }

    public void withdraw(int amount) { balance -= amount; }
    public void deposit(int amount) { balance += amount; }
}
