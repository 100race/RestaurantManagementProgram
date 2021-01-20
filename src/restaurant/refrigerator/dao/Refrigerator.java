package restaurant.refrigerator.dao;
import java.time.LocalDate;
import java.util.ArrayList;

import restaurant.food.vo.Ingredient;

public interface Refrigerator {
	
	//ArrayList<Ingredient> ingredients; 상수.
	
	void addIng(Ingredient ing);
	ArrayList<Ingredient> searchByName(String name);
	void updateDue(String name,LocalDate Date);
	void updateAmount(String name, int amount);
	void deleteByName(String name);
	ArrayList<Ingredient> selectAllIng();
	
}
