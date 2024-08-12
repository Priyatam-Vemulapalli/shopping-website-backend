package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Controller;

import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Service.BuyerService;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.Product;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.responsebody.ProductResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController("/api/buyer")
public class BuyerController {

    @Autowired
    BuyerService buyerService;

    @GetMapping("/product/all")
    public ResponseEntity gettAllProducts(@RequestParam(required = false) String filter,
                                          @RequestParam(required = false) String sort){
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

}

