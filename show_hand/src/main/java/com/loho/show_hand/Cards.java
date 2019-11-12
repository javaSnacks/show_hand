package com.loho.show_hand;

public class Cards {
    int[] poker = new int[57];
    //初始化扑克牌，1-13，14-26，27-39，40-52分别代表Club(草花) < Diamond(方块) < Heart(红桃) < Spade (黑桃)，53-57代表ghost（鬼）
    public Cards(){
        for (int i = 0; i< poker.length; i++){
            poker[i] = i+1;
        }
    }
}
