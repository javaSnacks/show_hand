package com.loho.show_hand;

import java.util.Arrays;

/**
 * 比较两副牌谁大谁小
 */
public class TestCompareTwoNums
{
	public static void main(String[] args)
	{
		FiveCards fiveCards = new FiveCards();
		Cards cards = new Cards();
		Operations.shuffleCards(cards);
		Card[] test1 = Arrays.copyOfRange(cards.poker, 0, 5);
		fiveCards.getCardsInfo(test1);
		Operations.display(test1);
		System.out.println("\n鬼的数量为:" + fiveCards.ghostNum + " 是否同花:" + fiveCards.isSameColor + " 是否顺子:" + fiveCards.isConsecutive + " 是否有重复数字:" + fiveCards.hasSameNum);
		Card[] test2 = Arrays.copyOfRange(cards.poker, 5, 10);
		fiveCards.getCardsInfo(test2);
		Operations.display(test2);
		System.out.println("\n鬼的数量为:" + fiveCards.ghostNum + " 是否同花:" + fiveCards.isSameColor + " 是否顺子:" + fiveCards.isConsecutive + " 是否有重复数字:" + fiveCards.hasSameNum);
		CompareTwoNums compare = new CompareTwoNums();
		int compareNum = compare.compareTwoNums(test1, test2);
		if (compareNum == 1)
		{
			System.out.println("第一副牌胜利！");
		}
		else if (compareNum == -1)
		{
			System.out.println("第二副牌胜利！");
		}
		else
		{
			System.out.println("平局！");
		}

	}
}
