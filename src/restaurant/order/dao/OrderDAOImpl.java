package restaurant.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.DbConnect;
import restaurant.order.vo.Order;

public class OrderDAOImpl implements OrderDAO {

	
	private DbConnect db;
	private String sql="";
	
	public OrderDAOImpl() {
		db =  DbConnect.getInstance();
	}
	
	@Override
	public void insert(Order o) {
		Connection conn=db.conn();
		sql="insert into food_order values(seq_order.nextval,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, o.getFood_idx());
			pstmt.setNString(2, o.getFoodname());
			pstmt.setInt(3,o.getAmount());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(int num) {
		Connection conn=db.conn();
		sql="delete from food_order where num=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public ArrayList<Order> getAllOrder() {
		
		Connection conn=db.conn();
		ArrayList<Order> list = new ArrayList<>();
		ResultSet rs = null;
		sql="select * from food_order order by num asc";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				Order o = new Order();
				o.setNum(rs.getInt(1));
				o.setFoodname(rs.getString(3));
				o.setAmount(rs.getInt(4));
				list.add(o);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public Order selectByNum(int num) {
		Order o = new Order();
		Connection conn=db.conn();
	
		ResultSet rs = null;
		sql="select * from food_order where num=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			rs.next();
			o.setNum(rs.getInt(1));
			o.setFoodname(rs.getString(3));
			o.setAmount(rs.getInt(4));

			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return o;
	}

}
