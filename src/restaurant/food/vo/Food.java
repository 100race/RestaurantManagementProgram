package restaurant.food.vo;

import java.util.ArrayList;

public class Food {
	private int idx;
	private String foodName;
	private int price;
	private ArrayList <Ingredient> ingredients;
	
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
	public ArrayList<Ingredient> getIngredient() {
		return ingredients;
	}
	public void setIngredient(ArrayList<Ingredient> ingredient) {
		this.ingredients = ingredient;
	}
	
	@Override
	public String toString() {
		return "Food [idx=" + idx + ", foodName=" + foodName + ", price=" + price + ", ingredient=" + ingredients + "]";
	}
	
	
}