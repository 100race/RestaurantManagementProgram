package restaurant.refrigerator;

import java.time.LocalDate;
import java.util.Scanner;

import restaurant.food.vo.Ingredient;
import restaurant.refrigerator.dao.RestaurantRefrigeratorDaoImpl;


public class RefrigerratorServiceImpl implements RefrigerratorService {

		private RestaurantRefrigeratorDaoImpl refger;
		public RefrigerratorServiceImpl() {
			refger = new RestaurantRefrigeratorDaoImpl();
		}
		
	
	
	@Override
	public void buying(Ingredient ing) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDue(LocalDate Date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getByName(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editDue(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteIng(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inIng(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void outIng(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAllIng() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PrintAllIng() {
		// TODO Auto-generated method stub
		
	}

}
