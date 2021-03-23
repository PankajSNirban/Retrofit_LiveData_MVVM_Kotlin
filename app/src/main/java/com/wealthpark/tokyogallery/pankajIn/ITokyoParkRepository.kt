package com.wealthpark.tokyogallery.pankajIn

import io.reactivex.Observable

interface ITokyoParkRepository {

    fun getCitiesAndFoodData(): Observable<TokyoParkModel>
}