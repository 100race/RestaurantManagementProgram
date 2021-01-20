package restaurant.food.vo;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
//날짜 정보 사용
/*
* LocalDate 클래스는 public 생성자를 제공하지 않기 때문에 객체를 생성할 때는 now()나, 
* of(), parse()와 같은 정적 메소드를 사용하도록 되어 있습니다. 
* 기본 포멧인 yyyy-MM-dd 형태의 문자열을 parse() 메소드에 넘길 수 있습니다.
*/

public class Ingredient implements Serializable{
    private String name;
    private int amount;
    private int price;
    private LocalDate due;
   
	public Ingredient(String name, int amount, int price, LocalDate due) {
		this.name = name;
		this.amount = amount;
		this.price = price;
		this.due = due;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public LocalDate getDue() {
		return due;
	}
	public void setDue(LocalDate due) {
		this.due = due;
	}
	@Override
	public String toString() {
		return "Ingredient [name=" + name + ", amount=" + amount + ", price=" + price + ", due=" + due + "]";
	}
	
}