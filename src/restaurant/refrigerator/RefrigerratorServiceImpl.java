package restaurant.refrigerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import restaurant.Main;
import restaurant.food.vo.Ingredient;
import restaurant.refrigerator.dao.RestaurantRefrigeratorDaoImpl;
import restaurant.supplier.dao.DaoImpl;

public class RefrigerratorServiceImpl implements RefrigerratorService {

<<<<<<< HEAD
		private RestaurantRefrigeratorDaoImpl rRDao;
		private DaoImpl sRDao;
		
		public RefrigerratorServiceImpl() {
			
			ArrayList<Ingredient> arr = new ArrayList<>(); //저장소
		
			this.rRDao = new RestaurantRefrigeratorDaoImpl();
			this.sRDao = restaurant.supplier.dao.DaoImpl.getInstance();
		}

=======
	private RestaurantRefrigeratorDaoImpl rRDao;
	private DaoImpl sRDao;
	
>>>>>>> master
	
	public RefrigerratorServiceImpl() {
	
		ArrayList<String> ingr = new ArrayList<>(); //저장소	
		
		this.rRDao = new RestaurantRefrigeratorDaoImpl();
		this.sRDao = new DaoImpl();
	}
	

	
	@Override
	public void buying(Ingredient ing) {
		// TODO Auto-generated method stub
		//공급처 식자재 차감, totalMoney 차감
		 
		ArrayList<Ingredient> ingr = new ArrayList<>(); //저장소	

		// 공급처 냉장고에서 식자재를 가져온다
		sRDao.selectAllIng().stream().forEach(ing2->{
	
			System.out.println("공급처 냉장고에서 "+ing2.getName()+"식자재를 "+ "30개 구매하였습니다.");
			int amount =ing2.getAmount()-30;
			ing2.setAmount(amount);//공급처 냉장고에서 구매수량 차감
			System.out.println("공급처 냉장고 잔여수량은"+ing2.getName()+ ing2.getAmount()+"개 입니다.");
			
			ing2.setAmount(30);
			rRDao.addIng(ing2);;//식당 냉장고에 공급처 냉장고에서 구매한 식자재와 수량 넣기
			
			int price = ing2.getPrice()*amount;
			System.out.println(ing2.getPrice());
			Main.TOTAL_MONEY *= - price;
			//total money차감
			
			
		//System.out.println(ing2.getAmount());//실제 잔여수량 확인용
		});
			
	}
		//식자재 amount 1회 구매량 최소 갯수 5~30개->30개로 통일함
		//김:30, 단무지:10, 쌀:50, 햄:10, 계란:20, 면사리:20, 어묵:10, 
		//대파:10, 쑥갓:5, 유부, 10 떡 : 20, 치즈:5, 돼지고기:10, 밀가루:10, 빵가루:10, 김치:10
				
		

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

	public void editDue(String name, LocalDate Date) {
		sRDao.selectAllIng().stream().forEach(ing3->{
			//구매 시 식자재 유통기한 setDue, nowdate+5일 설정
			//날짜 조회 ing3.getname()
			//날짜 지났을때 로직은??boolean -> T/F 
			//delete 식자재 삭제
			//System.out.println("유통기한 지난 식자재 리스트 입니다.");
			//다시 구매 설정 LocalDate.parse("2021-01-21")
			//nowdate+5일
		});
	
		// TODO Auto-generated method stub
		/*		기능 정의 
		* 		사올 때 식자재 유통기한 설정, 유통기한 지난 후, 조회만 해서 , 유통기한 지난 재고 리스트입니다.하고 
		* boolean -> T/F , 삭제후, 다시 사올 때, now date로 3일, 예시로 해서 호출
		* 		
		*/	
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
		System.out.println("식당 냉장고에 있는 모든 식자재 확인하기");
		ArrayList<Ingredient> ingr = rRDao.selectAllIng();
		System.out.println(ingr);

	}




}

