package test.set_stockingapp;

import java.util.Map;

public class ShoppingCartManagement {
	private static StockList stockList = new StockList();

	public static void main(String[] args) {
		StockItem temp = new StockItem("bread", 0.86, 100);
		stockList.addStock(temp);

		temp = new StockItem("cake", 1.10, 7);
		stockList.addStock(temp);
		temp = new StockItem("car", 12.5, 2);
		stockList.addStock(temp);
		temp = new StockItem("chair", 62, 10);
		stockList.addStock(temp);

		temp = new StockItem("cup", 0.5, 200);
		stockList.addStock(temp);
		temp = new StockItem("cup", 0.45, 7);
		stockList.addStock(temp);

		temp = new StockItem("door", 72.95, 4);
		stockList.addStock(temp);
		temp = new StockItem("juice", 2.50, 36);
		stockList.addStock(temp);
		temp = new StockItem("towel", 2.40, 80);
		stockList.addStock(temp);
		temp = new StockItem("vasel", 8.76, 40);
		stockList.addStock(temp);
		temp = new StockItem("phone", 96.99, 35);
		stockList.addStock(temp);

		System.out.println(stockList);
		System.out.println("\nList of items in stock:");
		int nIndex = 0;
		for (String s : stockList.Items().keySet()) {
			System.out.print("\t" + String.format("%6s", s) + "\t");
			nIndex++;
			if (nIndex != 0 && nIndex % 6 == 0) {
				System.out.println();
			}
		}

		System.out.println();
		Basket timBasket = new Basket("tim");
		sellItem(timBasket, "chair", 6);
		sellItem(timBasket, "chair", 3);
		sellItem(timBasket, "chair", 3); // return 0 because only 1 items left in the stocklist
		System.out.println(timBasket);

		sellItem(timBasket, "car", 1);
		sellItem(timBasket, "car", 1);
		if (sellItem(timBasket, "car", 1) == 0) {
			System.out.println("\nThere is no more car in the stock.");
		}
		System.out.println(timBasket);
		sellItem(timBasket, "porsche", 1); // We do not sell the item
		
		removeItem(timBasket, "chair", 8);
		removeItem(timBasket, "chair", 2);
		System.out.println(timBasket);
		removeItem(timBasket, "car", 1);
		System.out.println(timBasket);
		removeItem(timBasket, "car", 1);
		System.out.println(timBasket);
		removeItem(timBasket, "car", 1);
		System.out.println(timBasket);
		removeItem(timBasket, "chair", 1);
		System.out.println(timBasket);
		
		checkOut(timBasket);
		System.out.println(timBasket);
		
		// even the stocklist.Items will return unmodifiable Map however the object
		// inside the return Map still can be adjust
		// ==> consider to design program not allow to change the value of inside object
		stockList.Items().get("chair").adjustStock(1000);
		stockList.get("chair").adjustStock(-500);
		System.out.println(stockList);		

		for (Map.Entry<String, Double> item : stockList.PriceLists().entrySet()) {
			System.out.println(item.getKey() + "\t at price of " + String.format("%6.2f", item.getValue()));
		}
	}

	protected static int sellItem(Basket basket, String itemName, int quantity) {
		StockItem stockItem = stockList.get(itemName);
		if (stockItem == null) {
			System.out.println("\nWe do not sell the item");
			return 0;
		}
		// check if the stockList has item with enough quantity or not => if yes we add
		// to basket
		if (stockList.reserveStock(itemName, quantity) != 0) {
			return basket.addToBasket(stockItem, quantity);
		}
		return 0;
	}
	
	protected static int removeItem(Basket basket, String itemName, int quantity) {
		StockItem stockItem = stockList.get(itemName);
		if (stockItem == null) {
			System.out.println("\nWe do not sell the item");
			return 0;
		}
		
		if (basket.removeFromBasket(stockItem, quantity) == quantity ) {
			return stockList.unReserveStock(itemName, quantity);			
		}
		return 0;
				
	}
	
	protected static void checkOut(Basket basket) {
		for (Map.Entry<StockItem, Integer> item : basket.Items().entrySet()) {
			stockList.sellStock(item.getKey().getName(), item.getValue());
		}
		basket.clearBasket();
	}

}
