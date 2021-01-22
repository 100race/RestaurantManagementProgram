package restaurant.order.service;


import java.util.ArrayList;

import restaurant.food.vo.Food;
import restaurant.order.dao.OrderDAO;
import restaurant.order.dao.OrderDAOImpl;
import restaurant.order.vo.Order;




public class OrderServiceImpl implements OrderService{
	
	
	private int foodIdx;
	private Food food;
	private OrderDAO dao; // 주문DAO
	private Order o;
	//private foodDAO foodDAO; // 음식 DAO
	//private refrigeratorDAO // 냉장고 DAO
	
	
	
	public OrderServiceImpl() {
		dao=new OrderDAOImpl();
	}
	
		/*
	 *@param : foodIdx 주문음식의 index
 	 * 
	 */
	@Override
	public boolean checkIngr(int foodIdx) {
		boolean flag=true; // false로 변경
			/*if(foodDao.searchByIdx(foodIdx)!=null) {
				this.foodIdx=foodIdx;
				flag=true;
			}*/
		return flag;
	}
	
	/*
	 *@param : foodAmount 주문음식의 수량
 	 * 
	 */
	@Override
	public void addOrder(int foodAmount) {	
			//check 호출 식자재가 있는지 판단
			//있으면 추가하고 update 냉장고
			// if(food.
			// refrigeratorDAO	
			
			// Food food = foodDao.searchByIdx(foodIdx);
			this.o = new Order(food, foodAmount); // 테스트용
			
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

	@Override
	public void start() {
		// TODO Auto-generated method stub
		dao.start();
	}

	@Override
	public void orderSave() {
		// TODO Auto-generated method stub
		dao.orderSave();
	}
	

}
