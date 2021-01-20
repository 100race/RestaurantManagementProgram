package restaurant.supplier;

import java.util.ArrayList;
import java.util.Scanner;

import restaurant.food.vo.Ingredient;
import restaurant.supplier.dao.DaoImpl;

public class SupplyServiceImpl implements SupplyService{
	private DaoImpl dao;
	
	public SupplyServiceImpl() {
		dao = new DaoImpl();
	}


/* 첫실행시 한번만 초기화 - Ingredient 직렬화
* LocalDate 클래스는 public 생성자를 제공하지 않기 때문에 객체를 생성할 때는 now()나, 
* of(), parse()와 같은 정적 메소드를 사용하도록 되어 있습니다. 
* 기본 포멧인 yyyy-MM-dd 형태의 문자열을 parse() 메소드에 넘길 수 있습니다.
*/
	public void init() { 
	//	Ingredient i1 = new Ingredient("김", 9999999, 100, "")
				
	}
	
	public void start() { //시작시 실행
		
	}
	
	@Override
	public void getByName(Scanner sc) {
		System.out.println("찾을 식자재 이름을 입력하세요");
		String name = sc.next();
		ArrayList<Ingredient> list = dao.searchByName(name);
		System.out.println("===============찾은 식자재 목록===============");
		for(Ingredient i : list) 
			System.out.println(i);
	}

	@Override
	public void InIng(Scanner sc) {
		System.out.println("수정할 식자재 이름을 입력하세요");
		String name = sc.next();
		System.out.println("입고할 수량을 입력하세요");
		int amount = sc.nextInt();
		dao.updateAmount(name, amount);
		System.out.println(amount+"개 입력 되었습니다");
		
	}

	@Override
	public void OutIng(Scanner sc) {
		System.out.println("수정할 식자재 이름을 입력하세요");
		String name = sc.next();
		System.out.println("출고 수량을 입력하세요");
		int amount = -(sc.nextInt()); //음수처리
		dao.updateAmount(name, amount);
		System.out.println(amount+"개 출고 되었습니다");
		
	}

	@Override
	public void getAllIng() {
		for(Ingredient i : dao.selectAllIng()) 
			System.out.println(i);
	}
	
	public void stop() {
		
	}
	

}
