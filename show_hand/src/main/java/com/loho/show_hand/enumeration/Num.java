package com.loho.show_hand.enumeration;

/**
 * 牌的数字
 */
public enum Num
{
	num0("G", -1), num1("A", 1), num2("2", 2), num3("3", 3), num4("4", 4), num5("5", 5), num6("6", 6), num7("7", 7), num8("8", 8), num9("9", 9), num10("T", 10), num11("J", 11), num12("Q", 12), num13(
	"K",
	13);

	String name;
	int number;

	Num(String name, int num)
	{
		this.name = name;
		this.number = num;
	}

	public int getNumber()
	{
		return number;
	}

	@Override
	public String toString()
	{
		return this.name + "";
	}
}
