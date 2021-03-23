package com.wealthpark.tokyogallery.pankajIn

import retrofit2.Call
import retrofit2.http.GET


interface WealthAPIService {

    @GET("/a2b63ef226c08553b2f9")
    fun retrieveCityAndFoodList(): Call<TokyoParkModel>

}