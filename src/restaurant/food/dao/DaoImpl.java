package restaurant.food.dao;

import java.util.ArrayList;
import restaurant.food.vo.Food;
import restaurant.food.vo.Ingredient;
/**
 * 
 * @author Han
 *
 */
public class DaoImpl implements Dao {
	private ArrayList<Food> foods;
	
	public DaoImpl() {
		foods = new ArrayList<>();
	}

	/**
	 * @param food
	 * void : Food 객체를 추가함
	 */
	@Override
	public void insert(Food food) {
		foods.add(food);
		System.out.println("음식 메뉴 추가 완료!");
	}
	
	/**
	 * 음식 인덱스로 음식을 찾아 반환하는 메소드
	 * @param  : 찾고자 하는 음식의 idx
	 * @return : 입력된 idx와 일치하는 음식을 반환, 찾지 못할 경우 null 반환
	 */
	@Override
	public Food searchByIdx(int idx) {
		Food f = new Food();
		f.setIdx(idx);
		//foods에 f가 존재하면 f의 인덱스를 리턴, 없으면 -1 리턴
		int index = foods.indexOf(f); 
		if (index < 0) { //f가 없으면(-1이면)
			return null;
		} else {
			return foods.get(index); //인덱스에 있는 객체 리턴
		}
	}
	
	/**
	 * 음식 이름으로 음식을 찾아 반환하는 메소드
	 * @param  : 찾고자 하는 음식의 name
	 * @return : 입력된 name과 일치하는 음식을 반환, 찾지 못할 경우 null 반환
	 */
	@Override
	public Food searchByName(String name) {
		Food f = new Food();
		f.setFoodName(name);
		//foods에 f가 존재하면 f의 인덱스를 리턴, 없으면 -1 리턴
		int index = foods.indexOf(f);
		if (index < 0) { //f가 없으면(-1이면)
			return null;
		} else {
			return foods.get(index); //인덱스에 있는 객체 리턴
		}
	}
	
	/**
	 * 음식 인덱스로 음식을 찾아 삭제하는 메소드
	 * @param  : 찾고자 하는 음식의 idx
	 * void : 찾은 음식을 삭제, 없으면 sysout출력
	 */
	@Override
	public void deleteByIdx(int idx) {
		Food f = searchByIdx(idx);
		if(f != null) {
			foods.remove(f);
			System.out.println("음식 메뉴 삭제 완료!");
		}else {
			System.out.println("없는 번호 입니다. 다시 확인해주세요!");
		}
	}

	/**
	 * 음식 이름으로 음식을 찾아 삭제하는 메소드
	 * @param  : 찾고자 하는 음식의 name
	 * void : 찾은 음식을 삭제, 없으면 sysout출력
	 */
	@Override
	public void deleteByName(String name) {
		Food f = searchByName(name);
		if(f != null) {
			foods.remove(f);
			System.out.println("음식 메뉴 삭제 완료!");
		}else {
			System.out.println("없는 번호 입니다. 다시 확인해주세요!");
		}
	}

	/**
	 * 음식 인덱스로 음식을 찾아 재료를 수정하는 메소드
	 * @param  : 찾고자 하는 음식의 idx
	 * void : 찾은 음식의 재료를 수정, 없으면 sysout출력
	 */
	@Override
	public void updateIng(int idx, ArrayList<Ingredient> ingredients) {
		Food f = searchByIdx(idx);
		if(f != null) {
			f.setIngredient(ingredients);
		}else {
			System.out.println("없는 번호 입니다. 다시 확인해주세요!");
		}
	}

	/**
	 * 음식 인덱스로 음식을 찾아 가격을 수정하는 메소드
	 * @param  : 찾고자 하는 음식의 idx
	 * void : 찾은 음식의 가격을 수정, 없으면 sysout출력
	 */
	@Override
	public void updatePrice(int idx, int price) {
		Food f = searchByIdx(idx);
		if(f != null) {
			f.setPrice(price);
		}else {
			System.out.println("없는 번호 입니다. 다시 확인해주세요!");
		}
	}

	/**
	 * @return : 모든 음식을 반환
	 */
	@Override
	public ArrayList<Food> getAllFood() {
		return foods;
	}

}
