package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Service;

import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Repository.ProductRepository;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.exception.AcessNotFound;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.exception.InvalidOperation;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.exception.ProductNotFound;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.exception.UserNotFound;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.AppUser;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.Product;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.requestBody.OrderProductRequestBody;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.responsebody.BillResponseBody;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.responsebody.ProductResponseBody;
import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.responsebody.SpecificProductOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BuyerService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductUtilService productUtilService;

    @Autowired
    CommonUserService commonUserService;

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

    public BillResponseBody purchaseProduct(UUID buyerID, List<OrderProductRequestBody> orderProductRequestBodies){
        // validate if the userId is present in the system.
        AppUser appUser = commonUserService.getUserById(buyerID);
        if(appUser == null){
            throw new UserNotFound(
                    String.format("User with id %s does not exist",buyerID)
            );
        }
        if(!appUser.getUsertype().equals("BUYER")){
            throw new AcessNotFound(
                        String.format("The user with name %s does not have access to buy the product.",appUser.getName())
            );
        }

        // two types of authentications are cleared then the user will be able to buy the product.
        int total_bill_price=0;
        BillResponseBody bill = new BillResponseBody();
        bill.setProducts(new ArrayList<>());

        for(OrderProductRequestBody body : orderProductRequestBodies){
            String productName = body.getProductName();
            int productQuantity = body.getQuantity();
            Product product = productRepository.findByProductName(productName);

            if(product == null){
                throw new ProductNotFound(
                        String.format("product with name %s does not exits",productName));
            }

            if(productQuantity > product.getQuantity()){
                throw new InvalidOperation(
                        String.format("Product with name %s does not have enough quantity", productName));
            }

            int totalQuantitySold = product.getTotalSoldQuantity() + productQuantity;
            int quantityLeft = product.getQuantity()-productQuantity;
            productRepository.updateProductQuantity(quantityLeft,appUser.getId(), totalQuantitySold);
            SpecificProductOrderDetail productDetails = new SpecificProductOrderDetail();

            productDetails.setProductName(product.getProductName());
            productDetails.setProductID(product.getId());
            productDetails.setUnitPrice(product.getPrice());
            productDetails.setTotalPurchasedPrice(productQuantity*product.getPrice());


            total_bill_price += product.getPrice() * productQuantity;
            bill.getProducts().add(productDetails);

        }
        bill.setTotalAmount(total_bill_price);
        return bill;
    }
}
