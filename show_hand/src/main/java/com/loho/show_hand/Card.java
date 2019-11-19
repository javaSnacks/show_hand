package com.loho.show_hand;

import com.loho.show_hand.enumeration.Color;
import com.loho.show_hand.enumeration.Num;

/**
 * 一张牌
 */
public class Card implements Comparable
{
	//原始数字
	int originalNum;
	//1-13的显式数字
	Num num;
	//牌的花色
	Color color;

	public Card(int n)
	{
		this.originalNum = n;
		int number = (n + 12) % 13 + 1;
		switch (number)
		{
			case 1:
				num = Num.num1;
				break;
			case 2:
				num = Num.num2;
				break;
			case 3:
				num = Num.num3;
				break;
			case 4:
				num = Num.num4;
				break;
			case 5:
				num = Num.num5;
				break;
			case 6:
				num = Num.num6;
				break;
			case 7:
				num = Num.num7;
				break;
			case 8:
				num = Num.num8;
				break;
			case 9:
				num = Num.num9;
				break;
			case 10:
				num = Num.num10;
				break;
			case 11:
				num = Num.num11;
				break;
			case 12:
				num = Num.num12;
				break;
			case 13:
				num = Num.num13;
				break;
		}
		if (n > 52)
		{
			num = Num.num0;
		}
		int level = ((n - 1) / 13);
		if (level == 3)
		{
			color = Color.黑桃;
		}
		else if (level == 2)
		{
			color = Color.红桃;
		}
		else if (level == 1)
		{
			color = Color.方块;
		}
		else if (level == 0)
		{
			color = Color.草花;
		}
		else
		{
			color = Color.鬼;
		}

	}

	public int getNum()
	{
		return num.getNumber();
	}

	public Color getColor()
	{
		return color;
	}

	@Override
	public String toString()
	{
		return num + "" + color;
	}


	@Override
	public int compareTo(Object o)
	{
		if (o instanceof Card)
		{
			Card tmpCard = (Card)o;
			if (this.num.getNumber() > tmpCard.num.getNumber())
			{
				return 1;
			}
			else if (this.num.getNumber() < tmpCard.num.getNumber())
			{
				return -1;
			}
			else
			{
				if (this.color.getLevel() > tmpCard.color.getLevel())
				{
					return 1;
				}
				else if (this.color.getLevel() < tmpCard.color.getLevel())
				{
					return -1;
				}
			}
		}
		return 0;
	}
}
