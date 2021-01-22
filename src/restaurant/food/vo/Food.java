package restaurant.food.vo;
import java.util.ArrayList;
import java.util.Map;

public class Food {
	private int idx = 0;
	private String foodName;
	private int price;
	private Map<String, Integer> ingredients;
	
	public Food() {}
	public Food(String foodName, int price, Map<String, Integer> ingredients) {
		idx++;
		this.foodName = foodName;
		this.price = price;
		this.ingredients = ingredients;
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Map<String, Integer> getIngredient() {
		return ingredients;
	}
	public void setIngredient(Map<String, Integer> ingredients) {
		this.ingredients = ingredients;
	}
	
	@Override
	public String toString() {
		return "Food [idx=" + idx + ", foodName=" + foodName + ", price=" + price + ", ingredient=" + ingredients + "]";
	}
	
	
}