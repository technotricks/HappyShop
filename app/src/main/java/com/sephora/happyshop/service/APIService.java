package com.sephora.happyshop.service;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.sephora.happyshop.model.Category;
import com.sephora.happyshop.model.Product;

/**
 * Created by Kishore on 21/8/2017.
 */

public interface APIService {

    @GET("products")
    Call<Category> getProducts(@Query("category") String category,@Query("page") int page);



    @GET("products/{id}")
    Call<Category> getProduct(@Path("id") String id);
}
