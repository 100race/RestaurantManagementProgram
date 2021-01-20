package restaurant.refrigerator.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import restaurant.food.vo.Ingredient;

public class RestaurantRefrigeratorDaoImpl implements Refrigerator {

	
	private ArrayList<Ingredient> ingredients = new ArrayList<>(); //저장소

	@Override
	public void addIng(Ingredient ing) {
		// TODO Auto-generated method stub
		ingredients.add(ing);
		
	}

	@Override
	public ArrayList<Ingredient> searchByName(String name) {
		/*

		 * select * from refrigerator
		 * where name = name
		 */
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		
		for (Ingredient ingredient : this.ingredients) {//안에 찾는 걸 스캔
			if (name.equals(ingredient.getName())) {
				ingredients.add(ingredient);
			}
		}
		return ingredients;
	}

	@Override
	public void updateDue(String name, LocalDate Date) {
		// 유통기한
		for (Ingredient ingredient : this.ingredients) {//안에 찾는 걸 스캔
			if (name.equals(ingredient.getName())) {
				ingredient.setDue(Date);
			}
		}
		
	}

	@Override
	public void updateAmount(String name, int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Ingredient> selectAllIng() {
		// TODO Auto-generated method stub
		return null;
	}
	


	
	
	
	
	
}
