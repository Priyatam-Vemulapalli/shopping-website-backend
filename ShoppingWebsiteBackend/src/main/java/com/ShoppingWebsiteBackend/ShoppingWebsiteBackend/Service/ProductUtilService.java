package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Service;


import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.AppUser;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.Product;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.responsebody.ProductResponseBody;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.responsebody.UserResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductUtilService {
    // this class purpose is to convert the products into a product response body.

    public List<ProductResponseBody> convertProductToProductResponse(List<Product> products){
        List<ProductResponseBody> productResponseBodies = new ArrayList<>();

        for(Product product : products){

            // Try model mapper to map all these automatically!
            AppUser seller = product.getSeller();
            ProductResponseBody productResponseBody = new ProductResponseBody();
            UserResponse userResponse=new UserResponse();
            // Setting product values inside the product response body
            productResponseBody.setProductName(product.getProductName());
            productResponseBody.setCategory(product.getProductCategory());
            productResponseBody.setPrice(product.getPrice());
            productResponseBody.setRating(product.getRating());
            productResponseBody.setQuantity(product.getQuantity());
            // Setting values inside the user response body.
            userResponse.setUserName(seller.getName());
            userResponse.setEmail(seller.getEmail());
            userResponse.setPhoneNumber(seller.getPhoneNumber());
            userResponse.setAddress(seller.getAddress());
            userResponse.setAge(seller.getAge());
            // Set userResponseBody inside product response body.
            productResponseBody.setUserResponse(userResponse);

            // For a specific product, product response body is built. Now we need to add each product response body into list.
            productResponseBodies.add(productResponseBody);
        }
        return productResponseBodies;
    }
}
