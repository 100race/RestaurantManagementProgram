package restaurant;

import java.util.Scanner;

import restaurant.supplier.SupplyServiceImpl;

public class Menu {
	public void run_s() {
		SupplyServiceImpl service = new SupplyServiceImpl();
		while(true) {
		System.out.println("=============공급처 관리============");
		System.out.println("1.식자재 정보 검색 2.식자재 구매 3.식자재 목록 4.종료");
		Scanner sc = new Scanner(System.in);
		int menu = sc.nextInt();
		
		switch(menu) {
		case 1:
			service.getByName(sc);
			break;
		case 2:
			service.BuyIng(sc);
			break;
		case 3:
			service.getAllIng();
			break;
		case 4:
			return;

		}
		}
	}
}
