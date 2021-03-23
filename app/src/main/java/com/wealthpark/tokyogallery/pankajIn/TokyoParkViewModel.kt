package com.wealthpark.tokyogallery.pankajIn

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class TokyoParkViewModel(application: Application) : AndroidViewModel(application) {

    var tokyoParkGalleryData: MutableLiveData<TokyoParkModel> = MutableLiveData()

    val errorLiveData = MutableLiveData<String?>()
    val loaderLiveData = MutableLiveData<Boolean>()
    private var wealthAPIService: WealthAPIService

    init {
        var apiClient = TokyoParkAPIClient.getAPIClient()
        wealthAPIService = apiClient.create(WealthAPIService::class.java)
    }


    fun getCitiesAndFoodData()
    {
        loaderLiveData.value = true
        val disposable = CompositeDisposable()
        val observable = TokyoParkImpl(wealthAPIService).getCitiesAndFoodData()
        val dispose = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                loaderLiveData.value = false
                if (response?.getCities() != null && response.getFoods() !=null)
                {
                    tokyoParkGalleryData.value = response
                } else {
                    errorLiveData.value = "error"
                }
            }

        disposable.add(dispose)
    }


}