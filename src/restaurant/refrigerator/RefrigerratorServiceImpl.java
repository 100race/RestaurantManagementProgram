package restaurant.refrigerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import restaurant.Main;
import restaurant.food.vo.Ingredient;
import restaurant.refrigerator.dao.RestaurantRefrigeratorDaoImpl;
import restaurant.supplier.dao.DaoImpl;

public class RefrigerratorServiceImpl implements RefrigerratorService {


		private RestaurantRefrigeratorDaoImpl rRDao;
		private DaoImpl sRDao;
		private ArrayList<Ingredient> supplyIngredients;
		
		public RefrigerratorServiceImpl(ArrayList<Ingredient> supplyIngredients) {
			
			ArrayList<Ingredient> arr = new ArrayList<>(); //저장소
		
			this.rRDao = new RestaurantRefrigeratorDaoImpl();
			this.sRDao = restaurant.supplier.dao.DaoImpl.getInstance();
			this.supplyIngredients = supplyIngredients;
		}


	/*
	 공급처 냉장고에서 식자재 이름, 가격 구매하는 식으로

	 */
	@Override
	public void firstBuyIng() {
		// TODO Auto-generated method stub

		// 식당냉장고 및 totalmoney처리
		supplyIngredients.stream().forEach(sIng->{
			
			int price = sIng.getPrice();
			String name = sIng.getName();
			int amount = sIng.getAmount()-30;//잔여수량 default 9999999개
			int amount2 = sIng.getAmount()-9999969; //구매량 30개
			
			
			//System.out.println(amount);
			//System.out.println(price*amount2+"원");

			if(Main.TOTAL_MONEY - price*amount2>=0) {
				//sRDao.updateAmount(name, amount);//공급처 냉장고에서 구매수량 차감
				System.out.println("공급처 냉장고에서"+name+"식자재 구입하였습니다."+"남은 수량은"+ amount+"개 입니다.");
				

				Main.TOTAL_MONEY -= price;//total money차감
				System.out.println(price*amount2+"원 차감하였습니다.");
			}else {
				System.out.println("잔액 부족으로 구매 불가.");//잔액 부족 시 구매불가 내용 추가
			}
			//System.out.println(ing2.getAmount());//실제 잔여수량 확인용
			//현재 김이 +30개로 더 구매되어짐, 가격차감 안되고 있음.
			rRDao.addIng(sIng);//식당냉장고에 구매 정보 넣기
			
		});

		
		ArrayList<Ingredient> rRAll = rRDao.selectAllIng();
		System.out.println("rRall.size:" + rRAll.size());//수량 확인
		
		for(Ingredient ing2 : rRAll) {//람다 범위 외 변수중복으로 foreach문 사용
			String name = ing2.getName();
			int amount = ing2.getAmount() - 19999938;//왜 재고가 9999999*2배로 나오는지 확인
			
			//ing2.setAmount(-9999969);
			
//			rRDao.updateAmount(name, amount);//식당 냉장고에 공급처 냉장고에서 구매한 식자재와 수량 넣기
			System.out.println("공급처 냉장고에서 "+name+"식자재를 "+ amount+"개 구매하였습니다.");
		}

			
	}
		//식자재 amount 1회 구매량 최소 갯수 5~30개->30개로 통일함
		//김:30, 단무지:10, 쌀:50, 햄:10, 계란:20, 면사리:20, 어묵:10, 
		//대파:10, 쑥갓:5, 유부, 10 떡 : 20, 치즈:5, 돼지고기:10, 밀가루:10, 빵가루:10, 김치:10
				


	@Override
	public void editDue(String name, LocalDate Date) {
		rRDao.selectAllIng().stream().forEach(due2->{
			
			
			LocalDate due = due2.getDue();	
			
			LocalDate supplierDate = due2.getDue();//공급처식자재 유통기한을 가져온다
			
			LocalDate today = LocalDate.now();//금일날짜 
			LocalDate expiryDate = today.plusDays(3); //금일날짜에서 3일 후 종료
				//System.out.println(expiryDate);
				
			//LocalDate searchDate = rRDao.searchByName(name).get(0).getDue();//유통기한 검색 조회할때 가져온 유통기한, if문 이름 검색할 때 나오게 하기 
			
			due2.setDue(today); //inputDate 금일 사온 날짜로 설정

					if(expiryDate==supplierDate) {//유통기한 조회 시 기한이 종료된 날짜면
						System.out.println("유통기한 지난 식자재입니다..");
						rRDao.deleteByName(name);//식자재 삭제
						System.out.println("식자재 폐기처분하였습니다.");
						int amount = due2.getAmount()-30;
						rRDao.updateAmount(name, amount);//식자재 재입고
						System.out.println(due2.getName()+"식자재"+due2.getAmount()+"개 재입고하였습니다.");
						due2.setDue(today);//금일 사온 날짜로 유통기한 업데이트
						System.out.println(due2.getName()+"남은 유통기한"+due2.getDue()+"입니다.");
					}else {
						System.out.println("식자재 "+due2.getName()+" 남은 유통기한 "+due2.getDue()+" 까지 입니다.");//종료되지 않은 날짜이면
					}
					
		

		/*기능 정의 
		* 사올 때 식자재 유통기한 설정, 유통기한 지난 후, 조회만 해서 , 유통기한 지난 재고 리스트입니다.하고 
		* boolean -> T/F , 삭제후, 다시 사올 때, now date로 3일, 예시로 해서 호출
		*/
		});
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
		sRDao.selectAllIng().stream().forEach(ing4->{
		// TODO Auto-generated method stub
		//주문에서 사용시 updateamount, 공급처에서 사올때
			System.out.println("공급처에서 사올 식자재를 입력해주세요");
			String name = sc.next();
			if(ing4 == null) {
				System.out.println("식자재 없음");
			}
			else {
				ing4.setName(name);//식자재넣기
				System.out.println("해당 식자재 필요한 수량을 입력해주세요 (식자재 1종당 최소 구매수량 30개 이상)");
				int amount = sc.nextInt();
					if(sRDao.searchByName(name).get(0).getAmount()>30) {
						ing4.setAmount(amount);
						System.out.println(ing4.getName()+ing4.getAmount()+"개 구매하였습니다.");
					}
					else {
						System.out.println("공급처 식자재 재고가 부족합니다. 구매불가.");
					}
		//식당 냉장고에 넣기 기능
		//식자재 30개 미만 입력시 구매 불가 기능 추가
		}
	});	
		
	}

	@Override
	public void outIng(Scanner sc) {
		// TODO Auto-generated method stub
		//food에서 주문할 때 식자재 소진
		System.out.println("음식 준비할 때 필요한 식자재 이름을 입력하세요");
		String name = sc.next();
		System.out.println("필요한 식자재 수량은 얼마입니까?");
		int amount = sc.nextInt();
		rRDao.updateAmount(name, amount);
		System.out.println(name+"이"+amount+"소진되었습니다.");
		
		
	}

	@Override
	public void getAllIng() {
		// TODO Auto-generated method stub
		ArrayList<Ingredient> ingr = rRDao.selectAllIng();
		if(ingr == null) {
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





