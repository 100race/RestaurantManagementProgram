package service;

import java.util.ArrayList;

import dao.DAO;
import dao.DAOImpl;
import vo.Food;
import vo.Order;



public class ServiceImpl implements Service{
	
	private int foodIdx;
	private Food food;
	private DAO dao; // 주문DAO
	//private foodDAO foodDAO; // 음식 DAO
	//private refrigeratorDAO //
	public ServiceImpl() {
		dao=new DAOImpl();
	}
	
	@Override
	public boolean checkIngr(int foodIdx) {
		boolean flag=true; // false로 변경
			/*if(foodDao.searchByIdx(foodIdx)!=null) {
				this.foodIdx=foodIdx;
				flag=true;
			}*/
		return flag;
	}

	@Override
	public void addOrder(int foodAmount) {
		
	
			//Order o = new Order(foodDao.searchByIdx(foodIdx),foodAmount);	
			Order o = new Order(food, foodAmount); // 테스트용
			
			dao.insert(o);
			
	}
		
		
	
	@Override
	public ArrayList<Order> getAllOrder() {
		
		return dao.getAllOrder();
	}

	@Override
	public void finishOrder(int num) {
		
		dao.delete(num);
		
	}

	@Override
	public void printAllOrder() {
		
		for(Order o : getAllOrder()) {
			System.out.println(o);
		}
		
	}

}
