package com.loho.show_hand;

public class TestGetTypeBaseGhostNums {
    public static void main(String[] args) {
        int[] nums1 = new int[]{46,3,25,19,32};
        int[] nums2 = new int[]{54,1,45,29,28};
        int type = 0;
        int level = 0;
        GetTypeBaseGhostNums getTypeBaseGhostNums = new GetTypeBaseGhostNums();
//        type = getTypeBaseGhostNums.getPokerType(nums);
//        level = getTypeBaseGhostNums.level;
//        System.out.println(type+"--"+level);
        int tmp = getTypeBaseGhostNums.compareTwoPoker(nums1,nums2);
        System.out.println("\n結果是:"+tmp);
    }
}
