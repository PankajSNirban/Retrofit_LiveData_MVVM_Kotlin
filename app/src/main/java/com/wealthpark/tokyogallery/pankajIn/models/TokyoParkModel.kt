package com.wealthpark.tokyogallery.pankajIn.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TokyoParkModel {

    @SerializedName("foods")
    @Expose
    private var foods: List<FoodList?>? = null

    @SerializedName("cities")
    @Expose
    private var cities: List<CityList?>? = null

    fun getFoods(): List<FoodList?>? {
        return foods
    }

    fun setFoods(foods: List<FoodList?>?) {
        this.foods = foods
    }

    fun getCities(): List<CityList?>? {
        return cities
    }

    fun setCities(cities: List<CityList?>?) {
        this.cities = cities
    }
}