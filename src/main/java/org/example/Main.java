package org.example;

import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.example.services.IProductService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String url = "https://fakestoreapi.com/products/1";
        dealingWithOkHttp(url);
        dealingWithRetrofit("https://fakestoreapi.com/");
    }

    public static void dealingWithOkHttp(String url){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();

        try(Response response = client.newCall(request).execute()){
            if(response.isSuccessful() && response.body() != null) {
                System.out.println("------------------------OkHttp Response-------------------------");
                System.out.println(response.body().string());
                System.out.println("----------------------------------------------------------------");
            }else {
                System.out.println("Something went wrong...");
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void dealingWithRetrofit(String url){
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        IProductService productService = retrofit.create(IProductService.class);
        try{
            Call<JsonObject> response = productService.getProducts(1);
            JsonObject res = response.execute().body();
            System.out.println("------------------------Retrofit Response-------------------------");
            System.out.println(res);
            System.out.println("----------------------------------------------------------------");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}