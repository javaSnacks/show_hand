package com.loho.better_show_hand;

import java.util.Comparator;

public class Card implements Comparable {
    Integer num;
    Character color;

    Card() {
    }

    Card(int num, char color) {
        this.num = num;
        this.color = color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getColor() {
        return color;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return num + color + " ";
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Card) {
            Card card = (Card) o;
            int i = this.num.compareTo(card.num);
            if (i == 0) {
                return this.color.compareTo(card.color);
            } else {
                return i;
            }
        }
        return 0;
    }
}
