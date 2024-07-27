package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Service;

import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Repository.ItemRepository;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void registerItem(Item item){
        itemRepository.createItem(item);
    }
    public Item getItem(int itemcode){

        Item item=itemRepository.getItem(itemcode);
        return item;
    }
}
