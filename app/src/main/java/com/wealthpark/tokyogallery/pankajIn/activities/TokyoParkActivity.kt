package com.wealthpark.tokyogallery.pankajIn.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wealthpark.tokyogallery.pankajIn.*
import com.wealthpark.tokyogallery.pankajIn.adapters.TokyoCityAdapter
import com.wealthpark.tokyogallery.pankajIn.adapters.TokyoFoodAdapter
import com.wealthpark.tokyogallery.pankajIn.interfaces.OnItemClickListener
import com.wealthpark.tokyogallery.pankajIn.models.CityList
import com.wealthpark.tokyogallery.pankajIn.viewmodel.TokyoParkViewModel
import kotlinx.android.synthetic.main.activity_main.*


class TokyoParkActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var tokyoParkViewModel: TokyoParkViewModel
    private lateinit var linearLayoutManagerCity: LinearLayoutManager
    private lateinit var linearLayoutManagerFood: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        tokyoParkViewModel = ViewModelProviders.of(this).get(TokyoParkViewModel::class.java)

        setUpAdapters()

        registerObserver()

        if (checkInternetConnection(this)) tokyoParkViewModel.getCitiesAndFoodData() else showErrorDialog(getString(R.string.checkInternetConnection))

    }

    private fun setUpAdapters() {
        linearLayoutManagerCity = LinearLayoutManager(this)
        linearLayoutManagerFood = LinearLayoutManager(this)
        rv_tokyo_city_list.layoutManager = linearLayoutManagerCity
        rv_tokyo_food_list.layoutManager = linearLayoutManagerFood
    }

    private fun registerObserver() {

        tokyoParkViewModel.loaderLiveData.observe(this, Observer {
            if (it!!) llProgressBar.visibility = View.VISIBLE else llProgressBar.visibility =
                View.GONE

        })

        tokyoParkViewModel.errorLiveData.observe(this, Observer {
            showErrorDialog(it.toString())
        })


        tokyoParkViewModel.tokyoParkGalleryData.observe(this, Observer {
            tv_header_city.visibility=View.VISIBLE
            tv_header_food.visibility=View.VISIBLE
            rv_tokyo_city_list.adapter = TokyoCityAdapter(this, it.getCities(),this)
            rv_tokyo_food_list.adapter = TokyoFoodAdapter(this, it.getFoods())
        })


    }


    private fun showErrorDialog(title: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_layout)
        val body = dialog.findViewById(R.id.tvTitle) as TextView
        body.text = title
        val yesBtn = dialog.findViewById(R.id.btn_ok) as Button
        yesBtn.setOnClickListener {
            dialog.dismiss()
            finish()
        }
        dialog.show()

    }

    override fun onResume() {
        super.onResume()

        if (!checkInternetConnection(this))showErrorDialog(getString(R.string.checkInternetConnection))

    }

    private fun checkInternetConnection(ctx: Context): Boolean {
        val conMgr = ctx.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return conMgr.activeNetworkInfo != null && conMgr.activeNetworkInfo.isAvailable && conMgr.activeNetworkInfo.isConnected
    }

    override fun onItemClick(cityList: CityList?) {
        val intent = Intent(this, CityActivity::class.java)
        intent.putExtra("city_name", cityList?.name)
        intent.putExtra("city_desc", cityList?.description)
        intent.putExtra("city_image",  cityList?.image)
        this.startActivity(intent)
    }

}