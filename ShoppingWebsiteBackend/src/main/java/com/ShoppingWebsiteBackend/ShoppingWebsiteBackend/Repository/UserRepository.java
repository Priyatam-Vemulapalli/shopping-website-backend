package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Repository;

import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.ApplicationUser;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.Cart;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.Item;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserRepository {
    //right now we are not using any database.
    HashMap<String, ApplicationUser> userDB = new HashMap<>(); //user email, application object

    //maintains list of all carts
    HashMap<ApplicationUser,Cart> cartDB = new HashMap<>();

    public void createUser(ApplicationUser applicationUser){
        userDB.put(applicationUser.getEmail(),applicationUser);
        Cart cart=new Cart(applicationUser);
        cartDB.put(applicationUser, cart);
    }

    public ApplicationUser getUserByEmail(String email){
        return userDB.get(email);
    }

    public void updateUser(ApplicationUser applicationUser, String email){
        userDB.put(email,applicationUser);
    }

    public void deleteUser(String email){
        userDB.remove(email);
    }

    //will return the DB object when needed
    public HashMap<String, ApplicationUser> getUsernamesfromDB(){
        return userDB;
    }

    public void addItemToCart(Cart cart, ApplicationUser applicationUser){
        cartDB.put(applicationUser,cart);
    }

    public Cart getCartOfThatUser(ApplicationUser applicationUser){
        return cartDB.get(applicationUser);
    }
    public void updateCart(ApplicationUser applicationUser, Cart cart){
        cartDB.put(applicationUser,cart);
    }
}
