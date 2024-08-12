package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.Repository;

import com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query(value = "select * from product where seller_id =:sellerID", // Use your dynamic variable after colon
    nativeQuery = true) // Use your own native Query instead of JPA query
    public List<Product> getAllProductsBySellerID(UUID sellerID);

    @Query(value = "select * from product where rating =:maximumRating order by price asc limit 10",nativeQuery = true)
    public List<Product> getProductByMaximumRatingAndAsc(double maximumRating);

    @Query(value = "select * from product where rating =:maximumRating order by price desc limit 10",nativeQuery = true)
    public List<Product> getProductByMaximumRatingAndDesc(double maximumRating);

    @Query(value = "select * from product where rating = minimumRating order by price asc limit 10", nativeQuery = true)
    public List<Product> getProductByMinimumRatingAndAsc(double minimumRating);

    @Query(value = "select * from product where rating = minimumRating order by price desc limit 10", nativeQuery = true)
    public List<Product> getProductByMinimumRatingAndDesc(double minimumRating);

    @Query(value = "select max(rating) from product", nativeQuery = true)
    public Double getMaximumRating();

    @Query(value = "select min(rating) from product", nativeQuery = true)
    public Double getMinimumrating();

    @Query(value = "select * from product order by price asc limit 10", nativeQuery = true)
    public List<Product> getProductInAsc();

    @Query(value = "select * from product order by price desc limit 10", nativeQuery = true)
    public List<Product> getProductInDesc();

    @Query(value = "select * from product where rating =:Rating limit 10")
    public List<Product> getProductByRating(double Rating);

    @Query(value = "select * from product limit 10")
    public List<Product> getProductsAtRandom();

}
