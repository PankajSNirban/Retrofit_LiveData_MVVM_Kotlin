package com.wealthpark.tokyogallery.pankajIn.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.wealthpark.tokyogallery.pankajIn.R
import kotlinx.android.synthetic.main.activity_city.*


class CityActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        setUpToolBar()

        if (savedInstanceState == null)
        {
            textViewName.text=intent.extras?.getString("city_name")
            textViewDesc.text=intent.extras?.getString("city_desc")
            Glide.with(this@CityActivity)
                .load(intent.extras?.getString("city_image"))
                .error(R.drawable.ic_launcher_background)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageViewCity)
        } else {
            savedInstanceState.getSerializable("city_name") as String
        }
    }


    private fun setUpToolBar() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setSupportActionBar(toolbar)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(false)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.back_arrow_white_icon)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> this.finish()
        }
        return true
    }
}