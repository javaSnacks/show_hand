package com.loho.show_hand;

public class Shuffle {
    //模拟一次洗牌
    public static int[] shuffleCards(Cards cards){
        int tmp;
        int index;
        int[] poker = cards.poker;
        int length = poker.length;
        for (int i = 0; i < length; i++) {
            index = i+(int)(Math.random()*(length-i));
            tmp = poker[i];
            poker[i] = poker[index];
            poker[index] = tmp;
        }
        return poker;
    }
}
