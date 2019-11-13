package com.loho.show_hand;

public class Person {
    String type;
    int money;
    int[] poker = new int[5];

    Person() {
    }

    Person(String type) {
        this.type = type;
        money = 1000;
    }

    public void setPoker(int[] poker) {
        this.poker = poker;
    }

    public int[] getPoker() {
        return poker;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
