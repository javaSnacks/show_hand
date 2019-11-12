package com.loho.show_hand;

public class Test {
    public static void main(String[] args) {
//        Deal.deal(new Cards());
        GetType test = new GetType();
        int i = test.getPokerType(new int[]{1,14,2,3,16});
        System.out.println(i);
    }
}
