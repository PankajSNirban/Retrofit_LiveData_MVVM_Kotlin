package com.wealthpark.tokyogallery.pankajIn

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

internal class TokyoFoodAdapter(context: Context, parkDataList: List<FoodList?>?)  : RecyclerView.Adapter<TokyoFoodAdapter.ParkItemsHolder>()  {

    private val tokyoParkData: List<FoodList?>?=parkDataList
    private val context:Context=context


    override fun onCreateViewHolder(parent: ViewGroup, itemView: Int): TokyoFoodAdapter.ParkItemsHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.tokyo_park_items, parent, false)
        return ParkItemsHolder(itemView)
    }

    override fun onBindViewHolder(holder: TokyoFoodAdapter.ParkItemsHolder, position: Int) {

        val tokyoParkData = tokyoParkData?.get(position)
        holder.title.text = tokyoParkData?.name
        holder.desc.visibility=View.INVISIBLE
        holder.title.setTypeface(holder.title.typeface, Typeface.BOLD);

        Glide.with(context)
            .load(tokyoParkData?.image)
            .error(R.drawable.ic_launcher_background)
            .skipMemoryCache(true) //2
            .diskCacheStrategy(DiskCacheStrategy.NONE) //3
            .into(holder.imageViewParkItem)
    }



    override fun getItemCount() = tokyoParkData!!.size



    internal class ParkItemsHolder(v: View) : RecyclerView.ViewHolder(v){

        private var view: View = v

        var title: TextView = view.findViewById(R.id.textViewName)
        var desc: TextView = view.findViewById(R.id.textViewDesc)
        var imageViewParkItem: ImageView = view.findViewById(R.id.imageViewCity)

    }
}