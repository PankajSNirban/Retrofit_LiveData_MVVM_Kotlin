package com.wealthpark.tokyogallery.pankajIn.interfaces

import com.wealthpark.tokyogallery.pankajIn.models.TokyoParkModel
import io.reactivex.Observable

interface ITokyoParkRepository {

    fun getCitiesAndFoodData(): Observable<TokyoParkModel>
}