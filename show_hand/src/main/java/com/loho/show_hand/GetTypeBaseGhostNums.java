package com.loho.show_hand;

import com.loho.show_hand.enumeration.PokerType;

/**
 * 根据鬼的个数得到该副牌的牌型
 */
class GetTypeBaseGhostNums
{
	/**
	 * 这五张牌属于什么类型
	 *
	 * @param poker 五张牌
	 * @return 牌的类型
	 */
	PokerType getPokerType(Card[] poker)
	{
		FiveCards fiveCards = new FiveCards();
		fiveCards.getCardsInfo(poker);
		int ghostNum = fiveCards.ghostNum;
		boolean isConsecutive = fiveCards.isConsecutive;
		boolean isSameColor = fiveCards.isSameColor;
		boolean hasSameNum = fiveCards.hasSameNum;
		if (ghostNum == 5)
		{
			return PokerType.五鬼;
		}
		if (ghostNum == 4)
		{
			return PokerType.五条;
		}
		else if (ghostNum == 3)
		{
			//两张非鬼牌相等
			if (poker[3].getNum() == poker[4].getNum())
			{
				return PokerType.五条;
			}
			//两张非鬼牌不相等
			else
			{
				if (isConsecutive && isSameColor)
				{
					return PokerType.同花顺;
				}
				else
				{
					return PokerType.四条;
				}
			}
		}
		else if (ghostNum == 2)
		{
			//非鬼牌有无重复数字
			if (hasSameNum)
			{
				//三张非鬼牌相等
				if (poker[2].getNum() == poker[4].getNum())
				{
					return PokerType.五条;
				}
				//有两张非鬼牌相等
				else
				{
					return PokerType.四条;
				}
			}
			else
			{
				if (isConsecutive && isSameColor)
				{
					return PokerType.同花顺;
				}
				else if (isSameColor)
				{
					return PokerType.同花;
				}
				else if (isConsecutive)
				{
					return PokerType.顺子;
				}
				//只有两张鬼牌，不满足上边三种情况的话就是三条
				else
				{
					return PokerType.三条;
				}
			}
		}
		else if (ghostNum == 1)
		{
			//判断非鬼牌有多少重复数字
			if (hasSameNum)
			{
				//四张非鬼牌相等
				if (poker[1].getNum() == poker[4].getNum())
				{
					return PokerType.五条;
				}
				//三张非鬼牌相等
				else if (poker[1].getNum() == poker[3].getNum() || poker[2].getNum() == poker[4].getNum())
				{
					return PokerType.四条;
				}
				//两对非鬼牌相等
				else if (poker[1].getNum() == poker[2].getNum() && poker[3].getNum() == poker[4].getNum())
				{
					return PokerType.葫芦;
				}
				//一对非鬼牌相等
				else
				{
					return PokerType.三条;
				}
			}
			else
			{
				if (isConsecutive && isSameColor)
				{
					return PokerType.同花顺;
				}
				else if (isSameColor)
				{
					return PokerType.同花;
				}
				else if (isConsecutive)
				{
					return PokerType.顺子;
				}
				//只有一张鬼牌，不满足上边三种情况的话就是单对
				else
				{
					return PokerType.单对;
				}
			}
		}
		else if (ghostNum == 0)
		{
			//无鬼牌
			if (hasSameNum)
			{
				//四张牌相等
				if (poker[0].getNum() == poker[3].getNum() || poker[1].getNum() == poker[4].getNum())
				{
					return PokerType.四条;
				}
				//开头两张和结尾两张相等
				else if (poker[0].getNum() == poker[1].getNum() && poker[3].getNum() == poker[4].getNum())
				{
					return PokerType.葫芦;
				}
				//有三张相等
				else if (poker[0].getNum() == poker[2].getNum() || poker[1].getNum() == poker[3].getNum() || poker[2].getNum() == poker[4].getNum())
				{
					return PokerType.三条;
				}
				//有两对相等
				else if ((poker[0].getNum() == poker[1].getNum() && poker[2].getNum() == poker[3].getNum()) || (poker[0].getNum() == poker[1].getNum() && poker[3].getNum() == poker[4].getNum()) || (
					poker[1].getNum() == poker[2].getNum() && poker[3].getNum() == poker[4].getNum()))
				{
					return PokerType.二对;
				}
				//不满足上述所有情况且有相同数字，只能是单对
				else
				{
					return PokerType.单对;
				}
			}
			else
			{
				if (isConsecutive && isSameColor)
				{
					return PokerType.同花顺;
				}
				else if (isSameColor)
				{
					return PokerType.同花;
				}
				else if (isConsecutive)
				{
					return PokerType.顺子;
				}
				//不满足同花和连续，只能是散牌
				else
				{
					return PokerType.散牌;
				}
			}
		}
		return PokerType.散牌;
	}

}
