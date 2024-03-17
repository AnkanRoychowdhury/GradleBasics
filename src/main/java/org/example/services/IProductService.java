package org.example.services;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IProductService {
    @GET("products/{productId}")
    Call<JsonObject> getProducts(@Path("productId") int productId);
}
