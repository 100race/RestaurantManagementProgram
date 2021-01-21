package restaurant.refrigerator;

import java.time.LocalDate;
import java.util.Scanner;

import restaurant.food.vo.Ingredient;
import restaurant.refrigerator.dao.RestaurantRefrigeratorDaoImpl;


public class RefrigerratorServiceImpl implements RefrigerratorService {

		private RestaurantRefrigeratorDaoImpl refger;
		
		public RefrigerratorServiceImpl() {
			refger = new RestaurantRefrigeratorDaoImpl();
		}
		
	
	
	@Override
	public void buying(Ingredient ing) {
		// TODO Auto-generated method stub
//		공급처 재료, totalMoney 차감
//		2개를 같이 써야 하는데 service 한군데서 공급처, 냉장고 2곳에서 쓸수 있도록 설계도 개선 제안
//		refrigeratorService 1)supplier 2)refrigerator
		
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
		
		
		
		
	}

	@Override
	public void editDue(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteIng(Scanner sc) {
		// TODO Auto-generated method stub
		
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
		
	}

	@Override
	public void PrintAllIng() {
		// TODO Auto-generated method stub
		
	}

}
