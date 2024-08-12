package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Service;

import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Repository.ProductRepository;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.Product;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.responsebody.ProductResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.SimpleTimeZone;

@Service
public class BuyerService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductUtilService productUtilService;

    public List<ProductResponseBody> getProduct(String filter, String sort){
        List<Product> productList = null;
        if(filter.equals("MAXIMUM-RATING")){
            Double maxRating=productRepository.getMaximumRating();
            if(sort.equals("asc")){
                productList = productRepository.getProductByMaximumRatingAndAsc(maxRating);
            }
            else{

                productList = productRepository.getProductByMaximumRatingAndDesc(5);
            }
        } else if (filter.equals("MINIMUM-RATING")) {
            Double minRating = productRepository.getMinimumrating();
            if(sort.equals("asc")){

                productList = productRepository.getProductByMinimumRatingAndAsc(5);
            }
            else{
                productList = productRepository.getProductByMinimumRatingAndDesc(5);
            }
        }
        assert productList != null;
        return productUtilService.convertProductToProductResponse(productList);
    }

    //overloading the method such that only sorting criteria is given
    public List<ProductResponseBody> getProduct(String sort){
        List<Product> productList = null;
        if(sort.equals("asc")){
            productList = productRepository.getProductInAsc();
        }
        else{
            productList = productRepository.getProductInDesc();
        }
        return productUtilService.convertProductToProductResponse(productList);
    }

    public List<ProductResponseBody> getProductByRating(String rating){
        List<Product> productList = null;
        if(rating.equals("MAXIMUM-RATING")){
            productList = productRepository.getProductByRating(productRepository.getMaximumRating());
        }
        else{
            productList = productRepository.getProductByRating(productRepository.getMinimumrating());
        }
        return productUtilService.convertProductToProductResponse(productList);
    }
    public List<ProductResponseBody> getProductAtRandom(){

        List<Product> productList = productRepository.getProductsAtRandom();
        return productUtilService.convertProductToProductResponse(productList);
    }
}
