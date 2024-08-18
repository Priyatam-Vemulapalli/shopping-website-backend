package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Controller;

import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Service.BuyerService;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.exception.AcessNotFound;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.exception.InvalidOperation;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.exception.ProductNotFound;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.exception.UserNotFound;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.Product;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.requestBody.OrderProductRequestBody;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.responsebody.ProductResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/api/buyer")
public class BuyerController {

    @Autowired
    BuyerService buyerService;

    @GetMapping("/product/all")
    public ResponseEntity gettAllProducts(@RequestParam(required = false) String filter,
                                          @RequestParam(required = false) String sort){

        // To implement pagination add another RequestParam page number... for each page number,
        // return these number of items per page to return
        // e.g. page -1 => 0 to 10, page -2 => 11 to 20 items and so on...
        // Required is set to false for @RequestParam thus ensuring that cases
        // where the filters are null, and code should still return the products

        if(filter == null && sort == null){
            // We will randomly return the 10 products from DB
            List<ProductResponseBody> productResponseBodies = buyerService.getProductAtRandom();
            return new ResponseEntity<>(productResponseBodies,HttpStatus.OK);
        }
        else if(filter != null && sort == null){
            // We will return either max ratings or min rating products
            List<ProductResponseBody> productResponseBodies = buyerService.getProductByRating(filter);
            return new ResponseEntity(productResponseBodies,HttpStatus.OK);
        }
        else if(filter == null && sort !=null){
            // We will return top 10 products either of max price in table if sort = desc
            // We will return
            List<ProductResponseBody> productResponseBodies = buyerService.getProduct(sort);
            return new ResponseEntity<>(productResponseBodies,HttpStatus.OK);
        }
        else{
            // We will perform operations as per the filter and sort again with conditional statements
            List<ProductResponseBody> productResponseBodyList = buyerService.getProduct(filter, sort);
            return new ResponseEntity<>(productResponseBodyList, HttpStatus.OK);
        }
    }

    @PostMapping("product/buy")
    public void placeOrder(@RequestParam UUID buyerID,
                           @RequestBody List<OrderProductRequestBody> products){
        // service layer design...
        try{
            buyerService.purchaseProduct(buyerID,products);
        }
        catch(InvalidOperation | ProductNotFound | AcessNotFound | UserNotFound exception){
            exception.getLocalizedMessage();
        }
    }
}

