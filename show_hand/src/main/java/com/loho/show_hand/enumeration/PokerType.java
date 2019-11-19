package com.loho.show_hand.enumeration;

/**
 * 五张牌的类型
 */
public enum PokerType
{
	散牌(1), 单对(2), 二对(3), 三条(4), 顺子(5), 同花(6), 葫芦(7), 四条(8), 同花顺(9), 五条(10), 五鬼(11);
	int level;

	PokerType(int level)
	{
		this.level = level;
	}

	public int getLevel()
	{
		return level;
	}
}
