package com.fdmgroup.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "items")
public class Item {

	@Id
	@Column(name = "item_id", length = 5)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item")
	@SequenceGenerator(sequenceName = "item_seq", allocationSize = 1, name = "item")
	private int itemId;
	@Column(name = "description", length = 50)
	private String description;
	@Column(name = "price", precision = 10, scale = 2)
	private double price;
	@Column(name = "stock", length = 10)
	private int stock;

	public Item() {
		// TODO Auto-generated constructor stub
	}

	public Item(String description, double price, int stock) {
		super();
		this.description = description;
		this.price = price;
		this.stock = stock;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getItemId() {
		return itemId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (itemId != other.itemId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", description=" + description + ", price=" + price + ", stock=" + stock
				+ "]";
	}
	

}
