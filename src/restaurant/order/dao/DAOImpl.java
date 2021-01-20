package dao;

import java.util.ArrayList; 

import vo.Order;

public class DAOImpl implements DAO{
	
	ArrayList<Order> ord;
	
	public DAOImpl() {
		ord=new ArrayList<>();
	}
	
	public void insert(Order orders) {
		
		ord.add(orders);
	}
	
	public void delete(int num) {
		
		for(Order o : ord) {
			if(o.getNum()==num) {
			ord.remove(o);
				System.out.println("주문취소 완료");
			} else {
				System.out.println("주문번호 확인요망");
			}
		}
	}

	@Override
	public ArrayList<Order> getAllOrder() {
		
		return ord;
	}
	
	
	
}
