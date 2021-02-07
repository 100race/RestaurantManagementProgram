package restaurant.food.vo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import restaurant.food.dao.FoodDaoImpl;

public class Food{
	private int idx;
	private String foodName;
	private int price;
	
	public Food() {}
	
	public Food(int idx, int price) {
		this.idx = idx;
		this.price = price;
	}
	
	public Food(int idx, String foodName) {
		this.idx = idx;
		this.foodName = foodName;
	}
	
	public Food(String foodName, int price) {
		//this.idx = idx;
		//this.idx = FoodDaoImpl.().size()+1; //변경함
		this.foodName = foodName;
		this.price = price;
	}
	
	public Food(int idx, String foodName, int price) {
		this.idx = idx;
		//this.idx = FoodDaoImpl.().size()+1; //변경함
		this.foodName = foodName;
		this.price = price;
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
//	public Map<String, Integer> getIngredient() {
//		return ingredients;
//	}
//	public void setIngredient(Map<String, Integer> ingredients) {
//		this.ingredients = ingredients;
//	}

	@Override
	public String toString() {
		return "요리 [번호=" + idx + ", 메뉴=" + foodName + ", 가격=" + price + "]";
	}
}