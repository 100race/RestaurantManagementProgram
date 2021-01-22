package dao;

import java.util.ArrayList;

import vo.Order;

public interface OrderDAO {
	void insert(Order o);
	void delete(int num);
	ArrayList<Order> getAllOrder();
	void orderSave();
	void start();
}
