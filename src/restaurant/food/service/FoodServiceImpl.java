package restaurant.food.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import restaurant.food.dao.FoodDao;
import restaurant.food.dao.FoodDaoImpl;
import restaurant.food.vo.Food;
import restaurant.food.vo.Ingredient;
import restaurant.supplier.SupplyServiceImpl;
/**
 * 
 * @author Han
 *
 */
public class FoodServiceImpl implements FoodService {
	public FoodDao dao = FoodDaoImpl.getInstance(); //수정함
	//private SupplyServiceImpl supply_service = new SupplyServiceImpl(); //수정함
	//ArrayList<Ingredient> suppList = restaurant.supplier.dao.SupplyDaoImpl.getInstance().getIngredients();
	
	public FoodServiceImpl() {
		dao = FoodDaoImpl.getInstance();
	}
	
	/**
	 * @param 찾고자 하는 요리의 번호
	 * void : 입력 받은 요리가 DB에 있는지 확인, 없으면 false 반환
	 */
	@Override
	public boolean checking(int num) {
		Food f = dao.searchByIdx(num);
		if(f != null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * @param 출력하려는 food arraylist
	 * void : 리스트의 food를 전부 출력
	 */
	@Override
	public void printAll(ArrayList<Food> food) {
		for(Food f : food) {
			System.out.println(f);
		}
	}
	
	/**
	 * @param scanner로 음식 이름/가격/재료 이름/재료 수량 입력받음
	 * void : 입력 받은 Food 객체를 추가함
	 */
	@Override
	public void addFood(Scanner sc) {
		System.out.println("========= 요리 등록 =========");
		System.out.print("요리 이름 : ");
		String name = sc.next();
		System.out.print("가격 : ");
		int price = sc.nextInt();
		dao.insert(name, price);
	}

	/**
	 * @param  : 찾고자 하는 음식의 idx
	 * @return : 입력된 idx와 일치하는 음식을 반환, 찾지 못할 경우 null 반환
	 */
	@Override
	public Food getFoodByIdx(int idx) {
		Food tempFood = dao.searchByIdx(idx);
		if(tempFood != null){
			return tempFood;
		}else {
			System.out.println("번호를 잘못 입력하였습니다.");
		}
		return null;
	}

	/**
	 * @return : 음식을 전부 반환, 없는 경우 null 반환
	 */
	@Override
	public ArrayList<Food> getAllFood() {
		ArrayList<Food> foods = dao.getAllFood();
		if(foods != null) {
			return foods;
		}else {
			System.out.println("등록된 요리가 하나도 없습니다.");
		}
		return null;
	}

	/**
	 * @param  : 찾고자 하는 음식의 idx
	 * void : 입력된 idx와 일치하는 음식을 출력
	 */
	@Override
	public void printFoodByIdx(Scanner sc) {
		System.out.println("");
		int idx = sc.nextInt();
		Food tempFood = this.getFoodByIdx(idx);
		if(tempFood != null){
			System.out.println(tempFood);
		}else {
			System.out.println("해당하는 요리가 없습니다.");
		}
	}

	/**
	 * @param : scanner로 음식 번호/음식 가격 입력 받음
	 * void : 입력받은 번호의 음식의 가격을 변경
	 */
	@Override
	public void changePriceByIdx(Scanner sc) {
		System.out.println("========= 요리 가격 수정 =========");
		printAll(getAllFood());
		System.out.println("========= ========= =========");
		System.out.print("변경할 요리 번호:");
		int num = sc.nextInt();
		boolean flag = checking(num);
		if(flag) {
			System.out.print("새 가격: ");
			int price = sc.nextInt();
			dao.changePrice(new Food(num, price));
		}else {
			System.out.println("번호를 잘못 입력하였습니다.");
		}
	}
	
	/**
	 * @param : scanner로 변경할 요리 번호, 변경할 재료 이름 입력 받음
	 * void : 입력받은 요리의 이름을 변경
	 */
	@Override
	public void changeFoodNameByIdx(Scanner sc) {
		System.out.println("========= 요리 이름 변경 =========");
		printAll(getAllFood());
		System.out.println("========= ========= =========");
		System.out.print("변경할 요리 번호: ");
		int num = sc.nextInt();
		boolean flag = checking(num);
		if(flag) {
			System.out.print("새 이름: ");
			String name = sc.next();
			dao.changeName(new Food(num, name));
		}else {
			System.out.println("번호를 잘못 입력하였습니다.");
		}

	}

	/**
	 * @param : scanner로 삭제할 음식 번호 입력 받음
	 * void : 입력받은 음식을 삭제
	 */
	@Override
	public void delFoodByIdx(Scanner sc) {
		System.out.println("========= 요리 삭제 =========");
		printAll(getAllFood());
		System.out.println("========= ========= =========");
		System.out.print("삭제할 요리 번호: ");
		int num = sc.nextInt();
		boolean flag = checking(num);
		if(flag) {
			dao.deleteByIdx(num);
		} else {
			System.out.println("번호를 잘못 입력하였습니다.");
		}
	}

	

	
}
