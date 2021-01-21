package restaurant.refrigerator;

import java.time.LocalDate;
import java.util.Scanner;

import restaurant.food.vo.Ingredient;

public interface RefrigerratorService {

	public void buying(Ingredient ing);//공급처 재료, totalmoney차감
	public void updateDue(LocalDate Date);
	public void getByName(Scanner sc); //dao search 호출, void 반환 수정
	public void editDue(Scanner sc);//dao updateDAte 호출
	public void deleteIng(Scanner sc);
	public void inIng(Scanner sc);
	public void outIng(Scanner sc);
	public void getAllIng();
	public void PrintAllIng(); //출력 추가
}
