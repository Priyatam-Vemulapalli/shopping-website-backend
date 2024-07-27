package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Repository;

import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.ApplicationUser;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserRepository {
    //right now we are not using any database.
    // using hashmap as temp database.
    HashMap<String, ApplicationUser> userDB = new HashMap<>(); //user email, application object
    public void createUser(ApplicationUser applicationUser){
        userDB.put(applicationUser.getEmail(),applicationUser);
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
}
