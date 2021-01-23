package restaurant.finance.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

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
public class DaoImpl implements Dao {

	private static final String RECORD_FILE_PATH = "src/restaurant/files/finance_records.dat";
	private static final String MONEY_FILE_PATH = "src/restaurant/files/total_money.dat";
	private ArrayList<Finance> financeRecords;
	private static DaoImpl daoImpl = new DaoImpl();

	private DaoImpl() {
		financeRecords = new ArrayList<Finance>(); 
		start();
		initTotalMoney();
		
	}
	
	public static DaoImpl getInstance() {
		return daoImpl;
	}

	@Override
	public void input(int amount, String message) {
		int oriAmount = Finance.getTOTAL_MONEY();
		Finance.setTOTAL_MONEY(oriAmount+amount);
		financeRecords.add(new Finance(amount, message));
	}

	@Override
	public void output(int amount, String message) {
		int oriAmount = Finance.getTOTAL_MONEY();
		Finance.setTOTAL_MONEY(oriAmount-amount);
		financeRecords.add(new Finance(-amount, message));
	}

	@Override
	public ArrayList<Finance> getAllfinanceRecords() {
		return financeRecords;
	}

	/**
	 * 파일 실행시 이전 재정기록 불러오기
	 */
	@Override
	public void start() {
		File rf = new File(RECORD_FILE_PATH);
		boolean isExists = rf.exists();
		if(rf.exists()) {
		try {
			FileInputStream fi = new FileInputStream(RECORD_FILE_PATH);
			ObjectInputStream oi = new ObjectInputStream(fi);
			//unchecked warning 뜸
			financeRecords = (ArrayList<Finance>) oi.readObject();
			oi.close();
			fi.close();
		} catch (FileNotFoundException e) {
			System.out.println("restaurant.finance.DaoImpl start() Error: 초기화 파일을 불러오지 못했습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("restaurnat.finance.DaoImpl start() Error: 초기화 파일을 불러오지 못했습니다.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	
	
	/**
	 * 파일 종료시 파일에 재정기록 저장
	 */
	@Override
	public void stop() {
		try {
			FileOutputStream fo = new FileOutputStream(RECORD_FILE_PATH);
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			oo.writeObject(financeRecords);
			
			fo = new FileOutputStream(MONEY_FILE_PATH);
			fo.write(Finance.getTOTAL_MONEY());
			
			oo.close();
			fo.close();
			}
		catch (IOException e) {
			System.out.println("restaurant.finance DaoImpl stop() Error: 파일을 저장하지 못했습니다.");
			e.printStackTrace();
		}
	}
	
	/**
	 * 프로그램 처음 초기화시 초기금액설정 ( 이전금액 불러오기 / 파일 없을 시 100만원으로 초기화 )
	 */
	public void initTotalMoney() {
		int total =0;
		File rf = new File(RECORD_FILE_PATH);
		boolean isExists = rf.exists();
		if(!isExists){
			Finance.setTOTAL_MONEY(1000000);
		}else{
			try {
				FileInputStream fi = new FileInputStream(MONEY_FILE_PATH);
				total = fi.read();
				fi.close();
			} catch (FileNotFoundException e) {
				System.out.println("restaurant.finance.DaoImpl initTotalMoney() Error: 초기화 파일을 불러오지 못했습니다.");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("restaurant.finance.DaoImpl initTotalMoney() Error: 초기화 파일을 불러오지 못했습니다.");
				e.printStackTrace();
			}
			Finance.setTOTAL_MONEY(total);
		}
			
		}
	
}


