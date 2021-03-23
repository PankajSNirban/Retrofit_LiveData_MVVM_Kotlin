package com.wealthpark.tokyogallery.pankajIn.models

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class FoodList(
    @SerializedName("name") var name: String?,
    @SerializedName("image") var image: String?
) :Serializable{

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}

