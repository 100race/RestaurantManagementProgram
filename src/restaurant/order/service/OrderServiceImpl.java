package restaurant.order.service;


import java.util.ArrayList;

import restaurant.finance.dao.FinanceDao;
import restaurant.finance.dao.FinanceDaoImpl;
import restaurant.finance.vo.Finance;
import restaurant.food.dao.FoodDao;
import restaurant.food.dao.FoodDaoImpl;
import restaurant.food.vo.Food;
import restaurant.order.dao.OrderDAO;
import restaurant.order.dao.OrderDAOImpl;
import restaurant.order.vo.Order;

/**
 * 
 * @author SeongJin Park
 *
 */
public class OrderServiceImpl implements OrderService{
	
	
	private int foodIdx;
	private Food food;
	private OrderDAO dao; // 주문DAO
	private Order o;
	//private refrigeratorDAO // 냉장고 DAO
	private FoodDao foodDao; // 요리DAO
	private FinanceDao financeDao; // 입출금DAO
	
	
	public OrderServiceImpl() {
		
		dao=new OrderDAOImpl();
		foodDao=new FoodDaoImpl();
		financeDao=new FinanceDaoImpl();
		
	}
	
	/*
	 *@param : foodIdx 주문음식의 index
 	 * 
	 */
	@Override
	public boolean checkIngr(int foodIdx) {
		boolean flag=false; 
			if(foodDao.searchByIdx(foodIdx)!=null) {
				this.foodIdx=foodIdx;
				flag=true;
			}
		
		return flag;
	}
	
	/*
	 *@param : food 음식정보
	 *		   foodAmount 주문음식의 수량
 	 * 
	 */
	@Override
	public void addOrder(int foodAmount) {	
			
			food = foodDao.searchByIdx(foodIdx); // foodIdx로 search
			this.o = new Order(food, foodAmount); // 주문생성
			financeDao.input((food.getPrice()*foodAmount), food.getFoodName()); // 매출증가
			System.out.println("주문메뉴 : " + food.getFoodName() + "  "+foodAmount+"인분");
			System.out.println("수익 : " + food.getPrice()*foodAmount+"원");
			System.out.println("총 : " + Finance.getTOTAL_MONEY()+"원");
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
