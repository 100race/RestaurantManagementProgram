package main;

import java.util.Scanner;

import service.OrderService;
import service.OrderServiceImpl;

public class menu {

	int num;
	private OrderService service; //주문 service
	//private foodService fService 음식 service
	public menu() {
		service=new OrderServiceImpl();
	}
	
	public void run(Scanner sc) {
		boolean flag = true;
		
		
		while(flag) {
			service.start();
			System.out.println("1.주문추가 2.주문목록 3.주문취소 4.종료");
			int m= sc.nextInt();
			
			switch(m) {
				case 1:	
						System.out.println("주문음식선택");
					    //fService.getAllFood(); 
						int foodIdx= sc.nextInt();
						if(service.checkIngr(foodIdx)) {
							System.out.println("주문수량");
							int foodAmount=sc.nextInt();
							service.addOrder(foodAmount);
						} else {
							System.out.println("재고없음");
						}
						service.orderSave();
						break;
				
				case 2:
						service.printAllOrder();
						break;
						
				case 3: 
						System.out.println("취소주문 선택");
						service.printAllOrder();
						int calcelOrder= sc.nextInt();
						service.finishOrder(calcelOrder);
						break;
				case 4:
						flag=false;
						break;
			}
		}
	}
}
