package com.loho.show_hand;

public class Card {
    int num;
    int color;

    public Card(){}

    public Card(int num,int color){
        this.num = num;
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {

        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}

