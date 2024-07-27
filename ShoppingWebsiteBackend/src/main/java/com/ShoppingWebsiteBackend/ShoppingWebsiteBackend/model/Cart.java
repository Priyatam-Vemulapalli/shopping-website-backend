package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model;

import java.util.HashMap;

public class Cart {
    private final ApplicationUser applicationUser;
    //if you ever face any issue with cart note it that cart is now declared final
    private final HashMap<Item,Integer> cart=new HashMap<>();

    public Cart(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public void addToCart(Item item,int quantity){
        cart.put(item,quantity);
    }
    public void removeFromCart(Item item){
        cart.remove(item);
    }

    public String getUsername(){

        return applicationUser.getFirstname()+applicationUser.getLastname();
    }

}
