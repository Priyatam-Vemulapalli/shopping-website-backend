package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Service;

import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Repository.ItemRepository;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Repository.UserRepository;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.ApplicationUser;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.Cart;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//will perform CRED operations
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    //to register a new user
    public void registerUser(ApplicationUser applicationUser){
        //this method wants to save the user into the database.
        //so this method will call the repository layer to save the user.
        userRepository.createUser(applicationUser);
    }

    //get the details through email
    public ApplicationUser getUserByEmail(String email){

        //to get the user by email
        ApplicationUser applicationUser=userRepository.getUserByEmail(email);

        return applicationUser;
    }

    //update the details through email
    public void updateDetailsByUserName(ApplicationUser applicationUser, String email){
        userRepository.updateUser(applicationUser,email);
    }

    //delete the user from repository
    public void deleteUserByEmail(String email){
        userRepository.deleteUser(email);
    }

    //add item to one user's cart
    public void addItemToCart(ApplicationUser applicationUser,int itemCode){
        Item item= itemRepository.getItem(itemCode);
        Cart cart=userRepository.getCartOfThatUser(applicationUser);
        cart.addToCart(item,1);
        userRepository.updateCart(applicationUser,cart);
    }
}
