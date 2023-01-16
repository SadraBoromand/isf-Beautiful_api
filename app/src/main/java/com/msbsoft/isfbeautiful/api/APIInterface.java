package com.msbsoft.isfbeautiful.api;

import com.msbsoft.isfbeautiful.models.Food;
import com.msbsoft.isfbeautiful.models.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("place.json")
    Call<List<Place>> getPlaces();

    @GET("allPlaces.json")
    Call<List<Place>> getAllPlaces();

    @GET("food.json")
    Call<List<Food>> getFoods();

    @GET("allFoods.json")
    Call<List<Food>> getAllFoods();

}
