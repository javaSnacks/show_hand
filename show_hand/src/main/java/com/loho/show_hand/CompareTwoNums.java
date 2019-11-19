package com.loho.show_hand;

import com.loho.show_hand.enumeration.PokerType;
import java.util.Arrays;

/**
 * 比较两副牌的大小
 */
class CompareTwoNums
{

	/**
	 * 得出当前牌的levels
	 */
	int[] getLevels(Card[] poker)
	{
		//同类型的牌要比较levels
		int[] levels = new int[6];
		FiveCards fiveCards = new FiveCards();
		fiveCards.getCardsInfo(poker);
		int ghostNum = fiveCards.ghostNum;
		GetTypeBaseGhostNums getTypeBaseGhostNums = new GetTypeBaseGhostNums();
		int type = getTypeBaseGhostNums.getPokerType(poker).getLevel();
		if (type == 9)
		{
			//有1的同花顺
			if (poker[ghostNum].getNum() == 1)
			{
				//非鬼牌最小<=5，即12345
				if (poker[ghostNum + 1].getNum() <= 5)
				{
					levels[0] = 15;
				}
				//TJQKA
				else
				{
					levels[0] = 14;
				}
			}
			//没有1时候比较最大牌的数字和花色
			else
			{
				levels[0] = poker[4].getNum();
				levels[1] = poker[4].getColor().getLevel();
			}
		}
		else if (type == 8)
		{
			//有1的四条，最大
			if (poker[ghostNum].getNum() == 1)
			{
				levels[0] = 14;
			}
			else
			{
				//有三个鬼的四条，重复数字必为最后一个数（最大数）
				if (ghostNum == 3)
				{
					levels[0] = poker[4].getNum();
				}
				//没有三个鬼的四条，统计可得倒数第二个数为重复数字
				else
				{
					levels[0] = poker[3].getNum();
				}
			}
		}
		else if (type == 7)
		{
			//一个鬼的葫芦，非鬼牌必为两个对
			if (ghostNum == 1)
			{
				levels[0] = poker[4].getNum();
				levels[1] = poker[1].getNum();
				levels[2] = poker[4].getColor().getLevel();
			}
			else
			{
				//无鬼的葫芦，中间的数字必为三个重复的数字
				levels[0] = poker[2].getNum();
				//如果前三个数相同
				if (poker[2].getNum() == poker[1].getNum())
				{
					levels[1] = poker[3].getNum();
					levels[2] = poker[2].getColor().getLevel();
				}
				else
				{
					//如果后三个数相同
					levels[1] = poker[1].getNum();
					levels[2] = poker[4].getColor().getLevel();
				}
			}
		}
		else if (type == 6)
		{
			//同花，将牌数字倒序排列，并在levels最后填上同花的花色的数字
			for (int i = 0; i < 5; i++)
			{
				levels[i] = poker[4 - i].getNum();
			}
			levels[5] = poker[4].getColor().getLevel();
		}
		else if (type == 5)
		{
			//有1的顺子
			if (poker[ghostNum].getNum() == 1)
			{
				if (poker[ghostNum + 1].getNum() <= 5)
				{
					levels[0] = 15;
				}
				else
				{
					levels[0] = 14;
				}
			}
			//没有1的顺子
			else
			{
				levels[0] = poker[4].getNum();
			}
			//大小一样的情况下比较最大牌的花色
			levels[1] = poker[4].getColor().getLevel();
		}
		else if (type == 4)
		{
			//有1的三条
			if (poker[ghostNum].getNum() == 1)
			{
				levels[0] = 14;
				//找到最后一个1的花色
				for (int i = 4; i > 0; i--)
				{
					if (poker[i].getNum() == 1)
					{
						levels[1] = poker[i].getColor().getLevel();
						break;
					}
				}
			}
			else
			{
				//有两个鬼的三条，其重复数字必为最大的数字
				if (ghostNum == 2)
				{
					levels[0] = poker[4].getNum();
					levels[1] = poker[4].getColor().getLevel();
				}
				else
				{
					//找到重复的数字和其花色
					for (int i = 4; i > 1; i--)
					{
						if (poker[i].getNum() == poker[i - 1].getNum())
						{
							levels[0] = poker[i].getNum();
							levels[1] = poker[i].getColor().getLevel();
							break;
						}
					}
				}
			}
		}
		else if (type == 3)
		{
			//牌型为二对时候不会有鬼
			if (poker[2].getNum() == poker[3].getNum())
			{
				levels[0] = poker[3].getNum();
				levels[1] = poker[1].getNum();
				levels[2] = poker[3].getColor().getLevel();
			}
			else if (poker[0].getNum() == poker[1].getNum())
			{
				levels[0] = poker[4].getNum();
				levels[1] = poker[1].getNum();
				levels[2] = poker[4].getColor().getLevel();
			}
			else
			{
				levels[0] = poker[4].getNum();
				levels[1] = poker[2].getNum();
				levels[2] = poker[4].getColor().getLevel();
			}
		}
		else if (type == 2)
		{
			//一个鬼的单对
			if (ghostNum == 1)
			{
				levels[0] = poker[4].getNum();
				levels[1] = poker[4].getColor().getLevel();
			}
			else
			{
				//没有鬼时，找到单对中较大的那张牌的数字和花色
				for (int i = 0; i < 4; i++)
				{
					if (poker[i].getNum() == poker[i + 1].getNum())
					{
						levels[0] = poker[i + 1].getNum();
						levels[1] = poker[i + 1].getColor().getLevel();
						break;
					}
				}
			}
		}
		else
		{
			//散牌时候
			levels[0] = poker[4].getNum();
			levels[1] = poker[4].getColor().getLevel();
		}
		return levels;
	}

	/**
	 * 比较两副牌的大小，每副牌的数量为5张
	 *
	 * @return 第一副牌>第二副牌:1,第一副牌<第二副牌:-1,第一副牌==第二副牌:0
	 */
	int compareTwoNums(Card[] poker1, Card[] poker2)
	{
		GetTypeBaseGhostNums getTypeBaseGhostNums = new GetTypeBaseGhostNums();
		PokerType type1 = getTypeBaseGhostNums.getPokerType(poker1);
		int poker1Type = type1.getLevel();
		int[] levels1 = Arrays.copyOf(getLevels(poker1), 5);
		PokerType type2 = getTypeBaseGhostNums.getPokerType(poker2);
		int poker2Type = type2.getLevel();
		int[] levels2 = Arrays.copyOf(getLevels(poker2), 5);
		System.out.println("第一副牌牌型为:" + type1);
		System.out.println("第二副牌牌型为:" + type2);
		if (poker1Type > poker2Type)
		{
			return 1;
		}
		else if (poker1Type < poker2Type)
		{
			return -1;
		}
		else
		{
			for (int i = 0; i < 6; i++)
			{
				if (levels1[i] > levels2[i])
				{
					return 1;
				}
				else if (levels1[i] < levels2[i])
				{
					return -1;
				}
			}
		}
		return 0;
	}
}
