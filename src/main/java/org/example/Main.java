package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://fakestoreapi.com/products/1";

        Request request = new Request.Builder().url(url).build();
        try(Response response = client.newCall(request).execute()){
            if(response.isSuccessful() && response.body() != null) {
                System.out.println(response.body().string());
            }else {
                System.out.println("Wrong...");
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}