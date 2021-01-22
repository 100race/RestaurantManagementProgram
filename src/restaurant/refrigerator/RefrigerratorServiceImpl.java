package restaurant.refrigerator;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import restaurant.food.vo.Ingredient;
import restaurant.refrigerator.dao.RestaurantRefrigeratorDaoImpl;
import restaurant.supplier.dao.DaoImpl;

public class RefrigerratorServiceImpl implements RefrigerratorService {

		private RestaurantRefrigeratorDaoImpl rRDao;
		private DaoImpl sRDao;
		
		public RefrigerratorServiceImpl() {
			this.rRDao = new RestaurantRefrigeratorDaoImpl();
			this.sRDao = new DaoImpl();
		}
		
	
	
	@Override
	public void buying(Ingredient ing) {
		// TODO Auto-generated method stub
		/*공급처 식자재 차감, totalMoney 차감
*/
		System.out.println("공급처냉장고에서 16종 식자재를 1회 구매하였습니다. 식자재 amount 1회 구매량 갯수\r\n" + 
				"		김:30. 단무지:10, 쌀:50, 햄:10, 계란:20, 면사리:20, 어묵:10, 대파:10, 쑥갓:5, 유부:10\r\n" + 
				"		떡 : 20, 치즈:5, 돼지고기:10, 밀가루:10, 빵가루:10, 김치:10");
		sRDao.addIng(ing);
			
		
		
		} 


	@Override
	public void updateDue(LocalDate Date) {
		// TODO Auto-generated method stub
		
/*		기능 정의 필요
		유통기한 무한?인지 유한인지 정하고 무한이면 유통기한 수정이 굳이 필요x
		아니면 단순히 유통기한을 변경하고 싶을 때 쓰는 것?
		개별 유통기한 받을 수 있게 idx는 name으로 특정
		12시간 단위로 유통기한 체크 
*/		
		
	}

	@Override
	public void getByName(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("식자재 검색할 이름 입력");
		String name = sc.next();
		ArrayList<Ingredient> ingr = rRDao.searchByName(name);
		if(ingr == null) {
			System.out.println("식자재 없음");
		}
		else {
			System.out.println("식자재 목록");
			for(Ingredient i : ingr)
				System.out.println(i);
		}
		
	}

	@Override
	public void editDue(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("유통기한 변경할 식자재 이름을 입력하세요");
		String name = sc.next();
		System.out.println("변경할 유통기한을 입력하세요");
		LocalDate date1 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
//		updatedue 반환??
		
		
		
	}

	@Override
	public void deleteIng(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("삭제할 식자재 이름을 입력하세요");
		String name = sc.next();
		System.out.println("해당 식자재를 삭제하실겁니까?");
		rRDao.deleteByName(name);
		System.out.println(name+"이 삭제되었습니다");
		
		
	}

	@Override
	public void inIng(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("냉장고에 넣을 식자재를 입력하세요");
		String name;
		String inputname;
		if(rRDao.searchByName(name)) {
			
		}
		
		
	}

	@Override
	public void outIng(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public void getAllIng() {
		// TODO Auto-generated method stub
		ArrayList<Ingredient> ingr = rRDao.selectAllIng();
		if( ingr == null) {
			System.out.println("식자재가 없습니다.");
		}else {
			for(Ingredient i : ingr) 
				System.out.println(i);
		}

	}
	@Override
	public void PrintAllIng() {
		// TODO Auto-generated method stub
		System.out.println("공급처 냉장고에 있는 모든 식자재 확인하기");
		ArrayList<Ingredient> ingr = sRDao.selectAllIng();
		System.out.println(ingr);
//		메소드 1개 가지고 공급처 냉장고와 식당냉장고 바꿔가며 출력하려면?

	}

}
