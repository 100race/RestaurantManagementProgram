package restaurant.food.service;
import java.util.ArrayList;



import java.util.Scanner;
import restaurant.food.dao.FoodIngDao;
import restaurant.food.dao.FoodIngDaoImpl;
import restaurant.food.vo.Food;
import restaurant.food.vo.FoodIngredient;
import restaurant.food.vo.Ingredient;
import restaurant.supplier.SupplyServiceImpl;
//import sun.security.jca.GetInstance.Instance;

public class FoodIngServiceImpl implements FoodIngService {
	public FoodIngDao foodIngDao = FoodIngDaoImpl.getInstance();
	public FoodService food_service = new FoodServiceImpl();
	private SupplyServiceImpl supply_service = new SupplyServiceImpl(); //수정함
	ArrayList<Ingredient> suppList = restaurant.supplier.dao.SupplyDaoImpl.getInstance().getIngredients();
	
	public FoodIngServiceImpl() {
		foodIngDao = FoodIngDaoImpl.getInstance();
	}
	
	/**
	 * @param : 출력하려는 ing arraylist
	 * @return : 음식을 전부 반환
	 */
	@Override
	public void printAll(ArrayList<FoodIngredient> ing) {
		for(FoodIngredient i : ing) {
			System.out.println(i);
		}
	}

	/**
	 * @return : 요리 재료를 전부 반환
	 */
	@Override
	public ArrayList<FoodIngredient> getAllIng() {
		ArrayList<FoodIngredient> ings = foodIngDao.getAllIng();
		if(ings != null) {
			return ings;
		}else {
			System.out.println("등록된 요리의 재료가 하나도 없습니다.");
		}
		return null;
	}

	/**
	 * @param : scanner로 재료 추가할 음식 번호 입력 받음
	 * void : 입력받은 재료를 추가
	 */
	@Override
	public void addIngByIdx(Scanner sc) {
		ArrayList<Food> list = null;
		System.out.println("========= 요리 재료 추가 =========");
		list = food_service.getAllFood();
		food_service.printAll(list);
		System.out.println("========= ========= =========");
		System.out.print("재료 추가할 요리 번호: ");
		int num = sc.nextInt();
		boolean flag = food_service.checking(num);
		String name = null;
		int amount = 0;
		if(flag) {
			System.out.println("========= ========= =========");
			supply_service.getAllIng(); //공급처 재료 리스트 보여줌
			System.out.print("재료 이름: ");
			name = sc.next();
			if(supply_service.getByName(name) != null) {
				if(name.equals(supply_service.getByName(name).getName())) {
					System.out.print("재료 수량: ");
					amount = sc.nextInt();
					if(amount > 0) {
						foodIngDao.insertIng(num, name, amount);
					}else {
						System.out.println("수량이 잘못 되었습니다.");
					}
				}
			}else {
				System.out.println("정확한 이름을 입력하세요.");
			}
		}else {
			System.out.println("번호를 잘못 입력하였습니다.");
		}
	}

	/**
	 * @param : scanner로 재료 변경할 음식 번호 입력 받음
	 * void : 입력받은 수량으로 변경
	 */
	@Override
	public void changeAmountByIdx(Scanner sc) {
		System.out.println("========= 재료 수량 변경 =========");
		ArrayList<FoodIngredient> iList= foodIngDao.getAllIng();
		this.printAll(iList);
		System.out.println("========= ========= =========");
		System.out.println("요리 번호 : ");
		int num = sc.nextInt();
		if(num == foodIngDao.getIngByIdx(num).getIdx()) {
			System.out.println("========= ========= =========");
			System.out.println("재료 이름 : ");
			String name = sc.next();
			if(name.equals(foodIngDao.getIngByName(name).getName())) {
				System.out.println("========= ========= =========");
				System.out.println("변경할 재료 수량 : ");
				int amount = sc.nextInt();
				if(amount > 0) { 
					foodIngDao.changeIngAmount(num, name, amount);
				}else {
					System.out.println("적합한 수량이 아닙니다.");
				}
			}else {
				System.out.println("이름을 잘못 입력하였습니다.");
			}
		}else {
			System.out.println("번호를 잘못 입력하였습니다.");
		}
		
		
	}
	
	/**
	 * @param : scanner로 삭제할 음식 번호/삭제할 재료 번호 입력 받음
	 * void : 입력받은 음식의 재료를 삭제
	 */
	@Override
	public void delIngByIdx(Scanner sc) {
		System.out.println("========= 요리 재료 삭제 =========");
		ArrayList<FoodIngredient> iList= foodIngDao.getAllIng();
		printAll(iList);
		System.out.println("========= ========= =========");
		System.out.println("요리 번호 : ");
		int num = sc.nextInt();
		if(num == foodIngDao.getIngByIdx(num).getIdx()) {
			System.out.println("========= ========= =========");
			System.out.println("재료 이름 : ");
			String name = sc.next();
			if(name.equals(foodIngDao.getIngByName(name).getName())) {
				foodIngDao.deleteIng(num, name);
			}else {
				System.out.println("재료 이름을 정확히 입력해주세요.");
			}
		}else {
			System.out.println("번호를 잘못 입력하였습니다.");
		}
	}

}
