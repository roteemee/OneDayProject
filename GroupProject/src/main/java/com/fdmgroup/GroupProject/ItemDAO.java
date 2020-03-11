package com.fdmgroup.GroupProject;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.classes.Item;
import com.fdmgroup.repos.ItemRepo;

@Service
public class ItemDAO {
	
	@Autowired
	private ItemRepo ir;

	
	private Optional <Item> items;
	private List<Item>allItems;
	
	public void addItem(Item item) {
		items = ir.findById(item.getItemId());
		if (!items.isPresent()) {

			ir.save(item);
		}
	}
	
	public Item getItem(int itemId) {
		items = ir.findById(itemId);
		if (items.isPresent()) {
			return items.get();
		}
		else {
			return null;
					
		}
	}
	
	public void removeItem(int itemId) {
		items = ir.findById(itemId);

		if (items.isPresent()) {
			ir.delete(items.get());
			System.out.println("item removed");
		} else {
			System.out.println("No item under this id");
		}

	}
	
	public void updateItem(Item item) {

		items = ir.findById(item.getItemId());
		if (items.isPresent()) {
			ir.save(item);
			System.out.println("item changed");
		}
	}
	
	public List<Item> listItems(){
		allItems = ir.findAll();
		return allItems;
	}
	
	public ItemDAO() {
		
	}

}
