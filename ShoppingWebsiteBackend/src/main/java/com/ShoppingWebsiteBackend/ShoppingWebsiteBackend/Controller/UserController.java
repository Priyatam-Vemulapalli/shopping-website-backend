package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Controller;

import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Service.AllUserDetailsService;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Service.UserService;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {
    // dependency injection
    @Autowired
    private UserService userService;

    @Autowired
    private AllUserDetailsService allUserDetailsService;

    //endpoint to register a user
    @PostMapping("/api/register/user")
    public String registerUser(@RequestBody ApplicationUser applicationUser){
        //logic for saving user must be written in service layer!
        userService.registerUser(applicationUser);
        return "User created successfully";
    }

    //creating an endpoint to get the user details through email
    @GetMapping("/api/user")
    public ApplicationUser getDetails(@RequestParam String email){
        //controller layer will call the service layer
        return userService.getUserByEmail(email);

    }

    //endpoint that will update the user details by email
    @PutMapping("/api/user/update")
    public String updateDetails(@RequestBody ApplicationUser applicationUser,
                                         @RequestParam String email){
        userService.updateDetailsByUserName(applicationUser,email);
        return "updated the user details";
    }

    //endpoint to delete the user
    @DeleteMapping("/api/user/delete")
    public String deleteUserByEmail(@RequestParam String email){
        userService.deleteUserByEmail(email);
        return "user got deleted";
    }

    //endpoint to get all the usernames
    @GetMapping("/api/user/all")
    public List<String> getAllUsernames(){
        return allUserDetailsService.getAllUsernames();
    }
}
