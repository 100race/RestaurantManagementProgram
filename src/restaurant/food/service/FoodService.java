package restaurant.food.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import restaurant.food.vo.Food;

public interface FoodService {
	boolean checking(int num);
	//print
	void printAll(ArrayList<Food> food);
	void printFoodByIdx(Scanner sc);
	//insert
	void addFood(Scanner sc);
	//select
	Food getFoodByIdx(int idx);
	ArrayList<Food> getAllFood();
	//update
	void changePriceByIdx(Scanner sc);
	void changeFoodNameByIdx(Scanner sc);
	//delete
	void delFoodByIdx(Scanner sc);
}
