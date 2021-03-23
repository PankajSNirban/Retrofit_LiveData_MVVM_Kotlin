package com.wealthpark.tokyogallery.pankajIn

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import io.reactivex.Observable

class TokyoParkImpl(private val onBoardingServiceService: WealthAPIService) : ITokyoParkRepository {


    override fun getCitiesAndFoodData(): Observable<TokyoParkModel> {

        return Observable.create {

            val apiCall = onBoardingServiceService.retrieveCityAndFoodList()

            apiCall.enqueue(object : Callback<TokyoParkModel> {

                override fun onFailure(call: Call<TokyoParkModel>, t: Throwable) {
                    it.onNext(onFailureHandler(t.localizedMessage))
                    it.onComplete()
                }

                override fun onResponse(call: Call<TokyoParkModel>, response: Response<TokyoParkModel>) {
                    response.body()?.let { it1 -> it.onNext(it1) }
                    it.onComplete()
                }
            })
        }
    }


    fun onFailureHandler(error: String): TokyoParkModel {
        var response = TokyoParkModel()
        response.setCities(null);
        response.setFoods(null);
        return response
    }


}
