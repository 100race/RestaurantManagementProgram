package restaurant.supplier.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import conn.DbConnect;
import restaurant.food.vo.Ingredient;
import restaurant.refrigerator.dao.RefrigeratorDao;
/**
 * 
 * @author kyoungju
 *
 */
public class SupplyDaoImpl implements RefrigeratorDao{
	
	//public static final String FILE_PATH = "src/restaurant/files/supply_ingredients.dat";
	//private ArrayList<Ingredient> ingredients;
	private DbConnect db;
	private static SupplyDaoImpl daoImpl = new SupplyDaoImpl();
	
	/**
	 * Dao 생성자
	 * 파일에 객체정보를 넣어주기 위해 init()을 호출
	 * 생성시 파일에서 리스트를 받아옴
	 */
	private SupplyDaoImpl() {
		//ingredients = new ArrayList<Ingredient>(); 
		//File rf = new File(FILE_PATH);
		//boolean isExists = rf.exists();
		//if(!isExists)
			//init();
		//start();
		db = DbConnect.getInstance();
		
	}
	
	public static SupplyDaoImpl getInstance() {
		return daoImpl;
	}
	
	/**
	 * 
	 * @return 멤버변수로 저장된 ingredients 반환
	 */


	public ArrayList<Ingredient> getIngredients() { 
		ArrayList<Ingredient> list = new ArrayList<>();
		Connection conn = db.conn();
		String sql = "select * from supply_ingredients";
		int cnt = 0;
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Ingredient i= new Ingredient(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getDate(4).toLocalDate());
				list.add(i);
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
		
		return list;
	}
	


	/**
	 * 실행 시 파일에서 식자재 리스트 받아와서 ingredients에 초기화
	 */
//	public void start() {
//		try {
//			FileInputStream fi = new FileInputStream(FILE_PATH);
//			ObjectInputStream oi = new ObjectInputStream(fi);
//			//unchecked warning 뜸
//			ingredients = (ArrayList<Ingredient>) oi.readObject();
//			oi.close();
//			fi.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("restaurant.supplier DaoImpl start() Error: 초기화 파일을 불러오지 못했습니다.");
//			e.printStackTrace();
//		} catch (IOException e) {
//			System.out.println("restaurant.supplier DaoImpl start() Error: 초기화 파일을 불러오지 못했습니다.");
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * ingredients에 ingredient 객체 하나 추가
	 */
	@Override
	public void addIng(Ingredient ing) {
		Connection conn = db.conn();
		String sql = "insert into supply_ingredients values(seq_sup.nextval,?,?,?,?)";
		int cnt = 0;
		PreparedStatement pstmt;
		try {
		     pstmt = conn.prepareStatement(sql); //자바에서 sql문장을 실행
			//?에 들어갈 것을 넣어줌
			pstmt.setString(1, ing.getName()); 
			pstmt.setInt(2, ing.getAmount());
			pstmt.setInt(3, ing.getPrice());
			pstmt.setDate(4, Date.valueOf(ing.getDue()));
			//SQL 실행
			cnt = pstmt.executeUpdate();//executeUpdate() :insert,update,delete 쓰기작업, 적용된 줄 수 반환 int
			//executeQuery(): select 읽기작업 실행
			System.out.println("DB :"+cnt+"개 삽입 되었습니다.");
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
	 * @return 이름으로 찾은 결과 리스트를 반환
	 */
	@Override
	public ArrayList<Ingredient> searchByName(String name) {
		ArrayList<Ingredient> list = new ArrayList<>();
		Connection conn = db.conn();
		String sql = "select * from supply_ingredients where name = ?";
		int cnt = 0;
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,name);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Ingredient i= new Ingredient(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getDate(4).toLocalDate());
				list.add(i);
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
		
		return list;
	}
	

	/**
	 * 식자재의 날짜를 바꿔주는 메서드
	 * 이름이 같은 객체는 전부 바꾼다
	 */
	@Override
	public void updateDue(String name, LocalDate date) {
		Connection conn = db.conn();
		String sql = "update supply_ingredients set date = ? where name = ?";
		int cnt = 0;
		PreparedStatement pstmt;
		try {
		     pstmt = conn.prepareStatement(sql); //자바에서 sql문장을 실행
			//?에 들어갈 것을 넣어줌 
			pstmt.setDate(1, Date.valueOf(date));
			pstmt.setString(2, name);
			//SQL 실행
			cnt = pstmt.executeUpdate();//executeUpdate() :insert,update,delete 쓰기작업, 적용된 줄 수 반환 int
			//executeQuery(): select 읽기작업 실행
			System.out.println("DB :"+cnt+"개 수정 되었습니다.");
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
	 * 식자재의 재고량을 바꿔주는 메서드
	 * 이름이 같은 객체는 전부 바꾼다
	 */
	@Override
	public void updateAmount(String name, int newAmount) {
		Connection conn = db.conn();
		String sql1 = "select amount from supply_ingredients where name = ?";
		String sql2 = "update supply_ingredients set amount = ? where name = ?";
		int cnt = 0;
		int oldAmount = 0;
		PreparedStatement pstmt;
		ResultSet rs = null; //검색 결과 담을 변수

		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1,name);
			rs = pstmt.executeQuery(); //select라서 query로 실행됨. 1줄 검색되거나 없거나.
			if(rs.next()) {
				 oldAmount = rs.getInt(1);
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}	
		
		try {
		     pstmt = conn.prepareStatement(sql2); //자바에서 sql문장을 실행
			//?에 들어갈 것을 넣어줌 
			pstmt.setInt(1, oldAmount + newAmount);
			pstmt.setString(2, name);
			//SQL 실행
			cnt = pstmt.executeUpdate();
			System.out.println("DB :"+cnt+"개 수정 되었습니다.");
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
	 * 식자재의 이름을 받아 삭제하는 메서드
	 */
	@Override
	public void deleteByName(String name) {
		Connection conn = db.conn();
		String sql = "delete supply_ingredients where name = ?";
		int cnt = 0;
		PreparedStatement pstmt;
		try {
		     pstmt = conn.prepareStatement(sql); //자바에서 sql문장을 실행
			//?에 들어갈 것을 넣어줌 
			pstmt.setString(1, name);
			//SQL 실행
			cnt = pstmt.executeUpdate();
			System.out.println("DB :"+cnt+"개 수정 되었습니다.");
			
			
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
	 * @return 식자재 목록을 반환
	 */
	@Override
	public ArrayList<Ingredient> selectAllIng() {
			ArrayList<Ingredient> list = new ArrayList<>();
			Connection conn = db.conn();
			String sql = "select * from supply_ingredients";
			int cnt = 0;
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					Ingredient i= new Ingredient(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getDate(4).toLocalDate());
					list.add(i);
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
			
			return list;	
	}
	
	/**
	 * 첫 실행시만 파일에 초기화. 이후 주석처리
	 */

//	public void init() { 
//		
//		ArrayList<Ingredient> il = new ArrayList<Ingredient>();
//		il.add(new Ingredient("김", 9999999, 100, LocalDate.of(2100, 1, 1)));
//		il.add(new Ingredient("단무지", 9999999, 50, LocalDate.of(2100, 1, 1)));
//		il.add(new Ingredient("쌀", 9999999, 200, LocalDate.of(2100, 1, 1)));
//		il.add(new Ingredient("햄", 9999999, 500, LocalDate.of(2100, 1, 1)));
//		il.add(new Ingredient("계란", 9999999, 200, LocalDate.of(2100, 1, 1)));
//		il.add(new Ingredient("면사리", 9999999, 200, LocalDate.of(2100, 1, 1)));
//		il.add(new Ingredient("어묵", 9999999, 250, LocalDate.of(2100, 1, 1)));
//		il.add(new Ingredient("대파", 9999999, 50, LocalDate.of(2100, 1, 1)));
//		il.add(new Ingredient("쑥갓", 9999999, 80, LocalDate.of(2100, 1, 1)));
//		il.add(new Ingredient("유부", 9999999, 100, LocalDate.of(2100, 1, 1)));
//		il.add(new Ingredient("떡", 9999999, 300, LocalDate.of(2100, 1, 1)));
//		il.add(new Ingredient("치즈", 9999999, 400, LocalDate.of(2100, 1, 1)));
//		il.add(new Ingredient("돼지고기", 9999999, 800, LocalDate.of(2100, 1, 1)));
//		il.add(new Ingredient("밀가루", 9999999, 150, LocalDate.of(2100, 1, 1)));
//		il.add(new Ingredient("빵가루", 9999999, 150, LocalDate.of(2100, 1, 1)));
//		il.add(new Ingredient("김치", 9999999, 70, LocalDate.of(2100, 1, 1)));
//		
//		try {
//			FileOutputStream fo = new FileOutputStream(FILE_PATH);
//			ObjectOutputStream oo = new ObjectOutputStream(fo);	
//			oo.writeObject(il);
//			oo.close();
//			fo.close();
//			}
//		catch (IOException e) {
//			System.out.println("restaurant.supplier DaoImpl init() Error: 초기화 파일을 불러오지 못했습니다.");
//			e.printStackTrace();
//		}
//		
//	}
//	
//	/**
//	 * 호출시 파일에 식사재 리스트 저장
//	 */
//	public void save() {
//		try {	
//			FileOutputStream fo = new FileOutputStream(FILE_PATH);
//			ObjectOutputStream oo = new ObjectOutputStream(fo);
//			oo.writeObject(ingredients);
//			oo.close();
//			fo.close();
//			}
//		catch (IOException e) {
//			System.out.println("restaurant.supplier DaoImpl stop() Error: 파일을 저장하지 못했습니다.");
//			e.printStackTrace();
//		}
//	}

	@Override
	public void deleteByIdx(int idx) {
		// TODO Auto-generated method stub
		
	}




}
