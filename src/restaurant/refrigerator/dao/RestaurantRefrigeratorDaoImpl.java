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
//		식자재 입고(name, amount, price, due)

	}

	@Override
	public ArrayList<Ingredient> searchByName(String name) {
		/*

		 * select * from refrigerator
		 * where name = name
		 */
		ArrayList<Ingredient> ingredients = new ArrayList<>();
//      식자재 검색 및 확인
			for (Ingredient ingredient : this.ingredients) {//안에 찾는 걸 스캔
				if (name.equals(ingredient.getName())) {
					ingredients.add(ingredient);
				}
			}
			return ingredients;
		}

	@Override
	public void updateDue(String name, LocalDate Date) {
		// 유통기한 수정
		for (Ingredient ingredient : this.ingredients) {
			if (name.equals(ingredient.getName())) {
				ingredient.setDue(Date);
				
//				1일이 지날수록 유통기한 변경은?
//				default가 2100-01-01
			}
		}
		
	}

	@Override
	public void updateAmount(String name, int amount) {
		// TODO Auto-generated method stub
//		식자재 수량 수정
		for (Ingredient ingredient : this.ingredients) {
			if (name.equals(ingredient.getName())) {
				ingredient.setAmount(amount);//name으로 찾아서 amount 수정
			} 
/*			식자재 amount 1회 구매량 갯수
			김:30. 단무지:10, 쌀:50, 햄:10, 계란:20, 면사리:20, 어묵:10, 대파:10, 쑥갓:5, 유부:10
			떡 : 20, 치즈:5, 돼지고기:10, 밀가루:10, 빵가루:10, 김치:10
*/			
		}
	}

	@Override
	public void deleteByName(String name) {
		// TODO Auto-generated method stub
//		유통기한만료, 재료 소진시 식자재 삭제
		for (int i =0;  i<ingredients.size(); i++) {
			if(ingredients.get(i).getName().equals(name))
				ingredients.remove(i);
			}
		}
	

	@Override
	public ArrayList<Ingredient> selectAllIng() {
		// TODO Auto-generated method stub
//		식자재 목록 확인
		return ingredients;
	}
	


	
	
	
	
	
}
