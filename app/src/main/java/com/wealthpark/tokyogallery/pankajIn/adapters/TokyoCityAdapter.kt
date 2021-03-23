package com.wealthpark.tokyogallery.pankajIn.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.wealthpark.tokyogallery.pankajIn.models.CityList
import com.wealthpark.tokyogallery.pankajIn.interfaces.OnItemClickListener
import com.wealthpark.tokyogallery.pankajIn.R


internal class TokyoCityAdapter(
    context: Context, parkCityList: List<CityList?>?, listener: OnItemClickListener
)  : RecyclerView.Adapter<TokyoCityAdapter.ParkItemsHolder>()  {

    private val tokyoCityData: List<CityList?>?=parkCityList
    private val context:Context=context
    private val listener: OnItemClickListener? = listener

    override fun onCreateViewHolder(parent: ViewGroup, itemView: Int): ParkItemsHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.tokyo_park_items, parent, false)
        return ParkItemsHolder(itemView)
    }

    override fun onBindViewHolder(holder: ParkItemsHolder, position: Int) {

        val tokyoParkData = tokyoCityData?.get(position)
        holder.title.text = tokyoParkData?.name
        holder.desc.text = tokyoParkData?.description

        Glide.with(context)
            .load(tokyoParkData?.image)
            .error(R.drawable.ic_launcher_background)
            .skipMemoryCache(true) //2
            .diskCacheStrategy(DiskCacheStrategy.NONE) //3
            .into(holder.imageViewParkItem)

        holder.cardCity.setOnClickListener(View.OnClickListener {
            listener?.onItemClick(tokyoParkData)
        })

    }



    override fun getItemCount() = tokyoCityData!!.size



    internal class ParkItemsHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v

        var title: TextView = view.findViewById(R.id.textViewName)
        var desc: TextView = view.findViewById(R.id.textViewDesc)
        var cardCity: CardView = view.findViewById(R.id.card_city)
        var imageViewParkItem: ImageView = view.findViewById(R.id.imageViewCity)


    }

}