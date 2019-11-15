package com.loho.show_hand;

import java.util.HashSet;
import java.util.Set;

public class TestTwoCompareMethod {
    public static void main(String[] args) {
        Cards cards = new Cards();
        GetType getType = new GetType();
        GetTypeBaseGhostNums getTypeBaseGhostNums = new GetTypeBaseGhostNums();
        int count = 0;
        while (true) {
            if (count > 10000) {
                break;
            }
            count++;
            int[] poker = Shuffle.shuffleCards(cards);
            int[] testPoker1 = new int[5];
            int[] testPoker2 = new int[5];
            for (int i = 0; i < 5; i++) {
                testPoker1[i] = poker[20+i];
                testPoker2[i] = poker[30+i];
            }
            for (int k = 0; k < 5; k++) {
                System.out.print(testPoker1[k] + ",");
            }
            System.out.println();
            for (int k = 0; k < 5; k++) {
                System.out.print(testPoker2[k] + ",");
            }
            System.out.println();
            int one = getType.compareSize(testPoker1,testPoker2);
            System.out.println();
            int two = getTypeBaseGhostNums.compareTwoPoker(testPoker1,testPoker2);
            System.out.println("\n"+one + "**--++" + two);
            if (one != two) {
                System.out.println("count = " + count);
                break;
            }
        }
        System.out.println();
    }
}
