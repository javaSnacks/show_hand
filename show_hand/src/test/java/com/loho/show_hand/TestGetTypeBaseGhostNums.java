package com.loho.show_hand;

import com.loho.show_hand.enumeration.PokerType;
import java.util.Arrays;

/**
 * 测试牌型判断，特定牌的牌型判断
 */
public class TestGetTypeBaseGhostNums
{
	public static void main(String[] args)
	{
		//随机产生一副牌，判断牌型
		FiveCards fiveCards = new FiveCards();
		Cards cards = new Cards();
		Operations.shuffleCards(cards);
		Card[] test = Arrays.copyOfRange(cards.poker, 0, 5);
		fiveCards.getCardsInfo(test);
		Operations.display(test);
		System.out.println("\n鬼的数量为:" + fiveCards.ghostNum + " 是否同花:" + fiveCards.isSameColor + " 是否顺子:" + fiveCards.isConsecutive + " 是否有重复数字:" + fiveCards.hasSameNum);
		GetTypeBaseGhostNums getTypeBaseGhostNums = new GetTypeBaseGhostNums();
		PokerType pokerType = getTypeBaseGhostNums.getPokerType(test);
		System.out.println(pokerType);
		//输入特定牌得到该牌牌型
		Card[] test1 = new Card[5];
		test1[0] = new Card(53);
		test1[1] = new Card(54);
		test1[2] = new Card(1);
		test1[3] = new Card(11);
		test1[4] = new Card(10);
		fiveCards.getCardsInfo(test1);
		Operations.display(test1);
		System.out.println("\n鬼的数量为:" + fiveCards.ghostNum + " 是否同花:" + fiveCards.isSameColor + " 是否顺子:" + fiveCards.isConsecutive + " 是否有重复数字:" + fiveCards.hasSameNum);
		GetTypeBaseGhostNums getTypeBaseGhostNums1 = new GetTypeBaseGhostNums();
		PokerType pokerType1 = getTypeBaseGhostNums1.getPokerType(test1);
		System.out.println(pokerType1);
	}
}
