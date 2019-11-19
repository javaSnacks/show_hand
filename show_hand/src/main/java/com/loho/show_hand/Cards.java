package com.loho.show_hand;

/**
 * 一副扑克牌，包括52张正常牌和五张鬼牌
 */
public class Cards
{
	Card[] poker = new Card[57];

	/**
	 * 初始化扑克牌，1-13，14-26，27-39，40-52分别代表Club(草花) < Diamond(方块) < Heart(红桃) < Spade (黑桃)，53-57代表ghost（鬼）
	 */
	public Cards()
	{
		for (int i = 0; i < poker.length; i++)
		{
			poker[i] = new Card(i + 1);
		}
	}

	/**
	 * 显示全部的扑克牌
	 */
	public void display()
	{
		for (int i = 0; i < this.poker.length; i++)
		{
			System.out.print(poker[i].toString() + " ");
		}
	}

}
