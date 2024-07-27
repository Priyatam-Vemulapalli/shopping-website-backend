package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Repository;

import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.Item;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class ItemRepository {

    HashMap<Integer, Item> itemsDB = new HashMap<>(); //itemcode, Item object

    //add item into the DB
    public void createItem(Item item){
        itemsDB.put(item.getItemCode(),item);
    }

    //get item from DB
    public Item getItem(int itemCode){
        return itemsDB.get(itemCode);
    }
}
