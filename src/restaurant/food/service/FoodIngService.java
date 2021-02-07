package restaurant.food.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import restaurant.food.vo.FoodIngredient;

public interface FoodIngService {
	//print
	void printAll(ArrayList<FoodIngredient> ing);
	//insert
	void addIngByIdx(Scanner sc);
	//select
	ArrayList<FoodIngredient> getAllIng();
	//update
	void changeAmountByIdx(Scanner sc);
	//delete
	void delIngByIdx(Scanner sc);
}
