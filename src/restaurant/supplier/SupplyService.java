package restaurant.supplier;

import java.util.ArrayList;
import java.util.Scanner;

import restaurant.food.vo.Ingredient;

public interface SupplyService {
	public void getByName(Scanner sc);
	public Ingredient getByName(String name); //한응 추가
	public void refundIng(Scanner sc);
	public void buyIng(Scanner sc);
	public void getAllIng();
	
}
