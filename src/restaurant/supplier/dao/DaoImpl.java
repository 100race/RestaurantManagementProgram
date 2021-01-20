package restaurant.supplier.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import restaurant.food.vo.Ingredient;
import restaurant.refrigerator.dao.Refrigerator;

public class DaoImpl implements Refrigerator{
	
	private ArrayList<Ingredient> ingredients;
	//private int cnt;
	
	public DaoImpl() {
		ingredients = new ArrayList<Ingredient>(); 
	}
	
	@Override
	public void addIng(Ingredient ing) {
		ingredients.add(ing);
		//cnt ++;
	}

	@Override
	public ArrayList<Ingredient> searchByName(String name) {
		ArrayList<Ingredient> sl = new ArrayList<>();
		for(int i = 0 ; i< ingredients.size(); i++) {
			if(ingredients.get(i).getName().equals(name))
				sl.add(ingredients.get(i));
		}
		return sl;
	}

	@Override
	public void updateDue(String name, LocalDate date) {
		//이름이 같은건 전부 바꾸기
		for(int i = 0; i < ingredients.size(); i++) {
			if(ingredients.get(i).getName().equals(name)){
				ingredients.get(i).setDue(date);
			}
		}
	}

	
	@Override
	public void updateAmount(String name, int newAmount) {
		//이름이 같은건 전부 바꾸기
		int oriAmount = 0;
		for(int i = 0; i < ingredients.size(); i++) {
			if(ingredients.get(i).getName().equals(name)){
				oriAmount = ingredients.get(i).getAmount();
				ingredients.get(i).setAmount(oriAmount+newAmount);
			}
		}
	}

	@Override
	public void deleteByName(String name) {
		for(int i = 0 ; i< ingredients.size(); i++) {
			if(ingredients.get(i).getName().equals(name))
				ingredients.remove(i);
		}
	}
	


	@Override
	public ArrayList<Ingredient> selectAllIng() {
		return ingredients;
	}

	
}
