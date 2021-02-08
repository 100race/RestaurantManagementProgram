package restaurant.food.dao;

import java.util.ArrayList;
import java.util.Map;
import restaurant.food.vo.FoodIngredient;

public interface FoodIngDao {
	//insert
	void insertIng(int idx, String name, int amount);
	//select
	ArrayList<FoodIngredient> getAllIng();
	FoodIngredient getIngByName(String name);
	FoodIngredient getIngByIdx(int idx);
	ArrayList<FoodIngredient> getFoodIngByIdx(int idx); //추가 PSJ
	//update
	void changeIngAmount(int idx, String name, int value);
	void deleteIng(int idx, String ingName);
}
