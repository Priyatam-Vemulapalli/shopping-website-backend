package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Service;

import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Repository.UserRepository;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//will provide additional functionalities
@Service
public class AllUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //get all the usernames in repository
    public List<String> getAllUsernames(){
        HashMap<String, ApplicationUser> hm=userRepository.getUsernamesfromDB();
        List<String> usernames=new ArrayList<>();
        for(String key:hm.keySet()){
            ApplicationUser user=hm.get(key);
            String name=user.getFirstname()+user.getLastname();
            usernames.add(name);
        }
        return usernames;
    }
}
