package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Controller;

import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Service.ItemService;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Service.UserService;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.ApplicationUser;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

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

    @PostMapping("api/user/buyitem")
    public String addItemToThatUserCart(
            @RequestParam(required = true) String email,
            @RequestParam(required = true) int itemCode
    ){
        ApplicationUser applicationUser=userService.getUserByEmail(email);
        userService.addItemToCart(applicationUser,itemCode);
        return "added successfully";
    }
}
