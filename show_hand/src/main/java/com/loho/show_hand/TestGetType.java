package com.loho.show_hand;

import java.util.Arrays;

public class TestGetType {

    public static void main(String[] args) {
        //test
//        String s1 = "6C  4H  AS  5C  4C ";
//        InversePoker.inverse(s1);
        GetType getType = new GetType();
//        int ans = -2;
//        ans = getType.getPokerType(new int[]{24,22,34,48,8});
//        System.out.println(ans);
        int[] one = new int[]{16,51,31,29,42};
        int[] two = new int[]{46,40,1,24,27};
        int ans = getType.compareSize(one,two);
        System.out.println(ans);

    }
}
