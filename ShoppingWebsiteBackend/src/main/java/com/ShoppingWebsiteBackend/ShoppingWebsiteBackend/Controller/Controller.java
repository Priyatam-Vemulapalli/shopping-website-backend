package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Controller;

import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.ApplicationUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/hello")
    public String sayHi(){
        return "Hello";
    }

    // post method: client will send some data to server and this data is called request body
    // generally we send request body in json format
    // and that json format will ger converted in the java object
    @PostMapping("/api/register")
    //RequestBody: data sent by client to save info in the server
    public void createAccount(@RequestBody ApplicationUser applicationUser){
        // how springboot is converting json request body into java object ?

        System.out.println(applicationUser.getFirstname());
    }

}
