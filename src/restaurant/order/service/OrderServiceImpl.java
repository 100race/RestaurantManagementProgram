package restaurant.order.service;

import java.util.ArrayList;


import restaurant.finance.dao.FinanceDaoImpl;
import restaurant.food.dao.FoodDao;
import restaurant.food.dao.FoodDaoImpl;
import restaurant.food.dao.FoodIngDao;
import restaurant.food.dao.FoodIngDaoImpl;
import restaurant.food.vo.Food;
import restaurant.food.vo.FoodIngredient;
import restaurant.food.vo.Ingredient;
import restaurant.order.dao.OrderDAO;
import restaurant.order.dao.OrderDAOImpl;
import restaurant.order.vo.Order;
import restaurant.refrigerator.dao.RefrigeratorDao;
import restaurant.refrigerator.dao.RestaurantRefrigeratorDaoImpl;

public class OrderServiceImpl implements OrderService{

	private OrderDAO dao; // 주문DAO
	private Order o;
	private RefrigeratorDao RfDao; // 냉장고 DAO
	private FoodDao foodDao; // 요리DAO
	private FoodIngDao foodIngDao; // 재료 DAO
	private FinanceDaoImpl financeDao; // 입출금DAO
	
	public OrderServiceImpl() {
		
		o = new Order();
		dao = new OrderDAOImpl();
		foodDao = new FoodDaoImpl();
		foodIngDao = new FoodIngDaoImpl();
		financeDao = restaurant.finance.dao.FinanceDaoImpl.getInstance();
		RfDao = restaurant.refrigerator.dao.RestaurantRefrigeratorDaoImpl.getInstance(); 
	}
	@Override
	public boolean checkIngr(int foodIdx,int orderAmount) {
		boolean flag = false;
		boolean flag2 =false;
		/*System.out.println("검색한 요리의 idx : "+ foodDao.searchByIdx(foodIdx).getIdx());
			System.out.println(foodIngDao.getFoodIngByIdx(foodIdx).toString());
		*/	for( FoodIngredient f : foodIngDao.getFoodIngByIdx(foodIdx)) {
				int totalAmount = f.getAmount()*orderAmount;
				/*System.out.println("필요수량 : " + f.getName() +"  "+ totalAmount + "개");
				System.out.println("size: " + RfDao.searchByName(f.getName()).size());*/
				if(RfDao.searchByName(f.getName()).size()!=0) {
					for(Ingredient ing : RfDao.searchByName(f.getName())) {
						/*System.out.println("냉장고 안 : " + ing.toString());*/
							/*System.out.println(ing.getName() + "  " + ing.getAmount() +"비교 " + totalAmount);*/
							int empty = ing.getAmount();
							if(empty<totalAmount  || empty-totalAmount<0) {
								//냉장고에 있는재료 < 주문수량 = 주문불가 or 냉장고에있는 재료 - 주문수량 < 0 = 주문불가
						/*		System.out.println("주문불가");*/
								flag=false;
								flag2=true;
								break;
							} else {
								//주문 가능
								/*System.out.println("주문가능");*/
								flag=true;
								continue;
							} 		
					}
				} else {
					break;
				} 
				
				if(flag2) {
					break;
				}
			}
			if(flag) {
				//foodname 저장 후 추가, 재료수정 , 입금
				String foodName=foodDao.searchByIdx(foodIdx).getFoodName();
				o.setFoodname(foodName);
				o.setFood_idx(foodDao.searchByName(foodName).getIdx());
				for( FoodIngredient f : foodIngDao.getFoodIngByIdx(foodIdx)){
					for(Ingredient ing : RfDao.searchByName(f.getName())){
						int totalAmount = f.getAmount()*orderAmount;
//						System.out.println("차감 재료 : " + ing.getName() +" -"+totalAmount+"개");
						RfDao.updateAmount(ing.getName(), -totalAmount);
						}	
				}
				/*System.out.println("입금");*/
				financeDao.input(foodDao.searchByIdx(foodIdx).getPrice()*orderAmount, o.getFoodname()+" "+orderAmount+"인분");
				
			}
			return flag;
		
	}

	@Override
	public void addOrder(int foodAmount) {
		o.setAmount(foodAmount);
		dao.insert(o);
		
	}

	@Override
	public ArrayList<Order> getAllOrder() {
	
		return dao.getAllOrder();
	}

	@Override
	public void printAllOrder() {
		for (Order o : dao.getAllOrder()) {
			System.out.println(o);
		}
				
	}

	@Override
	public void finishOrder(int num) {
	
		dao.delete(num);
	}
	@Override
	public void cancelOrder(int num) {
		
		o = dao.selectByNum(num);
		/*System.out.println(o.toString());*/
		int foodIdx = foodDao.searchByName(o.getFoodname()).getIdx();
		/*System.out.println("메뉴 번호 : "  + foodIdx);*/
		
		/*for( FoodIngredient f : foodIngDao.getFoodIngByIdx(foodIdx)){
			for(Ingredient ing : RfDao.searchByName(f.getName())){
				int totalAmount = f.getAmount()*o.getAmount();
				RfDao.updateAmount(ing.getName(), totalAmount);
				}	
		}*/
		/*System.out.println("환불");*/
		financeDao.input(-foodDao.searchByIdx(foodIdx).getPrice()*o.getAmount(), o.getFoodname()+" "+o.getAmount()+"인분 주문 취소");
		
		dao.delete(num);
		
	}

}
