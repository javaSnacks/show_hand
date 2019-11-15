package com.loho.show_hand;

import java.util.Comparator;

public class Cards implements Comparator {
    int[] poker = new int[57];

    //初始化扑克牌，1-13，14-26，27-39，40-52分别代表Club(草花) < Diamond(方块) < Heart(红桃) < Spade (黑桃)，53-57代表ghost（鬼）
    public Cards() {
        for (int i = 0; i < poker.length; i++) {
            poker[i] = i + 1;
        }
    }

    @Override
    public int compare(Object o1, Object o2) {
        int i = (int) o1;
        int j = (int) o2;
        if ((i - 1) % 13 > (j - 1) % 13) {
            return 1;
        } else if ((i - 1) % 13 < (j - 1) % 13) {
            return -1;
        } else {
            if (i > j) {
                return 1;
            } else {
                return -1;
            }
        }

    }
}
