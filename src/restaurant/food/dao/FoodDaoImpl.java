package restaurant.food.dao;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import conn.DbConnect;
import restaurant.food.vo.Food;
import restaurant.food.vo.Ingredient;
import restaurant.order.vo.Order;
/**
 * 
 * @author Han
 *
 */
public class FoodDaoImpl implements FoodDao {
	private static DbConnect foodDb = DbConnect.getInstance();;
	//싱글톤
	private static FoodDao foodDaoImpl = new FoodDaoImpl();
	
	public static DbConnect getFoodDb() {
		return foodDb;
	}
	public static FoodDao getInstance() {
		return foodDaoImpl;
	}
	
	/**
	 * @return : 모든 음식을 반환
	 */
	@Override
	public ArrayList<Food> getAllFood() {
		ArrayList<Food> list = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from foods order by idx";
		//연결
		Connection conn = foodDb.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Food f = new Food(rs.getInt(1), rs.getString(2), rs.getInt(3));
				list.add(f);
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
	 * @param 추가할 food 
	 * void : Food 객체를 추가함
	 */
	@Override
	public void insert(String name, int price) {
		Connection conn = foodDb.conn(); //db 연결
		String sql = "insert into foods values(foods_seq.nextval,?,?)";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);//실행할 sql문으로 preparedstatement 객체 생성
			//물음표 매칭
			pstmt.setString(1, name);
			pstmt.setInt(2, price);
			//sql 실행 - executeUpdate(): 쓰기작업(insert, update, delete) 실행 => 적용된 줄수 반환(int)
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
	 * @param : 찾고자 하는 음식의 idx
	 * @return : 입력된 idx와 일치하는 음식을 반환, 찾지 못할 경우 null 반환
	 */
	@Override
	public Food searchByIdx(int idx) {
		ResultSet rs = null;
		String sql = "select * from foods where idx=?";
		Connection conn = foodDb.conn();
		Food f = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				f = new Food(rs.getInt(1), rs.getString(2), rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return f;
	}

	/**
	 * @param : 찾고자 하는 음식의 name
	 * @return : 입력된 name과 일치하는 음식을 반환, 찾지 못할 경우 null 반환
	 */
	@Override
	public Food searchByName(String name) {
		ResultSet rs = null;
		String sql = "select * from foods where foodname=?";
		Connection conn = foodDb.conn();
		Food f = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				f = new Food(rs.getInt(1), rs.getString(2), rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return f;
	}

	/**
	 * @param : 삭제하려는 음식의 idx 
	 * void : 찾은 음식을 삭제
	 */
	@Override
	public void deleteByIdx(int idx) {
		Connection conn = foodDb.conn(); // db 연결
		String sql = "delete from foods where idx = ?";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
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

	/**
	 * @param : 변경할 food
	 * void : 요리의 가격을 수정
	 */
	@Override
	public void changePrice(Food food) {
		Connection conn = foodDb.conn(); // db 연결
		String sql = "update foods set price=? where idx=?";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, food.getPrice());
			pstmt.setInt(2, food.getIdx());
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
	 *  @param : 변경할 food
	 *  void : 요리의 이름을 수정
	 */
	@Override
	public void changeName(Food food) {
		Connection conn = foodDb.conn(); // db 연결
		String sql = "update foods set foodname=? where idx=?";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, food.getFoodName());
			pstmt.setInt(2, food.getIdx());
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

}
