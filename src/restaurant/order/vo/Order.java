package restaurant.order.vo;

public class Order {

	
	private int num;
	private int food_idx;
	private String foodname;
	private int amount;
	
	public Order() {}
	
	

	public Order(int num, int food_idx, String foodname, int amount) {
		this.num = num;
		this.food_idx = food_idx;
		this.foodname = foodname;
		this.amount = amount;
	}



	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}



	public int getFood_idx() {
		return food_idx;
	}



	public void setFood_idx(int food_idx) {
		this.food_idx = food_idx;
	}



	public String getFoodname() {
		return foodname;
	}



	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	@Override
	public String toString() {

		return " [ 주문번호  " + num + " | 요리 : " + foodname + " | 수량 :" + amount + "]";
	}
	
	
	}
	
