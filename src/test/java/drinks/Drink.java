package drinks;

public class Drink {
	private String name;
	private int price;
	private String type;
	private int amount;
	private int popularity;
	
	public Drink(String name, Integer price, String type, int popularity) {
		this.name = name;
		this.price = price;
		this.type = type;
		this.amount = 1;
		this.popularity = popularity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Drink [name=" + name + ", price=" + price + ", type=" + type + ", amount=" + amount + ", popularity="
				+ popularity + "]";
	}


	
}
