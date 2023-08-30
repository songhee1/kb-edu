package com.service.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.spring.dao.ItemDAO;
import com.service.spring.domain.Item;
@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemDAO itemDAO;
	
	@Override
	public List<Item> getListItem() throws Exception {
		return itemDAO.getItemList();
	}

	@Override
	public Item getItem(Integer itemid) throws Exception {
		return itemDAO.getItem(itemid);
	}

}
