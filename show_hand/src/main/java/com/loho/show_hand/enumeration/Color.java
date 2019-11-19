package com.loho.show_hand.enumeration;

/**
 * 牌的颜色
 */
public enum Color
{
	//黑桃＞红桃＞方块＞草花>鬼
	黑桃(3), 红桃(2), 方块(1), 草花(0), 鬼(-1);

	int level;

	Color(int level)
	{
		this.level = level;
	}

	public int getLevel()
	{
		return level;
	}
}