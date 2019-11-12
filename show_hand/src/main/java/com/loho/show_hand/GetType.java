package com.loho.show_hand;

import java.util.Arrays;

class GetType {
    private int[] poker;
    private boolean hasGhost = false;
    private int[] nums;
    private char[] colors;
    private static int index = 0;
    int getPokerType(int[] poker) {
        this.poker = poker;
        this.nums = new int[5];
        this.colors = new char[5];
        for (int i = 0; i < 5; i++) {
            if (poker[i] > 52) {
                hasGhost = true;
                nums[i] = -1;
            } else {
                nums[i] = (poker[i] + 12) % 13 + 1;
            }
            colors[i] = Deal.getColor(poker[i]);
        }
        Arrays.sort(nums);
        for (int i = 0; i < 5; i++) {
            if (nums[i] == -1) {
                index++;
            } else {
                break;
            }
        }
        int type = -1;
        if (isFiveGhost()) {
            type = 11;
        } else if (isFiveIdentical()) {
            type = 10;
        } else if (isFiveConsecutiveAndSameColor()) {
            type = 9;
        } else if (isFourIdentical()) {
            type = 8;
        } else if (isThreeIdenticalAndOnePair()) {
            type = 7;
        } else if (isSameColor()) {
            type = 6;
        } else if (isFiveConsecutive()) {
            type = 5;
        } else if (isThreeIdentical()) {
            type = 4;
        } else if (isTwoPair()) {
            type = 3;
        } else if (isOnePair()) {
            type = 2;
        } else if (isSingle()) {
            type = 1;
        }
        return type;
    }

    private boolean isSingle() {
        return true;
    }

    private boolean isOnePair() {
        if(!hasGhost){
            for (int i = 1; i < 5; i++) {
                if(nums[i]==nums[i-1]){
                    return true;
                }
            }
            return false;
        }else{
            return true;
        }
    }

    private boolean isTwoPair() {
        if(!hasGhost){
            return (nums[0]==nums[1]&&nums[3]==nums[4])||(nums[1]==nums[2]&&nums[3]==nums[4])||(nums[0]==nums[1]&&nums[2]==nums[3]);
        }else{
            if (index==1){
                for (int i = 2; i < 5; i++) {
                    if(nums[i]==nums[i-1]){
                        return true;
                    }
                }
                return false;
            }
            return true;
        }
    }

    private boolean isThreeIdentical() {
        if(!hasGhost){
            int sameNum = nums[2];
            return (sameNum==nums[0]&&sameNum==nums[1])||(sameNum==nums[1]&&sameNum==nums[3])||(sameNum==nums[3]&&sameNum==nums[4]);
        }else{
            int index = 0;
            for (int i = 0; i < 5; i++) {
                if (nums[i] == -1) {
                    index++;
                } else {
                    break;
                }
            }
            if (index == 2){
                return true;
            }else{
                return (nums[1]==nums[2])||(nums[2]==nums[3])||(nums[3]==nums[4]);
            }
        }
    }

    private boolean isFiveConsecutive() {
        if(!hasGhost){
            for (int i = 1; i < 5; i++) {
                if (nums[i]!=nums[i-1]+1){
                    return false;
                }
            }
            return true;
        }else{
            //index为最后一个鬼的索引的后一个位置
            for (int i = 1; i < 5; i++) {
                if(nums[i]==nums[i-1]){
                    return false;
                }
            }
            if (index==1){
                return nums[4] - nums[1] < 5;
            }else if (index==2){
                return nums[4] - nums[2] < 5;
            }
            return false;
        }
    }

    private boolean isSameColor() {
        if (!hasGhost){
            char tmpColor = colors[0];
            for (char c :
                    colors) {
                if (c!=tmpColor){
                    return false;
                }
            }
            return true;
        }else{
            char tmpColor = 'N';
            for (int i = 0; i < 5; i++) {
                if (tmpColor == 'N'&&colors[i]!='G'){
                    tmpColor = colors[i];
                }
                if (tmpColor !='N'&&colors[i]!=tmpColor){
                    return false;
                }
            }
            return true;
        }
    }

    private boolean isThreeIdenticalAndOnePair() {
       if (!hasGhost) {
            return nums[0] == nums[1] && nums[3] == nums[4] && (nums[2] == nums[1] || nums[2] == nums[3]);
        } else {
           for (int i = 1; i < 5; i++) {
               if(nums[i]==nums[i-1]){
                   return false;
               }
           }
           if (index==2) {
               return nums[3] == nums[2] || nums[3] == nums[4];
           }else{
                return nums[1] == nums[2] && nums[3] == nums[4];
           }
        }
    }

    private boolean isFourIdentical() {
        //相同的数字
        int sameNum = nums[3];
        //统计相同数字的个数
        int count = 0;
        if (!hasGhost) {
            for (int i :
                    nums) {
                if (sameNum == i) {
                    count++;
                }
            }
            return count > 3;
        } else {
            //index为最后一个鬼的索引的后一个位置。鬼的索引为-1，所以可以找到在数组中最后一个鬼的索引位置，然后根据这个位置去判断剩下来的牌能否构成当前牌型
            int index = 0;
            for (int i = 0; i < 5; i++) {
                if (nums[i] == -1) {
                    index++;
                } else {
                    break;
                }
            }
            if (index == 3) {
                return true;
            } else if (index == 2) {
                return nums[2] == sameNum || nums[4] == sameNum;
            } else if (index == 1) {
                return nums[2] == sameNum && (nums[1] == sameNum || nums[4] == sameNum);
            }
        }
        return false;
    }

    private boolean isFiveConsecutiveAndSameColor() {
        int[] tmpInt = new int[]{1,10,11,12,13};
        for (int i = 0; i < 5; i++) {
            if (tmpInt[i]!=nums[i]){
                return isFiveConsecutive()&&isSameColor();
            }
        }
        return isSameColor();
//        //N代表没有颜色
//        char color = 'N';
//        int max = -1;
//        int min = 100;
//        int sum = 0;
//        for (int i :
//                poker) {
//            if (i < 53 && color == 'N') {
//                color = Deal.getColor(i);
//            }
//            if (i < 53 && color != Deal.getColor(i)) {
//                return false;
//            } else if (i < 53) {
//                int tmp = ((i + 12) % 13 + 1);
//                if (max < tmp) {
//                    max = tmp;
//                }
//                if (min > tmp) {
//                    min = tmp;
//                }
//                sum += tmp;
//            }
//        }
//        if (hasGhost && (max - min) < 4) {
//            return true;
//        } else if (!hasGhost && (max - min) == 4) {
//            return true;
//        } else if (!hasGhost && min == 1 && max == 13 && sum == 47) {
//            return true;
//        } else if (hasGhost && min == 1) {
//            if (max < 6) {
//                return true;
//            } else {
//                int secMin = max;
//                for (int i :
//                        poker) {
//                    int tmp1 = ((i + 12) % 13 + 1);
//                    if (tmp1 != 1 && secMin > tmp1 && i < 53) {
//                        secMin = tmp1;
//                    }
//                }
//                return secMin > 9;
//            }
//        }
//        return false;
    }

    private boolean isFiveIdentical() {
        int sameNum = nums[4];
        for (int i :
                nums) {
            if (i == -1) {
                continue;
            }
            if (i != sameNum) {
                return false;
            }
        }
        return true;
    }

    private boolean isFiveGhost() {
        for (int i :
                poker) {
            if (i < 53) {
                return false;
            }
        }
        return true;
    }

}
