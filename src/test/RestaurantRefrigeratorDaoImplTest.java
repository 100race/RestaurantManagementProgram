package test;

import java.time.LocalDate;

import restaurant.food.vo.Ingredient;
import restaurant.refrigerator.RefrigerratorServiceImpl;
import restaurant.refrigerator.dao.RestaurantRefrigeratorDaoImpl;

public class RestaurantRefrigeratorDaoImplTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestaurantRefrigeratorDaoImpl r = new RestaurantRefrigeratorDaoImpl();
		RefrigerratorServiceImpl rs = new RefrigerratorServiceImpl();
		
		LocalDate ld = LocalDate.parse("2021-01-20");
		
//		r.addIng(new Ingredient("김", 10, 1000, ld));
//		r.addIng(new Ingredient("김", 20, 1000, ld));
//		r.addIng(new Ingredient("단무지", 20, 1000, ld));
//		
//		ArrayList<Ingredient> result = r.searchByName("단무지");
//		
//		r.updateDue("단무지", LocalDate.parse("2021-01-21"));
//		
//		for(Ingredient in : result) {
//			System.out.println(in.getName());
//			System.out.println(in.getDue());
			
		rs.buying(new Ingredient("단무지", 20, 1000, ld));
//		rs.buying(new Ingredient("김", 10, 1000, ld));
	
		
		
			
		}
			
}

