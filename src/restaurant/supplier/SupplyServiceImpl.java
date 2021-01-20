package restaurant.supplier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import restaurant.Main;
import restaurant.food.vo.Ingredient;
import restaurant.supplier.dao.DaoImpl;

public class SupplyServiceImpl implements SupplyService{
	
	private DaoImpl dao;
	
	public SupplyServiceImpl() {
		dao = new DaoImpl();
	}
	
	@Override
	public void getByName(Scanner sc) {
		System.out.println("찾을 식자재 이름을 입력하세요");
		String name = sc.next();
		ArrayList<Ingredient> list = dao.searchByName(name);
		if(list == null) {
			System.out.println("식자재가 없습니다.");
		}
		else {
			System.out.println("===============찾은 식자재 목록===============");
			for(Ingredient i : list) 
				System.out.println(i);
		}
		
	}
	
	/*
	 * 입고가 우선 없어서 만들었는데
	 * 반품을 만들지 말지??
	 * 만들게 되면 냉장고에서 updateAmount 이용해서 만들어야 할듯
	 */

	@Override
	public void refundIng(Scanner sc) {
		System.out.println("반품할 식자재 이름을 입력하세요");
		String name = sc.next();
		System.out.println("반품할 수량을 입력하세요");
		int amount = sc.nextInt();
		dao.updateAmount(name, amount);
		System.out.println(amount+"개 입력 되었습니다");
		
	}
	
	 //식자재 구매
	@Override
	public void BuyIng(Scanner sc) {
		System.out.println("구매할 식자재 이름을 입력하세요");
		String name = sc.next();
		System.out.println("구매 수량을 입력하세요");
		int amount = (sc.nextInt());
		int price = dao.searchByName(name).get(0).getPrice();
		if(Main.TOTAL_MONEY - price*amount >= 0) {
			dao.updateAmount(name, -amount); //음수처리
			Main.TOTAL_MONEY -= price;
			System.out.println(amount+"개 구매 되었습니다");
		}else{
			System.out.println("잔액이 부족하여 구매 불가");
		}
		
	}

	@Override
	public void getAllIng() {
		ArrayList<Ingredient> list = dao.selectAllIng();
		if( list == null) {
			System.out.println("식자재가 없습니다.");
		}else {
			for(Ingredient i : list) 
				System.out.println(i);
		}
		
	}
	
	//끝날시 실행, 파일에 데이터 저장
	public void stop() {
		dao.stop();
		}
	}
	

