package dev.webshop.nailstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
	private Product product;
	private int quantity;

	public int getUnitPrice() {
		return product.getPrice();
	}

	public int getTotalPrice() {
		return quantity * getUnitPrice();
	}
}