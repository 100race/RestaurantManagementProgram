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

import board.vo.Board;
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
	private DbConnect foodDb;
	
	//싱글톤
	public FoodDaoImpl() {
		foodDb = DbConnect.getInstance();
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
				Food f = new Food(rs.getString(2), rs.getInt(3));
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
	 * @param food void : Food 객체를 추가함
	 */
	@Override
	public void insert(Food food) {
		Connection conn = foodDb.conn(); //db 연결
		String sql = "insert into foods values(?,?,?)";
		
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);//실행할 sql문으로 preparedstatement 객체 생성
			//물음표 매칭
			pstmt.setString(2, food.getFoodName());
			pstmt.setInt(3, food.getPrice());
			
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
		System.out.println("요리 추가 완료!");
	}

	/**
	 * 음식 인덱스로 음식을 찾아 반환하는 메소드
	 * 
	 * @param : 찾고자 하는 음식의 idx
	 * @return : 입력된 idx와 일치하는 음식을 반환, 찾지 못할 경우 null 반환
	 */
	@Override
	public Food searchByIdx(int idx) {
		Food f = getAllFood().get(idx-1);
		if (f == null) {
			System.out.println("요리가 존재하지 않습니다.");
			return null;
		} else {
			return f; // 인덱스에 있는 객체 리턴
		}
	}

	/**
	 * 음식 이름으로 음식을 찾아 반환하는 메소드
	 * 
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
		return b;
		
		Food f = new Food();
		f.setFoodName(name);
		// foods에 f가 존재하면 f의 인덱스를 리턴, 없으면 -1 리턴
		int index = foods.indexOf(f);
		if (index < 0) { // f가 없으면(-1이면)
			return null;
		} else {
			return foods.get(index); // 인덱스에 있는 객체 리턴
		}
	}

	/**
	 * 음식 인덱스로 음식을 찾아 삭제하는 메소드
	 * 
	 * @param : 찾고자 하는 음식의 idx void : 찾은 음식을 삭제, 없으면 sysout출력
	 */
	@Override
	public void deleteByIdx(int idx) {
		Food f = searchByIdx(idx);
		if (f != null) {
			foods.remove(f);
			System.out.println("요리 삭제 완료!");
			for(int i=idx-1; i<foods.size(); i++) { //삭제시 삭제한 리스트 이후 idx를 -1씩 감소시켜줌
				if(i == 0) {
					for(int j=0; j<foods.size(); j++) {
						foods.get(j).setIdx(j+1);
					}
					break;
				}else {
					foods.get(i).setIdx(i+1);
				}
			}
		} else {
			System.out.println("없는 번호 입니다. 다시 확인해주세요!");
		}
	}

	/**
	 * 음식 이름으로 음식을 찾아 삭제하는 메소드
	 * 
	 * @param : 찾고자 하는 음식의 name void : 찾은 음식을 삭제, 없으면 sysout출력
	 */
	@Override
	public void deleteByName(String name) {
		Food f = searchByName(name);
		if (f != null) {
			foods.remove(f);
			System.out.println("요리 삭제 완료!");
		} else {
			System.out.println("없는 번호 입니다. 다시 확인해주세요!");
		}
	}

	/**
	 * 음식 인덱스로 음식을 찾아 재료를 추가하는 메소드
	 * 
	 * @param : 찾고자 하는 음식의 idx, 추가할 ingredients void : 찾은 음식의 재료를 추가, 없으면 sysout출력
	 */
	@Override
	public void insertIng(int idx, Map<String, Integer> ingredient) {
		Food f = searchByIdx(idx);
		if (f != null) {
			f.getIngredient().putAll(ingredient);
			System.out.println("요리 재료 추가 완료!");
		} else {
			System.out.println("없는 번호 입니다. 다시 확인해주세요!");
		}
	}

	/**
	 * 음식 인덱스로 음식을 찾아 재료를 삭제하는 메소드
	 * 
	 * @param : 찾고자 하는 음식의 idx, 삭제할 ingredients void : 찾은 음식의 재료를 수정, 없으면 sysout출력
	 */
	@Override
	public void deleteIng(int idx, String ingName) {
		Food f = searchByIdx(idx);
		if (f != null && ingName != null) {
			f.getIngredient().remove(ingName);
			System.out.println("요리 재료 삭제 완료!");
		}else {
			System.out.println("재료명을 잘못 입력하였습니다.");
		}
	}

	/**
	 * 음식 인덱스로 음식을 찾아 재료의 수량을 변경하는 메소드
	 * 
	 * @param : 찾고자 하는 음식의 idx, 변경할 재료의 key, 변경할 value void : 찾은 음식의 재료의 수량을 수정, 없으면
	 *        sysout출력
	 */
	@Override
	public void changeIngCnt(int idx, String key, int value) {
		Food f = searchByIdx(idx);
		if (f != null) {
			if (value > 0) { // 수량이 양수이면
				f.getIngredient().replace(key, value);
				System.out.println("요리 재료 수량 변경 완료!");
			} else {
				System.out.println("수량이 0 이하입니다!");
			}
		} else {
			System.out.println("없는 번호 입니다. 다시 확인해주세요!");
		}
	}

	/**
	 * 음식 인덱스로 음식을 찾아 가격을 수정하는 메소드
	 * 
	 * @param : 찾고자 하는 음식의 idx, 변경할 price void : 찾은 음식의 가격을 수정, 없으면 sysout출력
	 */
	@Override
	public void updatePrice(int num, int price) {
		// 예외처리!
//		int temp = getFoods().get(num-1).getIdx();
//		if(temp == num-1) {
//			getFoods().get(num-1).setPrice(price);
//		}else {
//			System.out.println("없는 번호 입니다. 다시 확인해주세요! 다오부분!");
//		}
		if (getAllFood().get(num-1) != null) {
			getAllFood().get(num-1).setPrice(price);
		} else {
			System.out.println("없는 번호입니다. 다시 확인해주세요.");
		}

	}

	/**
	 * 음식 인덱스로 음식을 찾아 음식 이름을 수정하는 메소드
	 * 
	 * @param : 찾고자 하는 음식의 idx, 변경할 name void : 찾은 음식의 이름을 수정, 없으면 sysout출력
	 */
	@Override
	public void updateName(int idx, String name) {
		Food f = searchByIdx(idx);
		if (f != null) {
			f.setFoodName(name);
		} else {
			System.out.println("없는 번호 입니다. 다시 확인해주세요!");
		}
	}

}
