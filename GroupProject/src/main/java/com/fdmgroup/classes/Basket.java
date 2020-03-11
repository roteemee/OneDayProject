package com.fdmgroup.classes;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.SequenceGenerator;



@Entity(name = "baskets")
public class Basket {
	@Id
	@Column(name= "basket_id", length= 5)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "basket")
    @SequenceGenerator(sequenceName = "basket_seq", allocationSize = 1, name = "basket")
	private int basketId;
	
	@ElementCollection
	@CollectionTable(name = "item_quantity", joinColumns = @JoinColumn(name = "basket_id"))
	@MapKeyJoinColumn(name = "item_id")
	@Column(name = "quantity")

	private Map<Item, Integer> lineItem = new HashMap<Item, Integer>();
	
	@Column(name= "total_price",  precision =10 , scale =2)
	private double totalPrice;
	
	
	
	public Basket() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Basket(Map<Item, Integer> lineItem) {
		super();
		this.lineItem = lineItem;
	}



	public Map<Item, Integer> getLineItem() {
		return lineItem;
	}
	public void  setLineItem(Map<Item, Integer> lineItem) {
		this.lineItem = lineItem;
	}
	
	
	public double getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}



	public int getBasketId() {
		return basketId;
	}



	@Override
	public String toString() {
		return "Basket [basketId=" + basketId + ", lineItem=" + lineItem + ", totalPrice=" + totalPrice + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + basketId;
		result = prime * result + ((lineItem == null) ? 0 : lineItem.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Basket other = (Basket) obj;
		if (basketId != other.basketId)
			return false;
		if (lineItem == null) {
			if (other.lineItem != null)
				return false;
		} else if (!lineItem.equals(other.lineItem))
			return false;
		if (Double.doubleToLongBits(totalPrice) != Double.doubleToLongBits(other.totalPrice))
			return false;
		return true;
	}






}
