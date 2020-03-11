package com.fdmgroup.GroupProject;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.classes.Basket;
import com.fdmgroup.repos.BasketRepo;

@Service
public class BasketDAO {
	@Autowired 
	private BasketRepo bp;
	
	private Optional<Basket> baskets;
	private List<Basket> allBaskets;

	public void addBasket(Basket basket) {
		baskets = bp.findById(basket.getBasketId());
		if (!baskets.isPresent()) {

			bp.save(basket);
		}
	}

	public Basket getBasket(int basketId) {
		baskets = bp.findById(basketId);
		if (baskets.isPresent()) {

			return baskets.get();
		} else {
			System.out.print("No basket whith this id therefore is ");
			return null;
		}
	}

	public void removeBasket(int basketId) {
		baskets = bp.findById(basketId);

		if (baskets.isPresent()) {
			bp.delete(baskets.get());
			System.out.println("basket removed");
		} else {
			System.out.println("No basket under this id");
		}

	}

	public void updateBasket(Basket basket) {

		baskets = bp.findById(basket.getBasketId());
		if (baskets.isPresent()) {
			bp.save(basket);
			System.out.println("basket changed");
		}
	}

	public List<Basket> listBaskets() {
		allBaskets = bp.findAll();

		return allBaskets;
	}

	public BasketDAO() {

	}


}
