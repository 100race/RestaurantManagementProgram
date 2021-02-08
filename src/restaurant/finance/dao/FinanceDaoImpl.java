package restaurant.finance.dao;

import java.awt.BufferCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conn.DbConnect;
import restaurant.finance.vo.Finance;
import restaurant.food.vo.Ingredient;

/**
 * 
 * @author kyoungju
 * 싱글톤으로 생성하여 가게의 총금액(total_money)과
 * 금전출입을 기록하는 클래스
 * 파일 : total_money, finance_records
 * 사용시점 : 주문시 , 식자재 구매시
 *
 */
public class FinanceDaoImpl implements FinanceDao {

	private static FinanceDaoImpl daoImpl = new FinanceDaoImpl();
	private DbConnect db;

	private FinanceDaoImpl() {
		db = DbConnect.getInstance();
		initTotalMoney();
	}
	
	public static FinanceDaoImpl getInstance() {
		return daoImpl;
	}

	/**
	 * 금액과 메세지를 받아서 입금한 후 메세지 기록
	 */
	@Override
	public void input(int amount, String message) {
		int oriAmount = Finance.getTOTAL_MONEY();
		Finance.setTOTAL_MONEY(oriAmount+amount);	
		
		Connection conn = db.conn();
		String sql = "insert into finance values(seq_finance.nextval,?,?,?,sysdate)";
		int cnt = 0;
		PreparedStatement pstmt;
		try {
		    pstmt = conn.prepareStatement(sql); //자바에서 sql문장을 실행
			pstmt.setInt(1, Finance.getTOTAL_MONEY()); 
			pstmt.setInt(2, amount);
			pstmt.setString(3, message);
			cnt = pstmt.executeUpdate();
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
	 * 금액과 메세지를 받아서 출금한 후 메세지 기록
	 */
	@Override
	public void output(int amount, String message) {
		int oriAmount = Finance.getTOTAL_MONEY();
		Finance.setTOTAL_MONEY(oriAmount-amount);
		
		Connection conn = db.conn();
		String sql = "insert into finance values(seq_finance.nextval,?,?,?,sysdate)";
		int cnt = 0;
		PreparedStatement pstmt;
		try {
		    pstmt = conn.prepareStatement(sql); //자바에서 sql문장을 실행
			pstmt.setInt(1, Finance.getTOTAL_MONEY()); 
			pstmt.setInt(2, amount);
			pstmt.setString(3, message);
			cnt = pstmt.executeUpdate();
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

	@Override
	public ArrayList<Finance> getAllfinanceRecords() {
		ArrayList<Finance> list = new ArrayList<>();
		Connection conn = db.conn();
		String sql = "select * from finance order by idx asc";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new Finance(rs.getDate(5).toLocalDate(),rs.getInt(3),rs.getString(4)));
				//순서맞는지 확인
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
	 * 파일 실행시 이전 입출금기록 불러오기
	 */
	@Override
	public void start() {
		}
	
	
	/**
	 * 프로그램 처음 초기화시 초기금액설정 ( 이전금액 불러오기  )
	 */
	public void initTotalMoney() {

		Connection conn = db.conn();
		String sql = "select total_money from (select * from finance order by day desc) where rownum = 1";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Finance.setTOTAL_MONEY(rs.getInt(1));
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
			
		}
	
}


