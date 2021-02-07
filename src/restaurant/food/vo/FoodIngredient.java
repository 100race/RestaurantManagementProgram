package restaurant.food.vo;

//이 클래스는 요리가 가지고 있는 재료 VO입니다.
public class FoodIngredient {
	private int idx;
	private String name;
	private int amount;
	
	public FoodIngredient() {}

	public FoodIngredient(int idx, String name, int amount) {
		this.idx = idx;
		this.name = name;
		this.amount = amount;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return " [요리 번호 : " + idx + " | 재료 명 :" + name + " | 필요 수량 :" + amount + "]";
	}
	
	
}
