package com.xhateya.idn.dzikirapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.xhateya.idn.dzikirapp.model.Artikel
import com.xhateya.idn.dzikirapp.ui.QauliyahActivity

class MainActivity : AppCompatActivity() {
    private lateinit var llDzikirDoaHarian: LinearLayout
    private lateinit var llDzikirDoaShalat : LinearLayout
    private lateinit var llDzikirSetiapSaat : LinearLayout
    private lateinit var llDzikirPagiPetang : LinearLayout
    private lateinit var llDotSlider :LinearLayout
    private lateinit var vpArtikel :ViewPager2

    private var dotsCount = 0
    private var artikelData : ArrayList<Artikel> = arrayListOf()
    private lateinit var dotSlider : Array<ImageView?>

    private val slidingCallBack = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            for (dot in 0 until dotsCount){
                dotSlider[dot]?.setImageDrawable(
                    ContextCompat.getDrawable(
                    applicationContext, R.drawable.non_active_dot
                ))
            }
            dotSlider[position]?.setImageDrawable(
                ContextCompat.getDrawable(applicationContext, R.drawable.active_dot)
            )
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        initView()
        initData()
        setUpViewPager()

    }

    private fun setUpViewPager() {
        val artikelAdapter= ArtikelAdapter(artikelData)
        artikelAdapter.setOnItemClickCallback(object : OnItemClickCallback{
            override fun onItemClicked(data: Artikel) {

            }
        })
        vpArtikel.apply {
            adapter=artikelAdapter
            registerOnPageChangeCallback(slidingCallBack)

        }
        dotsCount=artikelData.size
        dotSlider= arrayOfNulls(dotsCount)

        for (i in 0 until dotsCount){
            dotSlider[i]= ImageView(this)
            dotSlider[i]?.setImageDrawable(
                ContextCompat.getDrawable(applicationContext, R.drawable.non_active_dot)
            )
            val params =LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8,0,8,0)
            llDotSlider.addView(dotSlider[i], params)
        }
        dotSlider[0]?.setImageDrawable(
            ContextCompat.getDrawable(applicationContext, R.drawable.active_dot)
        )
        
    }

    override fun onDestroy() {
        super.onDestroy()
        vpArtikel.unregisterOnPageChangeCallback(slidingCallBack)
    }

    private fun initData() {
        val image = resources.obtainTypedArray(R.array.img_artikel)
        val title = resources.getStringArray(R.array.title_artikel)
        val desc = resources.getStringArray(R.array.desc_artikel)
        artikelData.clear()
        for (data in title.indices){
            artikelData.add(
                Artikel(
                image.getResourceId(data, 0),
                    title[data],
                    desc[data]
            )
            )
        }
        image.recycle()

    }

    private fun initView() {
        llDzikirDoaShalat = findViewById(R.id.llDzikirDoaShalat)
        llDzikirDoaShalat.setOnClickListener {
            startActivity(Intent(this, QauliyahActivity::class.java))
        }

        llDzikirDoaHarian = findViewById(R.id.llDzikirDoaHarian)
        llDzikirDoaHarian.setOnClickListener {  }

        llDzikirSetiapSaat = findViewById(R.id.llDzikirSetiapSaat)
        llDzikirSetiapSaat.setOnClickListener {  }

        llDzikirPagiPetang = findViewById(R.id.llDzikirPagiPetang)
        llDzikirPagiPetang.setOnClickListener {  }

        llDotSlider = findViewById(R.id.llSliderDot)
        vpArtikel = findViewById(R.id.vpArtikel)

    }
}