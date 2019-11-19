package com.loho.show_hand;

import java.util.Arrays;

/**
 * 测试创建一副牌，洗牌，打印全部的或者特定的牌
 */
public class TestCards
{
	public static void main(String[] args)
	{
		//新创建一副新牌并打印
		Cards cards = new Cards();
		cards.display();
		//洗牌并打印
		Operations.shuffleCards(cards);
		System.out.println();
		cards.display();
		System.out.println();
		//对洗过的牌进行排序并打印
		Operations.sortPoker(cards.poker);
		Operations.display(cards.poker);
		//打印特定的牌
		System.out.println();
		Operations.display(Arrays.copyOfRange(cards.poker, 10, 15));
	}
}
