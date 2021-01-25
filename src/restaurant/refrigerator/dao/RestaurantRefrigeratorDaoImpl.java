package restaurant.refrigerator.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import restaurant.food.vo.Ingredient;

public class RestaurantRefrigeratorDaoImpl implements Refrigerator {
	
	
	public static final String FILE_PATH = "src/restaurant/files/restaurant_ingredients.dat";
	private static final String MONEY_FILE_PATH = "src/restaurant/files/total_money.dat";	
	
	
	private ArrayList<Ingredient> ingredients; //저장소
	
	private static RestaurantRefrigeratorDaoImpl RestaurantRefrigeratordaoImpl = new RestaurantRefrigeratorDaoImpl();
	
	private RestaurantRefrigeratorDaoImpl() {
		ingredients = new ArrayList<Ingredient>(); 
		File rf = new File(FILE_PATH);
		boolean isExists = rf.exists();
		if(isExists)
			start();
	}
	
	public static RestaurantRefrigeratorDaoImpl getInstance() {
		return RestaurantRefrigeratordaoImpl;
	}
	
	
	public void start() {
		try {
			FileInputStream fi = new FileInputStream(FILE_PATH);
			ObjectInputStream oi = new ObjectInputStream(fi);
			//unchecked warning 뜸
			ingredients = (ArrayList<Ingredient>) oi.readObject();
			oi.close();
			fi.close();
		} catch (FileNotFoundException e) {
			System.out.println("restaurant.refrigerator DaoImpl start() Error: 초기화 파일을 불러오지 못했습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("restaurant.refrigerator DaoImpl start() Error: 초기화 파일을 불러오지 못했습니다.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void addIng(Ingredient ing) {
		// TODO Auto-generated method stub
		ingredients.add(ing);
//		식자재 입고(name, amount, price, due)
		stop();

	}

	@Override
	public ArrayList<Ingredient> searchByName(String name) {
		/*

		 * select * from refrigerator
		 * where name = name
		 */
		ArrayList<Ingredient> ingredients = new ArrayList<>();
//      식자재 검색 및 확인
		for (Ingredient ingredient : this.ingredients) {//안에 찾는 걸 스캔
			if (name.equals(ingredient.getName())) {
				ingredients.add(ingredient);
			}
		}
		return ingredients;
	}

	@Override
	public void updateDue(String name, LocalDate Date) {
		// 유통기한 수정
		for (Ingredient ingredient : this.ingredients) {
			if (name.equals(ingredient.getName())) {
				ingredient.setDue(Date);
				
//				1일이 지날수록 유통기한 변경은?
//				default가 2100-01-01
			}
		}
		stop();
		
	}

	@Override
	public void updateAmount(String name, int amount) {
		// TODO Auto-generated method stub
//		식자재 수량 수정, 입고+출고 (출고는 -)
		for (Ingredient ingredient : this.ingredients) {
			if (name.equals(ingredient.getName())) {
				ingredient.setAmount((ingredient.getAmount()+amount));//ame으로 찾아서 amount 수정
			} 
/*			식자재 amount 1회 구매량 갯수 
			김:30. 단무지:10, 쌀:50, 햄:10, 계란:20, 면사리:20, 어묵:10, 대파:10, 쑥갓:5, 유부:10
			떡 : 20, 치즈:5, 돼지고기:10, 밀가루:10, 빵가루:10, 김치:10
*/			
		}
		stop();
	}

	@Override
	public void deleteByName(String name) {
		// TODO Auto-generated method stub
//		유통기한만료, 재료 소진시 식자재 삭제
		for (int i =0;  i<ingredients.size(); i++) {
			if(ingredients.get(i).getName().equals(name))
				ingredients.remove(i);
			}
		stop();
		}
	

	@Override
	public ArrayList<Ingredient> selectAllIng() {
		// TODO Auto-generated method stub
//		식자재 목록 확인
		return ingredients;
	}


	public void stop() {
		try {
			FileOutputStream fo = new FileOutputStream(FILE_PATH);
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			oo.writeObject(ingredients);
			oo.close();
			fo.close();
			}
		catch (IOException e) {
			System.out.println("restaurant.refrigerator DaoImpl stop() Error: 파일을 저장하지 못했습니다.");
			e.printStackTrace();
		}
	}


	}

	
	
	
	
	

