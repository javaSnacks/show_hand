package com.loho.show_hand;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        //test
//        String s1 = "TC  KD  KS  3D  7C";
//        InversePoker.inverse(s1);
//        GetType getType = new GetType();
//        int ans = -2;
//        ans = getType.compareSize(new int[]{36,32,3,39,23},new int[]{10,26,52,16,7});
//        System.out.println(ans);

        Person player = new Person("player");
        Person computer = new Person("computer");
        Cards cards = new Cards();
        Shuffle.shuffleCards(cards);
        GetType getType = new GetType();
        int oneWager = 10;

        while (true) {
            Shuffle.shuffleCards(cards);
            Scanner scanner = new Scanner(System.in);
            System.out.println("每次发牌前，玩家可以有3个选择： 输入 \"Y\" 继续发牌， 输入 \"R 钱数\"加注，之后赢家获得的货币会增多加注的钱数，输入\"N\"放弃这轮，对方直接获胜。");
            System.out.println("请输入你的选择：");
            String  choice = scanner.nextLine();
            if(choice.toLowerCase().equals("r")){
                System.out.println("请输入你要加注的数目:");
                int addWager=0;
                try{
                    addWager = scanner.nextInt();
                }catch (Exception e){
                    System.out.println("您输入的不是整数，请您重新开始游戏！");
                    scanner.nextLine();
                    continue;
                }
                if (addWager<player.getMoney()-10){
                    oneWager+=addWager;
                    System.out.println("加注成功，对局开始:");
                }else{
                    System.out.println("您的余额不足以加注，请重新开始！");
                    continue;
                }
            }
            if (choice.toLowerCase().equals("y")||choice.toLowerCase().equals("r")){
                player.setPoker(Arrays.copyOfRange(cards.poker,0,5));
                computer.setPoker(Arrays.copyOfRange(cards.poker,5,10));
                System.out.println("您的牌为:");
                Deal.display(player.getPoker());
                System.out.println();
                System.out.println("电脑的牌为:");
                Deal.display(computer.getPoker());
                System.out.println();
                int ans = getType.compareSize(player.getPoker(),computer.getPoker());
                System.out.println("经过计算可得:");
                if (ans==1){
                    System.out.println("您获胜了！\n");
                    player.setMoney(player.getMoney()+oneWager);
                }else if(ans == -1){
                    System.out.println("电脑获胜了！\n");
                    player.setMoney(player.getMoney()-oneWager);
                }else{
                    System.out.println("平局！\n");
                }
            }else if(choice.toLowerCase().equals("n")){
                System.out.println("您放弃本轮游戏，对方获胜！");
                //显示玩家货币剩余和损益
                System.out.println("您的货币剩余为:"+player.getMoney());
                System.out.println("本轮对局损益为:"+(player.getMoney()-1000));
                scanner.close();
                break;
            }else{
                System.out.println("您输入的内容有误，请重新输入！");
            }
        }
    }
}
