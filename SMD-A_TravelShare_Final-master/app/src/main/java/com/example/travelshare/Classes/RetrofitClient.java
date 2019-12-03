package com.example.travelshare.Classes;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class RetrofitClient {
//    private static final String baseURI= "http://10.0.2.2/api/";                //Local
    private static final String baseURI= "https://pixiders.com/dawoodproject/api/";  //Live
    private static Client client = null;

    public interface Client{
        @POST("login.php")
        Call<User> login(@Body JsonObject json);

        @POST("users.php")
        Call<Response> register(@Body JsonObject json);

        @GET("locations.php")
        Call<List<Location>> getLocations();

        @POST("trips.php")
        Call<Response> postTrip(@Body JsonObject json);

        @GET("trips.php")
        Call<List<Trip>> getMyTrips(@Query("user_id") String user_id);

        @GET("trips.php")
        Call<List<Trip>> getOtherTrips(@Query("not_user_id") String user_id);

        @POST("triprequests.php")
        Call<Response> sendRequest(@Body JsonObject json);

        @GET("triprequests.php")
        Call<List<TripRequest>> getTripRequests(@Query("id") String user_id);
    }

    public static Client getClient() {
        if (client==null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            client = retrofit.create(Client.class);
        }
        return client;
    }


}