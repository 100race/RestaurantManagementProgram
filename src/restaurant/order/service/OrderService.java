package restaurant.order.service;

import java.util.ArrayList;

import restaurant.order.vo.Order;

public interface OrderService {
	boolean checkIngr(int foodIdx , int orderAmount);
	void addOrder(int foodAmount);
	ArrayList<Order> getAllOrder();
	void printAllOrder();
	void finishOrder(int num);
	void cancelOrder(int num);
	
}
