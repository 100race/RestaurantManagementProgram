package restaurant.food.dao;

import java.util.ArrayList;
import java.util.Map;

import restaurant.food.vo.Food;
import restaurant.food.vo.Ingredient;

public interface FoodDao {
	//insert
	void insert(String name, int price);
	//select
	Food searchByIdx(int idx);
	Food searchByName(String name);
	ArrayList<Food> getAllFood();
	//update
	void changePrice(Food food);
	void changeName(Food food);
	//delete
	void deleteByIdx(int idx);
}
