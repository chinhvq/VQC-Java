package test.set_stockingapp;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class StockList {
	private final Map<String, StockItem> list;

	protected StockList() {
		list = new LinkedHashMap<>();
		// LinkedHashMap will keep item in the order that item being inputed
	}

	protected int addStock(StockItem item) {
		if (item != null) {
			// check if item already in the list or not
			StockItem inStock = list.getOrDefault(item.getName(), item);
			// check if item in the list already have some quantity then add new quantity
			// into current item in the list
			if (inStock != item) {
				item.adjustStock(inStock.availableQuantityInStock());
			}
			list.put(item.getName(), item);
			return item.availableQuantityInStock();
		}
		return 0;
	}

	protected int sellStock(String itemName, int quantity) {
		StockItem inStock = list.get(itemName);
		if (inStock != null && quantity > 0) {
			inStock.finaliseStock(quantity);
			return quantity;
		}
		return 0;
		
//		StockItem inStock = list.getOrDefault(itemName, null);
//		if (inStock != null && (inStock.availableQuantityInStock() >= quantity) && (quantity > 0)) {
//			inStock.adjustStock(-quantity);
//			return quantity;
//		}
//		return 0;
	}

	protected int reserveStock(String itemName, int quantity) {
		StockItem inStock = list.get(itemName);
		if (inStock != null && quantity > 0) {
			inStock.reserveStock(quantity);
			return quantity;
		}
		return 0;
	}
	
	protected int unReserveStock(String itemName, int quantity) {
		StockItem inStock = list.get(itemName);
		if (inStock != null && quantity > 0) {
			inStock.unReserveStock(quantity);
			return quantity;
		}
		return 0;
	}
	
	protected StockItem get(String itemName) {
		return list.get(itemName);
	}

	// this PriceList unmodifiableMap will cannot be modified and the items inside
	// this Map cannot be modified too ==> it is better than the Items() Map
	protected Map<String, Double> PriceLists() {
		Map<String, Double> prices = new LinkedHashMap<>();
		for (Entry<String, StockItem> item : list.entrySet()) {
			prices.put(item.getKey(), item.getValue().getPrice());
		}
		return Collections.unmodifiableMap(prices);
	}

	// unmodifiableMap is cannot be modified and faster because we do not not to
	// create a copy of the collection
	protected Map<String, StockItem> Items() {
		return Collections.unmodifiableMap(list);
	}

	@Override
	public String toString() {
		String s = "\nStock List\n";
		double totalCost = 0.0;

		for (Map.Entry<String, StockItem> item : list.entrySet()) {
			StockItem stockItem = item.getValue();
			double itemValue = stockItem.getPrice() * stockItem.availableQuantityInStock();

			s = s + stockItem + " . There are " + stockItem.availableQuantityInStock() + " in stock. Value of items: ";
			s = s + String.format("%.2f", itemValue) + "\n";
			totalCost += itemValue;
		}

		return s + " Total stock value " + String.format("%.2f", totalCost);
	}

}
