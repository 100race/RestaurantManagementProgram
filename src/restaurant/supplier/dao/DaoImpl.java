package restaurant.supplier.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import restaurant.food.vo.Ingredient;
import restaurant.refrigerator.dao.Refrigerator;
import restaurant.supplier.SupplyService;
import restaurant.supplier.SupplyServiceImpl;

public class DaoImpl implements Refrigerator{
	
	public static final String FILE_PATH = "src/restaurant/files/ingredients.dat";
	private ArrayList<Ingredient> ingredients;
	
	
	public DaoImpl() {
		ingredients = new ArrayList<Ingredient>(); 
		//init();
		start();
	}
	
	//실행 시 파일에서 리스트 받아옴
	public void start() {
		try {
			FileInputStream fi = new FileInputStream(FILE_PATH);
			ObjectInputStream oi = new ObjectInputStream(fi);
			//unchecked warning 뜸
			ingredients = (ArrayList<Ingredient>) oi.readObject();
			oi.close();
			fi.close();
		} catch (FileNotFoundException e) {
			System.out.println("Dao start() Error: 초기화 파일을 불러오지 못했습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Dao start() Error: 초기화 파일을 불러오지 못했습니다.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void addIng(Ingredient ing) {
		ingredients.add(ing);
	}

	@Override
	public ArrayList<Ingredient> searchByName(String name) {
		ArrayList<Ingredient> sl = new ArrayList<>();
		for(int i = 0 ; i< ingredients.size(); i++) {
			if(ingredients.get(i).getName().equals(name))
				sl.add(ingredients.get(i));
		}
		return sl;
	}

	@Override
	public void updateDue(String name, LocalDate date) {
		//이름이 같은건 전부 바꾸기
		for(int i = 0; i < ingredients.size(); i++) {
			if(ingredients.get(i).getName().equals(name)){
				ingredients.get(i).setDue(date);
			}
		}
	}

	
	@Override
	public void updateAmount(String name, int newAmount) {
		//이름이 같은건 전부 바꾸기
		int oriAmount = 0;
		for(int i = 0; i < ingredients.size(); i++) {
			if(ingredients.get(i).getName().equals(name)){
				oriAmount = ingredients.get(i).getAmount();
				ingredients.get(i).setAmount(oriAmount+newAmount);
			}
		}
	}

	@Override
	public void deleteByName(String name) {
		for(int i = 0 ; i< ingredients.size(); i++) {
			if(ingredients.get(i).getName().equals(name))
				ingredients.remove(i);
		}
	}
	


	@Override
	public ArrayList<Ingredient> selectAllIng() {
		return ingredients;
	}
	
	
	//첫실행시 한번만 초기화 (이후 주석처리)- Ingredient 직렬화 적용
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
//			System.out.println("DaoImpl init() Error: 초기화 파일을 불러오지 못했습니다.");
//			e.printStackTrace();
//		}
//		
//	}
	
	//끝날시 실행, 파일에 데이터 저장
	public void stop() {
		try {
			FileOutputStream fo = new FileOutputStream(FILE_PATH);
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			oo.writeObject(ingredients);
			oo.close();
			fo.close();
			}
		catch (IOException e) {
			System.out.println("DaoImpl stop() Error: 파일을 저장하지 못했습니다.");
			e.printStackTrace();
		}
	}

	
}
