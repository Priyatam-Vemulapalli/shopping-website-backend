package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.exception;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound(String message){
        super(message);
    }
}
