package com.loho.show_hand;

public class Player
{
	//玩家类型
	String type;
	//玩家货币
	int money;
	//玩家牌
	Card[] poker = new Card[5];

	Player(String type)
	{
		this.type = type;
		money = 1000;
	}

	public void setPoker(Card[] poker)
	{
		this.poker = poker;
	}

	public Card[] getPoker()
	{
		return poker;
	}

	public void setMoney(int money)
	{
		this.money = money;
	}

	public int getMoney()
	{
		return money;
	}

}
