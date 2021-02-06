package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

<<<<<<< HEAD
public class DbConnect {

	private static DbConnect db = new DbConnect();
	
	private String driver = "oracle.jdbc.driver.OracleDriver";//오라클 용 드라이버 명
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";//로그인에 필요한 서버 주소 및 sid

	private DbConnect() {
	}

	public static DbConnect getInstance() {
		return db;
	}

	public Connection conn() {
		Connection conn = null;
		try {
			Class.forName(driver);//드라이버 로드
			conn = DriverManager.getConnection(url, "hr", "hr");//db서버에 접속
=======
public class DbConnect { //싱글톤
	
	private static DbConnect db = new DbConnect();
	
	private String driver = "oracle.jdbc.driver.OracleDriver"; //오라클용 드라이버 명
	private String url = "jdbc:oracle:thin:@localhost:1521:testdb";//로그인에 필요한 서버주소 및 sid
	
	private DbConnect() {
	}
	
	public static DbConnect getInstance() {
		return db;
	}
	
	public Connection conn() {
		Connection conn = null;
		try {
			Class.forName(driver); //드라이버 로드
			conn = DriverManager.getConnection(url,"hr","hr"); //db서버에 접속
>>>>>>> fe3f9b97360124666329b47885f372c8435e6ef0
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
<<<<<<< HEAD
	}
=======
		
	}
	
>>>>>>> fe3f9b97360124666329b47885f372c8435e6ef0
}
