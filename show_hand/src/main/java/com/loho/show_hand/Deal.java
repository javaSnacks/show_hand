package com.loho.show_hand;

public class Deal {
    //模拟一次发全部的牌
    public static void deal(Cards cards){
        int[] poker = Shuffle.shuffleCards(cards);
        display(poker);
    }

    //将牌打印出来
    public static void display(int[] poker){
        for (int i : poker) {
            if(i>52){
                System.out.print('G'+"  ");
            }else{
                System.out.print(""+getNum(i)+getColor(i)+"  ");
            }
        }
    }
    public static char getNum(int i) {
        char c = 'n';
        int j = i%13;
        switch (j){
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                c = (char)('0'+j);
                break;
            case 1:
                c = 'A';
                break;
            case 10:
                c = 'T';
                break;
            case 11:
                c = 'J';
                break;
            case 12:
                c = 'Q';
                break;
            case 0:
                c = 'K';
                break;
        }
        return c;
    }

    public static char getColor(int i){
        int j = ((i-1)/13);
        char c = 'n';
        if(j==0){
            c = 'C';
        }else if (j==1){
            c = 'D';
        }else if(j==2){
            c = 'H';
        }else if(j==3){
            c = 'S';
        }else if(j==4){
            c = 'G';
        }
        return c;
    }
}
