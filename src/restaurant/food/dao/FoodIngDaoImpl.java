package restaurant.food.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import conn.DbConnect;
import restaurant.food.vo.Food;
import restaurant.food.vo.FoodIngredient;

public class FoodIngDaoImpl implements FoodIngDao {
	private static DbConnect foodIngDb = DbConnect.getInstance();;
	//싱글톤
	private static FoodIngDao foodIngDaoImpl = new FoodIngDaoImpl();
	
	public static DbConnect getFoodDb() {
		return foodIngDb;
	}
	public static FoodIngDao getInstance() {
		return foodIngDaoImpl;
	}
	
	/**
	 * @param : 음식을 찾을 idx, 추가할 name, amount 
	 * void : 찾은 음식의 재료를 추가
	 */
	@Override
	public void insertIng(int idx, String name, int amount) {
		Connection conn = foodIngDb.conn(); // db 연결
		String sql = "insert into food_ingredients values(?,?,?)";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, name);
			pstmt.setInt(3, amount);
			cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄 insert 됨");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param : 찾을 재료 이름
	 * @return : 재료를 찾아 반환
	 */
	@Override
	public FoodIngredient getIngByName(String name) {
		FoodIngredient i = new FoodIngredient();
		ResultSet rs = null;
		String sql = "select * from food_ingredients where name=?";
		//연결
		Connection conn = foodIngDb.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				i = new FoodIngredient(rs.getInt(1), rs.getString(2), rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	
	/**
	 * @param : 찾을 재료 idx
	 * @return : 재료를 찾아 반환
	 */
	@Override
	public FoodIngredient getIngByIdx(int idx) {
		FoodIngredient i = new FoodIngredient();
		ResultSet rs = null;
		String sql = "select * from food_ingredients where idx=?";
		//연결
		Connection conn = foodIngDb.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				i = new FoodIngredient(rs.getInt(1), rs.getString(2), rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	

	/**
	 * @return : 요리의 재료들 전부 반환
	 */
	@Override
	public ArrayList<FoodIngredient> getAllIng() {
		ArrayList<FoodIngredient> list = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from food_ingredients order by idx";
		//연결
		Connection conn = foodIngDb.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FoodIngredient i = new FoodIngredient(rs.getInt(1), rs.getString(2), rs.getInt(3));
				list.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	
	/**
	 * @param : 음식을 찾을 idx, 변경할 amount 
	 * void : 찾은 음식의 재료의 수량을 수정
	 */
	@Override
	public void changeIngAmount(int idx, String name, int amount) {
		Connection conn = foodIngDb.conn(); // db 연결
		String sql = "update food_ingredients set amount=? where idx=? and name=?";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, idx);
			pstmt.setString(3, name);
			cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄 update 됨");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @param : 음식을 찾을 idx, 삭제할 ingName
	 * void : 찾은 음식의 재료를 삭제
	 */
	@Override
	public void deleteIng(int idx, String ingName) {
		Connection conn = foodIngDb.conn(); // db 연결
		String sql = "delete food_ingredients where idx=? and name=?";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, ingName);
			cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄 delete 됨");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
