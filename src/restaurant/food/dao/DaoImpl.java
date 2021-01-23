package restaurant.food.dao;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import restaurant.food.vo.Food;
import restaurant.food.vo.Ingredient;
/**
 * 
 * @author Han
 *
 */
public class DaoImpl implements Dao {
	private static ArrayList<Food> foods;
	
	public DaoImpl() {
		foods = new ArrayList<>();
	}
	
	public static ArrayList<Food> getFoods() {
		return foods;
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
		f.setIdx(idx--);
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
	 * 음식 인덱스로 음식을 찾아 재료를 추가하는 메소드
	 * @param  : 찾고자 하는 음식의 idx, 추가할 ingredients
	 * void : 찾은 음식의 재료를 추가, 없으면 sysout출력
	 */
	@Override
	public void insertIng(int idx, Map<String, Integer> ingredient) {
		Food f = searchByIdx(idx);
		if(f != null) {
			f.getIngredient().putAll(ingredient);
			System.out.println("음식 재료 추가 완료!");
		}else {
			System.out.println("없는 번호 입니다. 다시 확인해주세요!");
		}
	}
	
	/**
	 * 음식 인덱스로 음식을 찾아 재료를 삭제하는 메소드
	 * @param  : 찾고자 하는 음식의 idx, 삭제할 ingredients
	 * void : 찾은 음식의 재료를 수정, 없으면 sysout출력
	 */
	@Override
	public void deleteIng(int idx, String ingName) {
		Food f = searchByIdx(idx);
		if(f != null) {
			f.getIngredient().remove(ingName);
			System.out.println("음식 재료 삭제 완료!");
		}else {
			System.out.println("없는 번호 입니다. 다시 확인해주세요!");
		}
	}

	/**
	 * 음식 인덱스로 음식을 찾아 재료의 수량을 변경하는 메소드
	 * @param  : 찾고자 하는 음식의 idx, 변경할 재료의 key, 변경할 value
	 * void : 찾은 음식의 재료의 수량을 수정, 없으면 sysout출력
	 */
	@Override
	public void changeIngCnt(int idx, String key, int value) {
		Food f = searchByIdx(idx);
		if(f != null) {
			if(value > 0) { //수량이 양수이면
				f.getIngredient().replace(key, value);
				System.out.println("음식 재료 수량 변경 완료!");
			}else {
				System.out.println("수량이 0 이하입니다!");
			}
		}else {
			System.out.println("없는 번호 입니다. 다시 확인해주세요!");
		}
	}

	/**
	 * 음식 인덱스로 음식을 찾아 가격을 수정하는 메소드
	 * @param  : 찾고자 하는 음식의 idx, 변경할 price
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
	 * 음식 인덱스로 음식을 찾아 음식 이름을 수정하는 메소드
	 * @param  : 찾고자 하는 음식의 idx, 변경할 name
	 * void : 찾은 음식의 이름을 수정, 없으면 sysout출력
	 */
	@Override
	public void updateName(int idx, String name) {
		Food f = searchByIdx(idx);
		if(f != null) {
			f.setFoodName(name);
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
