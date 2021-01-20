package restaurant.order.vo;

import restaurant.food.vo.Food;

public class Order {
	public static int NUM;
	private Food food;
	private int amount;
	
	public Order(Food food, int amount) {
		this.food = food;
		this.amount = amount;
	}
	
	public static int getNUM() {
		return NUM;
	}
	public static void setNUM(int nUM) {
		NUM = nUM;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Order [amount=" + amount + "]";
	}
	
	
}
