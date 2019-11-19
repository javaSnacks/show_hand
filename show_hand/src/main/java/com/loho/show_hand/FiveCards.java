package com.loho.show_hand;

import com.loho.show_hand.enumeration.Color;

/**
 * 一次对局所需要的五张牌的有关信息，包括这五张牌的鬼的数量，是否同花，是否顺子，非鬼牌是否有重复数字，该牌的levels（两副牌同类型则比较levels）
 */
class FiveCards
{
	//五张牌
	private Card[] cards = new Card[5];
	//鬼的数量
	int ghostNum = 0;
	//是否是同花
	boolean isSameColor = true;
	//能否构成顺子
	boolean isConsecutive = false;
	//判断非鬼牌是否有重复数字
	boolean hasSameNum = false;

	/**
	 * 得到这5张扑克的以上成员变量信息
	 *
	 * @param fiveCards 五张牌
	 */
	void getCardsInfo(Card[] fiveCards)
	{
		this.cards = fiveCards;
		//特别重要，按照牌的数字和颜色进行排序，鬼牌放在最前边
		Operations.sortPoker(cards);
		//判断鬼牌数量
		for (int i = 0; i < 5; i++)
		{
			if (cards[4 - i].getColor() == Color.鬼)
			{
				ghostNum = 5 - i;
				break;
			}
		}
		if (ghostNum == 5)
		{
			return;
		}
		//判断是否同花
		Color tmpColor = cards[ghostNum].getColor();
		for (int i = ghostNum; i < 5; i++)
		{
			if (cards[i].getColor() != tmpColor)
			{
				isSameColor = false;
				break;
			}
		}
		//判断非鬼牌是否重复
		for (int i = ghostNum; i < 4; i++)
		{
			if (cards[i].getNum() == cards[i + 1].getNum())
			{
				hasSameNum = true;
				break;
			}
		}
		//判断能否构成顺子
		if (!hasSameNum)
		{
			//非鬼牌的第一个数字是1
			if (cards[ghostNum].getNum() == 1)
			{
				//非鬼牌最大数字<=5或者非鬼牌最小数字（除了1）>=10均可构成顺子
				if (cards[4].getNum() <= 5 || cards[ghostNum + 1].getNum() >= 10)
				{
					isConsecutive = true;
				}
			}
			else
			{
				//非鬼牌无1的情况下非鬼牌最大数和非鬼牌最小数相差4以内
				if (cards[4].getNum() - cards[ghostNum].getNum() <= 4)
				{
					isConsecutive = true;
				}
			}

		}
	}

}
