package com.loho.show_hand;
/**
 * 得到该牌型的类型（定级）以及两副牌比较的时候比较两副牌的level
 */

import java.util.Arrays;

class GetType {
    private int[] poker = new int[5];
    private boolean hasGhost = false;
    private int[] nums = new int[5];
    private char[] colors = new char[5];
    private int index = 0;

    //得到该扑克的一些信息
    private int[] getPokerInfo(int[] poker) {
        int[] nums = new int[5];
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
        this.nums = nums;
        return nums;
    }

    //得到该扑克的类型，if中的判断由定级函数完成
    private int getPokerType(int[] poker) {
        this.poker = poker;
        this.nums = new int[5];
        this.colors = new char[5];
        nums = getPokerInfo(poker);
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

    //同为同花顺的比较
    private int nineOrFiveLevel(int[] poker) {
        getPokerInfo(poker);
        int[] nums = this.nums;
        boolean hasGhost = this.hasGhost;
        int index = this.index;
        int level = -1;
        if (hasGhost) {
            if (nums[4] == 5) {
                level = 15;
            }
            if (index == 1) {
                if (nums[4] == 13 && nums[1] == 10) {
                    level = 14;
                } else {
                    level = nums[4];
                }
            } else if (index == 2) {
                if (nums[4] - nums[2] == 2 && nums[4] < 13) {
                    level = nums[4] + 2;
                } else if (nums[4] - nums[2] == 2 && nums[4] == 13) {
                    level = 14;
                } else if (nums[4] - nums[2] == 3) {
                    level = nums[4] + 1;
                } else if (nums[4] - nums[2] == 4) {
                    level = nums[4];
                }
            } else if (index == 3) {
                if (nums[4] > 11 && nums[3] > 9) {
                    level = 14;
                } else if (nums[4] - nums[3] == 4) {
                    level = 13;
                } else if (nums[4] - nums[3] == 3) {
                    level = nums[4] + 1;
                } else if (nums[4] - nums[3] == 2) {
                    level = nums[4] + 2;
                } else if (nums[4] - nums[3] == 1) {
                    level = nums[4] + 3;
                }
            }
        } else {
            if (nums[0] == 1 && nums[4] == 5) {
                level = 15;
            } else if (nums[0] == 1 && nums[4] == 13) {
                level = 14;
            } else {
                level = nums[4];
            }
        }
        return level;
    }

    //  同为葫芦的比较，其中花色的比较没写出来
    private int[] sevenLevel(int[] poker) {
        getPokerInfo(poker);
        int[] nums = this.nums;
        boolean hasGhost = this.hasGhost;
        int index = this.index;
        int threeSameNum = -1;
        int twoSameNum = -1;
        if (!hasGhost) {
            threeSameNum = nums[2];
            if (threeSameNum == nums[0]) {
                twoSameNum = nums[4];
            } else {
                twoSameNum = nums[0];
            }
        } else if (index == 1) {
            if (nums[1] == nums[2] && nums[2] == nums[3]) {
                threeSameNum = nums[1];
                twoSameNum = nums[4];
            } else {
                threeSameNum = nums[4];
                twoSameNum = nums[1];
            }
        }
        int[] ans = new int[2];
        ans[0] = threeSameNum;
        ans[1] = twoSameNum;
        return ans;
    }

    //同花的时候得到其中的一个非鬼牌，数越大花色越大
    private int getSameColor(int[] poker) {
        int sameColor = -1;
        for (int i = 0; i < 5; i++) {
            if (poker[i] < 53) {
                sameColor = poker[i];
                break;
            }
        }
        return sameColor;
    }

    //比较三条的level
    private int threeIdenticalLevel(int[] poker) {
        getPokerInfo(poker);
        int[] nums = this.nums;
        boolean hasGhost = this.hasGhost;
        int index = this.index;
        int level = -1;
        if (!hasGhost) {
            for (int i = 1; i < 5; i++) {
                if (nums[i] == nums[i - 1]) {
                    level = nums[i];
                    break;
                }
            }
        } else if (index == 1) {
            for (int i = 1; i < 5; i++) {
                if (nums[i] == nums[i - 1]) {
                    level = nums[i];
                    break;
                }
            }
            if (level == -1) {
                level = nums[4];
            }
        } else {
            level = nums[4];
        }
        return level;
    }

    //比较两对的level
    private int[] twoPairLevel(int[] poker) {
        //大对的数，小对的数，大对的花色，
        int[] ans = new int[3];
        getPokerInfo(poker);
        if (!hasGhost) {
            if (nums[4] == nums[3]) {
                ans[0] = nums[4];
                if (nums[0] == nums[1]) {
                    ans[1] = nums[0];
                } else {
                    ans[1] = nums[2];
                }
            } else {
                ans[0] = nums[3];
                ans[1] = nums[1];
            }
            int max = -1;
            for (int i = 0; i < 5; i++) {
                if ((poker[i] + 12) % 13 + 1 == ans[0] && max < poker[i]) {
                    max = poker[i];
                }
            }
            ans[2] = max;
        }
        return ans;
    }

    //比较单对的level
    private int[] onePairLevel(int[] poker) {
        int[] ans = new int[2];
        getPokerInfo(poker);
        if (!hasGhost) {
            for (int i = 1; i < 5; i++) {
                if (nums[i] == nums[i - 1]) {
                    ans[0] = nums[i];
                    break;
                }
            }
        } else {
            ans[0] = nums[4];
        }
        //比较花色的时候没有用鬼模拟的花色，用的是原有的最大的花色
        int max = -1;
        for (int i = 0; i < 5; i++) {
            if ((poker[i] + 12) % 13 + 1 == ans[0] && max < poker[i]) {
                max = poker[i];
            }
        }
        ans[1] = max;
        return ans;
    }

    //比较散牌的level
    private int[] singleLevel(int[] poker) {
        int[] ans = new int[2];
        getPokerInfo(poker);
        ans[0] = nums[4];
        int max = -1;
        for (int i = 0; i < 5; i++) {
            if ((poker[i] + 12) % 13 + 1 == ans[0] && max < poker[i]) {
                max = poker[i];
            }
        }
        ans[1] = max;
        return ans;
    }

    //比较level
    int compareSize(int[] poker1, int[] poker2) {
        GetType getType = new GetType();
        int type1 = getType.getPokerType(poker1);
        int[] nums1 = getType.nums;
        boolean hasGhost1 = getType.hasGhost;
        int index1 = getType.index;
        int type2 = getType.getPokerType(poker2);
        int[] nums2 = getType.nums;
        int index2 = getType.index;
        boolean hasGhost2 = getType.hasGhost;

        if (type1 > type2) {
            return 1;
        } else if (type1 < type2) {
            return -1;
        } else {
            if (type1 == 10) {
                if (nums1[4] > nums2[4]) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (type1 == 9) {
                int max1 = nineOrFiveLevel(nums1);
                int max2 = nineOrFiveLevel(nums2);
                if (max1 > max2) {
                    return 1;
                } else if (max1 < max2) {
                    return -1;
                } else {
                    return getSameColor(poker1) > getSameColor(poker2) ? 1 : -1;
                }
            } else if (type1 == 8) {
                if (nums1[3] > nums2[3]) {
                    return 1;
                } else if (nums1[3] < nums2[3]) {
                    return -1;
                } else {
                    if (nums1[4] > nums2[4]) {
                        return 1;
                    } else if (nums1[4] < nums2[4]) {
                        return -1;
                    } else {
                        return getSameColor(poker1) > getSameColor(poker2) ? 1 : -1;
                    }
                }
            } else if (type1 == 7) {
                int[] first = sevenLevel(poker1);
                int[] second = sevenLevel(poker2);
                if (first[0] != second[0]) {
                    return first[0] > second[0] ? 1 : -1;
                } else {
                    return first[1] > second[1] ? 1 : -1;
                }
            } else if (type1 == 6) {
                for (int i = 0; i < 5; i++) {
                    if (nums1[4 - i] > nums2[4 - i]) {
                        return 1;
                    } else if (nums1[4 - i] < nums2[4 - i]) {
                        return -1;
                    }
                }
            } else if (type1 == 5) {
                int max1 = nineOrFiveLevel(nums1);
                int max2 = nineOrFiveLevel(nums2);
                if (max1 > max2) {
                    return 1;
                } else if (max1 < max2) {
                    return -1;
                } else {
                    //比较最大的那张牌的花色

                }
            } else if (type1 == 4) {
                return threeIdenticalLevel(poker1) > threeIdenticalLevel(poker2) ? 1 : -1;
            } else if (type1 == 3) {
                int[] one = twoPairLevel(poker1);
                int[] two = twoPairLevel(poker2);
                for (int i = 0; i < 3; i++) {
                    if (one[i] > two[i]) {
                        return 1;
                    } else if (one[i] < two[i]) {
                        return -1;
                    }
                }
            } else if (type1 == 2) {
                int[] one = onePairLevel(poker1);
                int[] two = onePairLevel(poker2);
                for (int i = 0; i < 2; i++) {
                    if (one[i] > two[i]) {
                        return 1;
                    } else if (one[i] < two[i]) {
                        return -1;
                    }
                }
            } else if (type1 == 1) {
                int[] one = singleLevel(poker1);
                int[] two = singleLevel(poker2);
                for (int i = 0; i < 2; i++) {
                    if (one[i] > two[i]) {
                        return 1;
                    } else if (one[i] < two[i]) {
                        return -1;
                    }
                }
            }
        }
        return 0;
    }

    //散牌定级
    private boolean isSingle() {
        return true;
    }

    //单对定级
    private boolean isOnePair() {
        if (!hasGhost) {
            for (int i = 1; i < 5; i++) {
                if (nums[i] == nums[i - 1]) {
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }

    //二对定级
    private boolean isTwoPair() {
        if (!hasGhost) {
            return (nums[0] == nums[1] && nums[3] == nums[4]) || (nums[1] == nums[2] && nums[3] == nums[4]) || (nums[0] == nums[1] && nums[2] == nums[3]);
        } else {
            if (index == 1) {
                for (int i = 2; i < 5; i++) {
                    if (nums[i] == nums[i - 1]) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        }
    }

    //三条定级
    private boolean isThreeIdentical() {
        if (!hasGhost) {
            int sameNum = nums[2];
            return (sameNum == nums[0] && sameNum == nums[1]) || (sameNum == nums[1] && sameNum == nums[3]) || (sameNum == nums[3] && sameNum == nums[4]);
        } else {
            int index = 0;
            for (int i = 0; i < 5; i++) {
                if (nums[i] == -1) {
                    index++;
                } else {
                    break;
                }
            }
            if (index == 2) {
                return true;
            } else {
                return (nums[1] == nums[2]) || (nums[2] == nums[3]) || (nums[3] == nums[4]);
            }
        }
    }

    //顺子定级
    private boolean isFiveConsecutive() {
        if (!hasGhost) {
            for (int i = 1; i < 5; i++) {
                if (nums[i] != nums[i - 1] + 1) {
                    return false;
                }
            }
            return true;
        } else {
            //index为最后一个鬼的索引的后一个位置
            for (int i = index; i < 5; i++) {
                if (nums[i] == nums[i - 1]) {
                    return false;
                }
            }
            if (index == 1) {
                return nums[4] - nums[1] < 5;
            } else if (index == 2) {
                return nums[4] - nums[2] < 5;
            } else if (index == 3) {
                if (nums[3] != 1) {
                    return nums[4] - nums[3] < 5;
                } else {
                    return nums[4] < 6 || nums[4] > 9;
                }
            }
            return false;
        }
    }

    //同花定级
    private boolean isSameColor() {
        if (!hasGhost) {
            char tmpColor = colors[0];
            for (char c :
                    colors) {
                if (c != tmpColor) {
                    return false;
                }
            }
            return true;
        } else {
            char tmpColor = 'N';
            for (int i = 0; i < 5; i++) {
                if (tmpColor == 'N' && colors[i] != 'G') {
                    tmpColor = colors[i];
                }
                if (tmpColor != 'N' && colors[i] != tmpColor) {
                    return false;
                }
            }
            return true;
        }
    }

    //葫芦定级
    private boolean isThreeIdenticalAndOnePair() {
        if (!hasGhost) {
            return nums[0] == nums[1] && nums[3] == nums[4] && (nums[2] == nums[1] || nums[2] == nums[3]);
        } else {
            for (int i = 1; i < 5; i++) {
                if (nums[i] == nums[i - 1]) {
                    return false;
                }
            }
            if (index == 2) {
                return nums[3] == nums[2] || nums[3] == nums[4];
            } else {
                return nums[1] == nums[2] && nums[3] == nums[4];
            }
        }
    }

    //四条定级
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

    //同花顺定级
    private boolean isFiveConsecutiveAndSameColor() {
        int[] tmpInt = new int[]{1, 10, 11, 12, 13};
        for (int i = 0; i < 5; i++) {
            if (tmpInt[i] != nums[i]) {
                return isFiveConsecutive() && isSameColor();
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

    //五条定级
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

    //五鬼定级
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
