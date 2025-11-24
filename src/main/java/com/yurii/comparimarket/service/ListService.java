package com.yurii.comparimarket.service;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Optional;
import com.yurii.comparimarket.model.ListItem;

public class ListService {
	
	private List<ListItem> purchaseList;
	
	public ListService() {
		purchaseList = new ArrayList<ListItem>();
	}
	
	public void addItem(ListItem listItem) {
		purchaseList.add(listItem);
	}
	
	public void removeItem(ListItem listItem) {
		Optional<Integer> id = findItemByItemName(listItem.getATBItemName());
		if(id.isPresent()) {
			purchaseList.remove(id.get());
		}
		
	}
	
	public Optional<Integer> findItemByItemName(String listItemName) {
		for(ListItem item : purchaseList) {
			if(item.getATBItemName().equals(listItemName)||
					item.getSilpoItemName().equals(listItemName)||
					item.getVarusItemName().equals(listItemName)) {
				return Optional.of(purchaseList.indexOf(item));
			}
		}
		return Optional.absent();
	}
	
	public List<ListItem> getAllItems(){
		return purchaseList;
	}

}
