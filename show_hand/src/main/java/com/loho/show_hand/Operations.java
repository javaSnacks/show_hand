package com.loho.show_hand;

import java.util.Arrays;

public class Operations
{
	/**
	 * 模拟一次洗牌
	 *
	 * @param cards
	 * @return 按照数字和花色洗好顺序的牌
	 */
	public static Card[] shuffleCards(Cards cards)
	{
		Card tmp;
		int index;
		Card[] poker = cards.poker;
		int length = poker.length;
		for (int i = 0; i < length; i++)
		{
			index = i + (int)(Math.random() * (length - i));
			tmp = poker[i];
			poker[i] = poker[index];
			poker[index] = tmp;
		}
		return poker;
	}

	/**
	 * 打印指定的扑克牌
	 *
	 * @param poker
	 */
	public static void display(Card[] poker)
	{
		for (int i = 0; i < poker.length; i++)
		{
			System.out.print(poker[i].toString() + " ");
		}
	}

	/**
	 * 将一副牌根据数字和花色排序
	 *
	 * @param poker
	 * @return 排序的扑克牌
	 */
	public static Card[] sortPoker(Card[] poker)
	{
		Arrays.sort(poker);
		return poker;
	}
}
