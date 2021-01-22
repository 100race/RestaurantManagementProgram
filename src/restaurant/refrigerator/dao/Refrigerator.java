package restaurant.refrigerator.dao;
import java.time.LocalDate;
import java.util.ArrayList;

import restaurant.food.vo.Ingredient;

public interface Refrigerator {
	
	//ArrayList<Ingredient> ingredients; 상수.
	
	void addIng(Ingredient ing); //식자재 항목 입력
	ArrayList<Ingredient> searchByName(String name);//name으로 검색
	void updateDue(String name,LocalDate Date);
	void updateAmount(String name, int amount);
	void deleteByName(String name);
	ArrayList<Ingredient> selectAllIng();

}
