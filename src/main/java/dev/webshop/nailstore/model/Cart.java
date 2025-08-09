package dev.webshop.nailstore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Cart {
	private List<CartItem> items = new ArrayList<>();

	public void addItem(Product product, int quantity) {
		for (CartItem item : items) {
			if (item.getProduct().getId().equals(product.getId())) {
				item.setQuantity(item.getQuantity() + quantity);
				return;
			}
		}
		items.add(new CartItem(product, quantity));
	}

	public void removeItem(Long productId) {
		items.removeIf(item -> item.getProduct().getId().equals(productId));
	}

	public void modifyItemQuantity(Long productId, int newQuantity) {
		if (items.isEmpty()) return;

		for (CartItem item : items) {
			if (item.getProduct().getId().equals(productId)) {
				item.setQuantity(newQuantity);
				return;
			}
		}
	}

	public int getTotal() {
		return items.stream()
				.mapToInt(CartItem::getTotalPrice)
				.sum();
	}
}
