package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Controller;


import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Service.UserService;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.ApplicationUser;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private UserService userService=new UserService();
    @PostMapping("/api/register/user")
    public String registerUser(@RequestBody ApplicationUser applicationUser){
        //logic for saving user must be written in service layer!
        userService.registerUser(applicationUser);
        return "User created succefully";
    }

    //creating an endpoint to get the userdetails through emial

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


    @DeleteMapping("/api/user/delete")
    public String deleteUserByEmail(@RequestParam String email){
        userService.deleteUserByEmail(email);
        return "user got deleted";
    }
}
