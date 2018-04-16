package test.set_stockingapp;

public class StockItem implements Comparable<StockItem> {
	private final String name;
	private double price;	
	private int quantityInStock;
	private int reserved = 0;
	
	protected StockItem(String name, double price) {
		this.name = name;
		this.price = price;
		this.quantityInStock = 0;
	}

	protected StockItem(String name, double price, int quantityStock) {
		this.name = name;
		this.price = price;
		this.quantityInStock = quantityStock;
	}

	protected String getName() {
		return name;
	}

	protected double getPrice() {
		return price;
	}

	protected int availableQuantityInStock() {
		return quantityInStock - reserved;
	}

	protected void setPrice(double price) {
		if (price > 0) {
			this.price = price;
		}		
	}

	protected void adjustStock(int quantityStock) {
		int newQuantity = this.quantityInStock + quantityStock;
		if (newQuantity >= 0) {
			this.quantityInStock = newQuantity;
		}		
	}
	
	protected int reserveStock(int quantity) {
		if (quantity < this.availableQuantityInStock()) {
			reserved += quantity;
			return quantity;
		}
		return 0;
	}
	
	protected int unReserveStock(int quantity) {
		if (quantity < this.reserved) {
			reserved -= quantity;
			return quantity;
		}
		return 0;
	}
	
	protected int finaliseStock(int quantity) {
		if(quantity < reserved) {
			quantityInStock -= quantity;
			reserved -= quantity;
			return quantity;					
		}
		return 0;
	}

	@Override
	public int hashCode() {
		return this.name.hashCode() + 57;
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println("Entering this.equals()");
		if (this == obj)
			return true;
		if (obj == null || (this.getClass() != obj.getClass()))
			return false;
		
		String objName = ((StockItem) obj).getName();
		return this.name.equals(objName);		
	}

	@Override
	public int compareTo(StockItem o) {
//		System.out.println("Entering this.compareTo()");
		if (this == o) {
			return 0;
		}
		if (o != null) {
			return this.name.compareTo(o.getName());
		}
		
		throw new NullPointerException();		
	}
	
	@Override
	public String toString() {
		return this.name + "\t price : " + String.format("%6.2f", this.price) + " . Reserved : " + this.reserved;
	}
	
	
	
	
	
}
