package restaurant;

import java.util.Scanner;

import restaurant.food.service.ServiceImpl;
import restaurant.order.service.OrderServiceImpl;
import restaurant.supplier.SupplyServiceImpl;


public class Menu {
	private SupplyServiceImpl supply_service;
	private ServiceImpl food_service;
	private OrderServiceImpl order_service;
	
	public Menu() {
		supply_service = new SupplyServiceImpl();
		food_service = new ServiceImpl();
		order_service = new OrderServiceImpl();
	}
	
	public void run(Scanner sc) {// 상위 메뉴
		boolean flag = true;
		//service.start(); //시작하면 자동으로 파일 로드
		while (flag) {
			System.out.println("1.음식 관리 2.공급처 관리 3.주문 관리 4.종료");
			int m = sc.nextInt();
			switch (m) {
			case 1:
				run_f(sc);
				break;
			case 2:
				run_s(sc);
				break;
			case 3:
				run_o(sc);
				break;
			case 4:
				flag = false;
				break;
			}
		}
		//service.stop(); //시작하면 자동으로 파일 저장
	}
	
	public void run_f(Scanner sc2) {
		boolean flag = true;
		while(flag) {
			System.out.println("=============음식 관리============");
			System.out.println("1.음식 추가하기 2.음식 전체 보기 3.음식 가격 변경하기 4.음식 이름 변경하기 5.기존 음식 재료 추가하기 6.기존 음식 재료 삭제하기 7.음식 삭제 하기 8.종료");
			Scanner sc = new Scanner(System.in);
			int menu = sc.nextInt();
			switch(menu) {
			case 1:
				food_service.addFood(sc);
				break;
			case 2:
				food_service.printAllFood();
				break;
			case 3:
				food_service.changePriceByIdx(sc);
				break;
			case 4:
				food_service.changeFoodNameByIdx(sc);
				break;
			case 5:
				food_service.addIngByIdx(sc);
				break;
			case 6:
				food_service.delIngByIdx(sc);
				break;
			case 7:
				food_service.delFoodByIdx(sc);
				break;
			case 8:
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
			order_service.start();
			System.out.println("=============주문 관리============");
			System.out.println("1.주문추가 2.주문목록 3.주문취소 4.종료");
			int m= sc.nextInt();
			switch(m) {
				case 1:	
						System.out.println("주문음식선택");
					    //Service.getAllFood(); 
						int foodIdx= sc.nextInt();
						if(order_service.checkIngr(foodIdx)) {
							System.out.println("주문수량");
							int foodAmount=sc.nextInt();
							order_service.addOrder(foodAmount);
						} else {
							System.out.println("재고없음");
						}
						order_service.orderSave();
						break;
				
				case 2:
						order_service.printAllOrder();
						break;
						
				case 3: 
						System.out.println("취소주문 선택");
						order_service.printAllOrder();
						int calcelOrder= sc.nextInt();
						order_service.finishOrder(calcelOrder);
						break;
				case 4:
						flag=false;
						break;
			}
		}
	}
	
	public void run_s(Scanner sc2) {
		
		while(true) {
			System.out.println("=============공급처 관리============");
			System.out.println("1.식자재 정보 검색 2.식자재 구매 3.식자재 목록 4.종료");
			Scanner sc = new Scanner(System.in);
			int menu = sc.nextInt();
			
			switch(menu) {
			case 1:
				supply_service.getByName(sc);
				break;
			case 2:
				supply_service.BuyIng(sc);
				break;
			case 3:
				supply_service.getAllIng();
				break;
			case 4:
				return;
	
			}
		}
	}
}
