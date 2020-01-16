package com.caovr.phuc.hocquansu.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.caovr.phuc.hocquansu.model.Setting
import com.caovr.phuc.hocquansu.viewmodel.coppyData
import com.google.android.gms.ads.MobileAds

class Splash_creenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this, "ca-app-pub-5262778888403948~9914363984")
        val setting = Setting()
        loadDataFromAsset(this,setting.getDatabse_Name())
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun loadDataFromAsset(context: Context, DATABASE_NAME:String)
    {
        val coppyData = coppyData()
        coppyData.openDatabase(this, DATABASE_NAME)
    }

}