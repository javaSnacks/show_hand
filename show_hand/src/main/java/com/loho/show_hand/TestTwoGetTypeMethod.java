package com.loho.show_hand;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestTwoGetTypeMethod {
    public static void main(String[] args) {
        Cards cards = new Cards();
        GetType getType = new GetType();
        GetTypeBaseGhostNums getTypeBaseGhostNums = new GetTypeBaseGhostNums();
        int count = 0;
        Set<Integer> set = new HashSet();
        while (true) {
            if (count > 10000000) {
                break;
            }
            count++;
            int[] poker = Shuffle.shuffleCards(cards);
            int[] testPoker = new int[5];
            for (int i = 0; i < 5; i++) {
                testPoker[i] = poker[20+i];
            }
            for (int k = 0; k < 5; k++) {
                System.out.print(testPoker[k] + ",");
            }
            System.out.println();
            int one = getType.getPokerType(testPoker);
            for (int k = 0; k < 5; k++) {
                System.out.print(testPoker[k] + ",");
            }
            System.out.println();
            int two = getTypeBaseGhostNums.getPokerType(testPoker);
            System.out.println(one + "**--++" + two);
            set.add(one);
            if (one != two) {
                System.out.println("count = " + count);
                break;
            }
        }
        System.out.println();
        for (int i :
                set) {
            System.out.print(i+" ");
        }
    }
}
