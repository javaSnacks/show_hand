package com.loho.show_hand;

public class InversePoker {
    public static void inverse(String s){
        String[] strings = s.split("  ");
        int[] nums = new int[]{0,0,0,0,0};
        for (int i = 0; i < 5; i++) {
            if (strings[i].charAt(0)=='G'){
                nums[i] = 53;
            }else{
                if (strings[i].charAt(1)=='D'){
                    nums[i]+=13;
                }else if (strings[i].charAt(1)=='H'){
                    nums[i]+=26;
                }else if (strings[i].charAt(1)=='S'){
                    nums[i]+=39;
                }
                nums[i]+=convert(strings[i].charAt(0));
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(nums[i]);
        }
    }
    static int convert(char c){
        int num = -1;
        switch (c){
            case 'T':
                num = 10;
                break;
            case 'J':
                num = 11;
                break;
            case 'Q':
                num = 12;
                break;
            case 'K':
                num = 13;
                break;
            case 'A':
                num = 1;
                break;
            default:
                num = c-'0';
        }
        return num;
    }
}
