package restaurant;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import restaurant.finance.service.FinanceServiceImpl;
import restaurant.food.service.FoodIngServiceImpl;
import restaurant.food.service.FoodServiceImpl;
import restaurant.food.vo.Food;
import restaurant.food.vo.FoodIngredient;
import restaurant.order.service.OrderServiceImpl;
import restaurant.refrigerator.RefrigeratorServiceImpl;
import restaurant.supplier.SupplyServiceImpl;


public class Menu {

	private static final LocalDate LocalDate = null;
	private FinanceServiceImpl finance_service;
	private SupplyServiceImpl supply_service;
	private FoodServiceImpl food_service;
	private FoodIngServiceImpl foodIng_service;
	private OrderServiceImpl order_service;
	private RefrigeratorServiceImpl refrigerator_Service;
	
	public Menu() {
		finance_service = new FinanceServiceImpl();
		supply_service = new SupplyServiceImpl();
		food_service = new FoodServiceImpl();
		foodIng_service = new FoodIngServiceImpl();
		order_service = new OrderServiceImpl();
		refrigerator_Service = new RefrigeratorServiceImpl();
	}
	
	// 메인 메뉴
	public void run(Scanner sc) {
		boolean flag = true;
		while (flag) {
			System.out.println("============= [메인 메뉴] ============");
			System.out.println("[1 요리 관리] [2 냉장고 관리] [3 주문 관리]  [4 공급처 관리]  [5 매출 관리] [6 프로그램 종료]");
			int m = sc.nextInt();
			switch (m) {
			case 1:
				run_f(sc);
				break;
			case 2:
				run_r(sc);
				break;
			case 3:
				run_o(sc);
				break;
			case 4:
				run_s(sc);	
				break;
			case 5:
				run_fi(sc);	
				break;
			case 6:
				flag = false;
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}
	}
	
	public void run_fi(Scanner sc) {
		boolean flag = true;
		while(flag) {
			System.out.println("============= [매출 관리] ============");
			System.out.println("1.현재 총 보유금액 출력 2.입출금내역 출력 3.입금하기 4.출금하기 5.뒤로가기");
			int menu = sc.nextInt();
			switch(menu) {
			case 1:
				finance_service.printTotalMoney();
				break;
			case 2:
				finance_service.printAllFinanceRecords();
				break;
			case 3:
				finance_service.inputMoney(sc);
				break;
			case 4:
				finance_service.outputMoney(sc);
				break;
			case 5:
				flag = false;
				break;
			default:
				System.out.println("번호를 정확히 입력하세요.");
				break;
			}
		}
	}
	
	public void run_f(Scanner sc2) {
		boolean flag = true;
		ArrayList<Food> list = null;
		while(flag) {
			System.out.println("============= [요리 관리] ============");
			System.out.println("1.요리 추가하기 2.요리 전체 보기 3.요리 가격 변경하기 4.요리 이름 변경하기 5.요리 재료 추가하기 6.요리 재료 수량 변경하기 7.요리 재료 삭제하기 8.요리 삭제 하기 9.뒤로가기");
			Scanner sc = new Scanner(System.in);
			int menu = sc.nextInt();
			switch(menu) {
			case 1:
				food_service.addFood(sc);
				break;
			case 2:
				list = food_service.getAllFood();
				food_service.printAll(list);
				break;
			case 3:
				food_service.changePriceByIdx(sc);
				break;
			case 4:
				food_service.changeFoodNameByIdx(sc);
				break;
			case 5:
				foodIng_service.addIngByIdx(sc);
				break;
			case 6:
				foodIng_service.changeAmountByIdx(sc);
				break;
			case 7:
				foodIng_service.delIngByIdx(sc);
				break;
			case 8:
				food_service.delFoodByIdx(sc);
				break;
			case 9:
				flag = false;
				break;
			default:
				System.out.println("번호를 정확히 입력하세요.");
				break;
			}
		}
	}
	
	public void run_o(Scanner sc) {
		boolean flag = true;

		while(flag) {
			System.out.println("============= [주문 관리] ============");
			System.out.println("1.주문추가 2.주문목록 3.주문완료 4.주문취소 5.뒤로가기");
			int m= sc.nextInt();
			
			switch(m) {
				case 1:	
						System.out.println("주문음식선택");
						for(Food f : food_service.getAllFood()) {
							System.out.println(f);
						}
						int orderNum= sc.nextInt();
						if(food_service.checking(orderNum)) {
							System.out.println("수량 입력");
							int orderAmount= sc.nextInt();
							if(order_service.checkIngr(food_service.getFoodByIdx(orderNum).getIdx(), orderAmount)) {
								System.out.println("주문접수 완료");
								order_service.addOrder(orderAmount);
							} else {
								System.out.println("재고확인 필요");
							}
							
						}
							
						/* 수량 선택 
						 * 재료 조회 idx = orderNum
						 * food_service.getFoodByIdx(orderNum).getIdx()
						 */
						
						System.out.println();
						
						break;
				
				case 2:
						order_service.printAllOrder();
						break;
				case 3: 
						System.out.println("완료주문 선택");
						order_service.printAllOrder();
						int finishOrder= sc.nextInt();
						order_service.finishOrder(finishOrder);
						
						break;
				case 4: 
						System.out.println("취소주문 선택");
						order_service.printAllOrder();
						int calcelOrder= sc.nextInt();
						order_service.cancelOrder(calcelOrder);
						System.out.println("취소완료");
						break;
				case 5:
						flag=false;
						break;
			}
		}
	}
	
	public void run_r(Scanner sc) {
		boolean flag = true;
		
		while (flag) {
			System.out.println("============= [냉장고 관리] ============");
			System.out.println("1.식자재 입고 2.유통기한 확인 3.식자재 정보 검색 4.식자재 폐기 5.식자재 목록 6.뒤로가기");
			int menu = sc.nextInt();

			switch(menu) {
				case 1:
					supply_service.getAllIng();
					refrigerator_Service.BuyIng(sc);
					break;
				case 2: 
					refrigerator_Service.editDue(toString(), LocalDate);
				case 3:
					refrigerator_Service.getByName(sc);
					break;
				case 4:
					refrigerator_Service.getAllIng();
					refrigerator_Service.deleteIng(sc);
					break;
				case 5:
					refrigerator_Service.getAllIng();
					break;
				case 6:
					//restaurant.refrigerator.dao.RestaurantRefrigeratorDaoImpl.getInstance().stop();
					flag=false;
					break;
			}

		}
	}
	
	public void run_s(Scanner sc) {
		boolean flag = true;
		
		while(flag) {
			System.out.println("============= [공급처 관리] ============");
			System.out.println("1.식자재 정보 검색 2.식자재 구매 3.식자재 목록 4.뒤로가기");
			int menu = sc.nextInt();
			
			switch(menu) {
			case 1:
				supply_service.getByName(sc);
				break;
			case 2:
				supply_service.getAllIng();
				supply_service.buyIng(sc);
				break;
			case 3:
				supply_service.getAllIng();
				break;
			case 4:
				//restaurant.supplier.dao.SupplyDaoImpl.getInstance().save();
				flag=false;
				break;
	
			}
		}
	}
	

}
