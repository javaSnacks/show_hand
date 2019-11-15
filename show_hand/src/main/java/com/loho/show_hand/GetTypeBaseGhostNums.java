package com.loho.show_hand;

import java.util.Arrays;

/**
 * 第二种方法：先判断鬼的个数，同时将同级别比较的level算出来，由于有的牌型不能通过一个level比较出来大小，这种情况就比较leves数组。
 */
public class GetTypeBaseGhostNums {
    //直白的扑克数字数组
    private int[] nums = new int[5];
    //原始的扑克牌数组
    private int[] poker = new int[5];
    //扑克牌的花色
    private int[] color = new int[5];
    //相同type比较level的值
    int level = 0;
    //鬼的数量
    int ghostNum = 0;
    //是否是同花
    boolean isSameColor = false;
    //能否构成顺子
    boolean isConsecutive = false;
    //判断非鬼牌是否有重复数字
    boolean hasSameNum = false;
    //只比较level不能得出结果的
    int[] levels = new int[3];


    //得到该扑克的数字数组并排序，鬼为-1在最前边,
    private void getClearPokerNum(int[] poker) {
        int[] nums = new int[5];
        //此次排序是为了让花色大的牌放在后边，当数字相同时比较花色只需要比较后边的牌就可以了
        Arrays.sort(poker);
        for (int i = 0; i < 5; i++) {
            if (poker[i] > 52) {
                nums[i] = -1;
            } else {
                nums[i] = (poker[i] + 12) % 13 + 1;
            }
            if (i != 4 && nums[i] == nums[i + 1]) {
                hasSameNum = true;
            }
            color[i] = (poker[i] + 12) / 13;
        }
        insertSort(nums, color);
    }

    //插入排序，同时排序简明的数字牌和牌的花色
    void insertSort(int[] arr, int[] poker) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int temp2 = poker[i];
            int indx = 0;
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    arr[j] = arr[j - 1];
                    poker[j] = poker[j - 1];
                    indx = j - 1;
                } else {
                    break;
                }
                arr[indx] = temp;
                poker[indx] = temp2;
            }
        }
        this.poker = poker;
        this.nums = arr;
    }

    //五张牌中有几个鬼
    private int getGhostNums(int[] poker) {
        int ghostNum = 0;
        for (int i = 0; i < 5; i++) {
            if (poker[i] > 52) {
                ghostNum++;
            }
        }
        this.ghostNum = ghostNum;
        return ghostNum;
    }

    //这五张牌属于什么类型
    int getPokerType(int[] poker) {
        int ghostNum = getGhostNums(poker);
        getClearPokerNum(poker);
        int type = 0;
        if (ghostNum == 5) {
            type = 11;
            return type;
        }
        isConsecutive = false;
        isSameColor = false;
        isSameColor(color);
        if (!hasSameNum) {
            isConsecutive(nums);
        }
        if (ghostNum == 4) {
            type = 10;
            level = nums[4];
        } else if (ghostNum == 3) {
            if (nums[3] == nums[4]) {
                level = nums[4];
                return 10;
            } else {
                if (nums[3] == 1) {
                    if (nums[4] <= 5) {
                        level = 15;
                    } else if (nums[4] >= 10) {
                        level = 14;
                    } else {
                        level = 14;
                        return 8;
                    }
                    if (isSameColor) {
                        return 9;
                    } else {
                        level = 14;
                        return 8;
                    }
                } else {
                    if (isSameColor) {
                        if (nums[3] >= 10) {
                            level = 14;
                            return 9;
                        } else if (nums[4] - nums[3] == 4) {
                            level = nums[4];
                            return 9;
                        } else if (nums[4] - nums[3] == 3) {
                            level = nums[4] + 1;
                            return 9;
                        } else if (nums[4] - nums[3] == 2) {
                            level = nums[4] + 2;
                            return 9;
                        } else if (nums[4] - nums[3] == 1) {
                            level = nums[4] + 3;
                            return 9;
                        }
                    }
                    level = nums[4];
                    return 8;

                }
            }
        } else if (ghostNum == 2) {
            //有无重复数字
            if (nums[2] == nums[3] && nums[3] == nums[4]) {
                level = nums[4];
                return 10;
            } else if (nums[2] == nums[3] || nums[3] == nums[4]) {
                level = nums[3];
                return 8;
            } else {
                int tmp = isNineSixFive();
                if (tmp != 0) {
                    return tmp;
                }
                if (nums[2] == 1) {
                    level = 14;
                    levels[0] = color[2];
                    return 4;
                } else {
                    level = nums[4];
                    levels[0] = color[4];
                    return 4;
                }
            }
        } else if (ghostNum == 1) {
            //判断非鬼牌有多少重复数字
            if (nums[1] == nums[2] && nums[2] == nums[3] && nums[3] == nums[4]) {
                return 10;
            } else if ((nums[1] == nums[2] && nums[2] == nums[3]) || (nums[2] == nums[3] && nums[3] == nums[4])) {
                if (nums[2] == 1) {
                    level = 14;
                } else {
                    level = nums[2];
                }
                return 8;
            } else if (nums[1] == nums[2] && nums[3] == nums[4]) {
                if (nums[1] != 1) {
                    level = nums[4];
                } else {
                    level = 14;
                }
                levels[0] = nums[4];
                levels[1] = nums[2];
                levels[2] = color[4];
                return 7;
            } else if (nums[1] == nums[2] || nums[2] == nums[3] || nums[3] == nums[4]) {
                if (nums[1] == 1 && nums[2] == 1) {
                    level = 14;
                    levels[0] = color[2];
                } else {
                    int sameNum = -1;
                    if (nums[1] == nums[2] || nums[2] == nums[3]) {
                        sameNum = nums[2];
                    } else {
                        sameNum = nums[3];
                    }
                    level = sameNum;
                }
                for (int i = 4; i > 0; i--) {
                    if (nums[i] == nums[i-1]){
                        levels[0] = color[i];
                        break;
                    }
                }
                return 4;
            } else {
                int tmp = isNineSixFive();
                if (tmp != 0) {
                    return tmp;
                }
                level = nums[4];
                levels[0] = color[4];
                return 2;
            }
        } else if (ghostNum == 0) {
            if (nums[0] != nums[1] && nums[1] != nums[2] && nums[2] != nums[3] && nums[3] != nums[4]) {
                int temp = isNineSixFive();
                if (temp != 0) {
                    return temp;
                }
            }
            if (nums[0] == nums[3] || nums[1] == nums[4]) {
                if (nums[2] != 1) {
                    level = nums[2];
                } else {
                    level = 14;
                }
                return 8;
            }
            if (nums[0] == nums[1] && nums[3] == nums[4]) {
                if (nums[2] == nums[1]) {
                    if (nums[0] == 1) {
                        level = 14;
                    } else {
                        level = nums[3];
                    }
                    levels[0] = nums[2];
                    levels[1] = nums[4];
                    levels[2] = color[2];
                    return 7;
                } else if (nums[2] == nums[3]) {
                    level = nums[3];
                    levels[0] = nums[4];
                    levels[1] = nums[1];
                    levels[2] = color[4];
                    return 7;
                }
            }
            if (nums[0] == nums[2] || nums[1] == nums[3] || nums[2] == nums[4]) {
                if (nums[0] == nums[2] && nums[0] == 1) {
                    level = 14;
                } else {
                    level = nums[2];
                }
                for (int i = 4; i > 0; i--) {
                    if (nums[i] == nums[i-1]){
                        levels[0] = color[i];
                        break;
                    }
                }
                return 4;
            }
            if ((nums[0] == nums[1] && nums[2] == nums[3]) || (nums[0] == nums[1] && nums[3] == nums[4]) || (nums[1] == nums[2] && nums[3] == nums[4])) {
                level = nums[3];
                if (nums[3] == nums[4]){
                    levels[1] = color[4];
                }else{
                    levels[1] = color[3];
                }
                levels[0] = nums[1];
                return 3;
            }
            if ((nums[0] == nums[1]) || (nums[1] == nums[2]) || (nums[2] == nums[3]) || (nums[3] == nums[4])) {
                if ((nums[0] == nums[1]) || (nums[1] == nums[2])) {
                    level = nums[1];
                } else {
                    level = nums[3];
                }
                for (int i = 1; i < 5; i++) {
                    if (nums[i]==nums[i-1]){
                        levels[0] = color[i];
                        break;
                    }
                }
                return 2;
            }
            level = nums[4];
            levels[0] = color[4];
            return 1;
        }
        return type;
    }

    //单比较level就能出结果
    int compareLevel(int poker1Level, int poker2Level) {
        if (poker1Level > poker2Level) {
            return 1;
        } else if (poker1Level < poker2Level) {
            return -1;
        } else {
            return 0;
        }
    }

    //比较两副牌谁大谁小
    int compareTwoPoker(int[] poker1, int[] poker2) {
        int poker1Type = getPokerType(poker1);
        int poker1Level = level;
        int[] nums1 = nums;
        int[] colors1 = color;
        int[] levels1 = Arrays.copyOf(this.levels,5);
        int poker2Type = getPokerType(poker2);
        int poker2Level = level;
        int[] nums2 = nums;
        int[] colors2 = color;
        int [] levels2 = Arrays.copyOf(this.levels,5);
//        System.out.println("poker1Level:" + poker1Level + " poker2Level:" + poker2Level);
//        System.out.println("levels1:");
//        for (int i = 0; i < 3; i++) {
//            System.out.print(levels1[i]+" ");
//        }
//        System.out.println("\nlevels2:");
//        for (int i = 0; i < 3; i++) {
//            System.out.print(levels2[i]+" ");
//        }
        if (poker1Type > poker2Type) {
            return 1;
        } else if (poker1Type < poker2Type) {
            return -1;
        } else {
            int compareLevel = compareLevel(poker1Level, poker2Level);
            if (poker1Type == 9 || poker1Type == 8) {
                return compareLevel;
            } else if (poker1Type == 6){
                if (compareLevel != 0) {
                    return compareLevel;
                } else{
                    for (int i = 0; i < 5; i++) {
                        if (nums1[4-i]>nums2[4-i]){
                            return 1;
                        }else if (nums1[4-i]<nums2[4-i]){
                            return -1;
                        }
                    }
                }
                return  0;
            }else {
                if (compareLevel != 0) {
                    return compareLevel;
                } else {
                    for (int i = 0; i < 3; i++) {
                        if (levels1[i]>levels2[i]){
                            return 1;
                        }else if (levels1[i]<levels2[i]){
                            return -1;
                        }
                    }
                    return 0;
                }
            }
        }
    }

    //判断是否为同花顺，同花，顺子
    int isNineSixFive() {
        //判断是否为同花
        if (isSameColor(color)) {
            //判断能否凑成连续
            if (isConsecutive) {
                return 9;
            } else {
                return 6;
            }
        }
        if (isConsecutive) {
            if (nums[4]<=5){
                level = 15;
            }else if (nums[ghostNum]>=10){
                level = 14;
            }else{
                level = nums[4];
            }
            levels[0] = color[4];
            return 5;
        }
        return 0;
    }

    //判断是否能凑成连续牌,注意，使用的时候需判断非鬼牌是否有重复，没有重复方可用
    void isConsecutive(int[] poker) {
        //判断是否有1
        if (nums[ghostNum] == 1) {
            //判断最大值是5以内还是10以上
            if (nums[4] > 5 && nums[4] < 10) {

            } else {
                if (nums[4] <= 5) {
                    isConsecutive = true;
                    level = 15;
                } else if (nums[ghostNum + 1] >= 10) {
                    isConsecutive = true;
                    level = 14;
                }
            }
        } else {
            //判断最小值是否在10以上
            int tmp = nums[4] - nums[ghostNum];
            if (nums[ghostNum] >= 10) {
                isConsecutive = true;
                level = 14;
            } else if (tmp <= 4) {
                isConsecutive = true;
                level = nums[4] + 4 - tmp;
            }
        }
    }

    //判断是否是同花牌，只做了<3 个鬼的时候，因为大于等于3个鬼的时候用不到判断是否同色
    boolean isSameColor(int[] color) {
        int tmpColor = 0;
        if (ghostNum == 3) {
            if (color[4] == color[3]) {
                isSameColor = true;
                return true;
            }
            return false;
        } else if (ghostNum == 2) {
            if (color[3] == color[2] && color[3] == color[4]) {
                isSameColor = true;
                return true;
            }
            return false;
        } else if (ghostNum == 1) {
            tmpColor = color[1];
            for (int i = 2; i < 5; i++) {
                if (tmpColor != color[i]) {
                    return false;
                }
            }
            isSameColor = true;
            return true;
        } else {
            tmpColor = color[0];
            for (int i = 1; i < 5; i++) {
                if (tmpColor != color[i]) {
                    return false;
                }
            }
            isSameColor = true;
            return true;
        }
    }

}
