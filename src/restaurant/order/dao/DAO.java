package dao;

import java.util.ArrayList;

import vo.Order;

public interface DAO {
	void insert(Order o);
	void delete(int num);
	ArrayList<Order> getAllOrder();
}
