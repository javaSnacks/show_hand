package com.loho.show_hand;

import java.util.Arrays;

/**
 * 测试一副牌的内容是否正确
 */
public class TestFiveCards
{
	public static void main(String[] args)
	{
		FiveCards fiveCards = new FiveCards();
		Cards cards = new Cards();
		Operations.shuffleCards(cards);
		Card[] test = Arrays.copyOfRange(cards.poker, 0, 5);
		fiveCards.getCardsInfo(test);
		Operations.display(test);
		System.out.println("\n鬼的数量为:" + fiveCards.ghostNum + " 是否同花:" + fiveCards.isSameColor + " 是否顺子:" + fiveCards.isConsecutive + " 是否有重复数字:" + fiveCards.hasSameNum);
	}
}
