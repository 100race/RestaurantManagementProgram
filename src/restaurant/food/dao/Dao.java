package restaurant.food.dao;

import java.util.ArrayList;
import restaurant.food.vo.Food;
import restaurant.food.vo.Ingredient;

public interface Dao {
	void insert(Food food);
	void deleteByIdx(int idx);
	void deleteByName(String foodName);
	Food searchByIdx(int idx);
	Food searchByName(String name);
	void updateIng(int idx, ArrayList<Ingredient> ingredients);
	void updatePrice(int idx, int price);
	ArrayList<Food> getAllFood();
}
