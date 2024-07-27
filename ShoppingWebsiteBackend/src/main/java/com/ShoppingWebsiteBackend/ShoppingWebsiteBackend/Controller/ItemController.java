package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Controller;

import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Service.ItemService;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/api/additem")
    public String registerItem(@RequestBody Item item){
        itemService.registerItem(item);
        return "Item added";
    }

    @GetMapping("/api/item")
    public Item getItem(@RequestParam int itemcode){

        Item item=itemService.getItem(itemcode);
        return item;
    }

}
