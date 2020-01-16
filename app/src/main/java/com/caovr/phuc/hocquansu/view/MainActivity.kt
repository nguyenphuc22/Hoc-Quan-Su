package com.caovr.phuc.hocquansu.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.caovr.phuc.hocquansu.R
import com.caovr.phuc.hocquansu.model.Setting
import com.caovr.phuc.hocquansu.model.SharePreference
import com.caovr.phuc.hocquansu.model.Term
import com.caovr.phuc.hocquansu.view.AdapterTerm
import com.caovr.phuc.hocquansu.view.ListActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_setting.*
import android.util.Pair as UtilPair



class MainActivity : AppCompatActivity(),View.OnClickListener {
    val setting  = Setting()
    val TOTAL:String = "Total"
    val CHECK_MODE:String = "Mode"
    val TIME:String = "Time"
    var sharePreference : SharePreference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharePreference = SharePreference(this@MainActivity)
        //check status bar .
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        var list = Term().makeListTerm()
        // load su kien
        setting.setMode(sharePreference!!.get(CHECK_MODE,setting.getMode()))
        setting.setTotalSentence(sharePreference!!.get(TOTAL,setting.getTotalSentence()))
        setting.setTime(sharePreference!!.get(TIME,setting.getTime()))
        //Chuoi su kien click
        img_setting.setOnClickListener(this)
        rv_menu.apply {
            layoutManager = GridLayoutManager(context,2) as RecyclerView.LayoutManager?
            adapter = AdapterTerm(list,this@MainActivity)

        }

    }
    fun intent()
    {
        val intent = Intent(this, ListActivity::class.java)

        startActivity(intent)
        finish()
    }

    override fun onClick(v: View?) {
        setting.resetPoint()
        setting.resetSentenceCout()
        setting.unLookBotton()
        setting.offResult()
        when(v?.id)
        {

            R.id.img_setting->{
                val bottomSheet = BottomSheetDialog(this)
                val sheetView = layoutInflater.inflate(R.layout.bottom_sheet_setting,null)
                bottomSheet.setContentView(sheetView)
                bottomSheet.nb_question.minValue = 1
                // the size max question
                bottomSheet.nb_question.maxValue = 100
                bottomSheet.nb_question.value = setting.getTotalSentence()

                bottomSheet.nb_question.setOnValueChangedListener { picker, oldVal, newVal ->
                    setting.setTotalSentence(newVal - 1)
                    sharePreference!!.save(TOTAL,setting.getTotalSentence())

                }

                bottomSheet.nb_time.minValue = 1
                // the size max question
                bottomSheet.nb_time.maxValue = 100
                bottomSheet.nb_time.value = setting.getTime()

                bottomSheet.nb_time.setOnValueChangedListener { picker, oldVal, newVal ->
                    setting.setTime(newVal - 1)
                    sharePreference!!.save(TIME,setting.getTime())
                }
                bottomSheet.btn_switch.isChecked = setting.getMode()
                bottomSheet.btn_switch.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked)
                    {
                        setting.setMode(true)
                    }else
                    {
                        setting.setMode(false)
                    }
                    sharePreference!!.save(CHECK_MODE,setting.getMode())
                }
                bottomSheet.show()
            }
        }
    }

}
