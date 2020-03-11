package com.fdmgroup.GroupProject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.classes.Basket;
import com.fdmgroup.classes.Item;
import com.fdmgroup.repos.BasketRepo;
import com.fdmgroup.repos.ItemRepo;

@Controller
@SessionAttributes("basket") // session binging of one basket persession
public class MyController {
	// DAO autowire

	@Autowired
	ItemDAO idao = new ItemDAO();

	@Autowired
	BasketDAO bd = new BasketDAO();

	// Repo autowire
	@Autowired
	ItemRepo itemRepo;

	@Autowired
	BasketRepo basketRepo;

	// session binding of each new basket

	@ModelAttribute("basket")
	private Basket makeBasket() {
		return new Basket();
	}

	// common pages

	@GetMapping("/")
	public String doWork(Model i) {
		List<Item> itemList = idao.listItems();
		i.addAttribute("itemList", itemList);
		return "home";
	}

	// Basket Page
	@PostMapping("addToBasket")
	public String addToBasket(Model i, Model j, @RequestParam String itemId, @RequestParam String quantity,
			@ModelAttribute(name = "basket") Basket basket) {
		List<Item> itemList = idao.listItems();
		i.addAttribute("itemList", itemList);

		Item item = idao.getItem(Integer.parseInt(itemId));
		if (item.getStock() < Integer.parseInt(quantity)|| Integer.parseInt(quantity)==0) {
			return "error";

		} else {
			//basket.getLineItem().put(idao.getItem(Integer.parseInt(itemId)), Integer.parseInt(quantity));
			
			Map<Item, Integer> basketMap = basket.getLineItem();
			
			
			if( ! basketMap.containsKey(item)){
				basketMap.put(item, Integer.parseInt(quantity));
			}
			else {
				
				basketMap.put(item, Integer.parseInt(quantity) + basketMap.get(item));
			}
			
			
			Set<Map.Entry<Item, Integer>> ses = basket.getLineItem().entrySet();
			double theSum = 0.00;

			for (Map.Entry<Item, Integer> se : ses) {
				theSum += (se.getKey().getPrice() * se.getValue());

				basket.setTotalPrice(theSum);

				bd.addBasket(basket);
				bd.updateBasket(basket);
				item.setStock(item.getStock() - Integer.parseInt(quantity));
				idao.updateItem(item);

			}

		}
		return "home";
	}

	@GetMapping("basket")

	public String viewBasket(Model i, @ModelAttribute(name = "basket") Basket basket) {

		Map<Item, Integer> basketlist = basket.getLineItem();
		i.addAttribute("basketlist", basketlist);
		i.addAttribute("totalPrice", basket.getTotalPrice());

		return "basket";
	}

}
