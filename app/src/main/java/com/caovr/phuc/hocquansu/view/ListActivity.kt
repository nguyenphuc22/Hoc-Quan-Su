package com.caovr.phuc.hocquansu.view

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.caovr.phuc.hocquansu.R
import com.caovr.phuc.hocquansu.model.Question
import com.caovr.phuc.hocquansu.model.Setting
import com.caovr.phuc.hocquansu.viewmodel.DocumentViewmodel
import com.caovr.phuc.hocquansu.view.MainActivity
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.dialog_result.*
import java.util.concurrent.TimeUnit

class ListActivity : AppCompatActivity() {
    val setting = Setting()
    val documentViewmodel = DocumentViewmodel()
    var check = false
    val DATABASE_NAME:String = setting.getDatabse_Name()
    var list: ArrayList<Question>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        img_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        setImgTitle()

        // tao data
        list = makeData()
        val anserList = makeFakeData(list!!.size)

        txt_titleList.setText("Học Phần ${setting.getTopic()}")
        var millisInfuture:Long =  1000 * 60 * setting.getTime().toLong()
        val timer = Mycouter(millisInfuture,1000)

        if (setting.getTopic() != 4)
            rv.addOnScrollListener(object : RecyclerView.OnScrollListener()
            {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0)
                    {
                        fab.hide()
                    }else
                    {
                        fab.show()
                    }
                }
            })
        else
        {
            fab.hide()
            txt_subtitleList.text = "Tất cả các đáp án"
            setting.onResult()
        }
        rv.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context) as RecyclerView.LayoutManager?
            adapter = QuestionAdapter(context, list!!, anserList,this@ListActivity)
        }

        clicFab(this,rv.adapter as QuestionAdapter,anserList,timer)
    }

    private fun makeData():ArrayList<Question>
    {

        val list = documentViewmodel.getDocumentExam(this,setting.getDatabse_Name(),setting.getTopic())
        return list
    }

    private fun makeFakeData(number:Int): Array<Int?>
    {

        val anserList = arrayOfNulls<Int>(number)
        var i = 0
        while (i < number)
        {
            anserList[i] = -1
            i++
        }
        return anserList
    }


    private fun clicFab(
        context: Context,
        adapter: QuestionAdapter,
        anserList: Array<Int?>,
        timer: Mycouter
    ) {
        if (setting.getMode() == false && setting.getTopic() != 4)
        {
            check = true
            fab.setImageResource(R.drawable.ic_create_black_24dp)
            setTextAndAddSentence()
            fab.hide()
            fab.show()
        }
        else
        {
            if (setting.getTopic() != 4)
            timer.start()
        }
        fab.setOnClickListener {
            if (check == true)
            {
                if (setting.getMode())
                {
                    check = false
                    fab.setImageResource(R.drawable.ic_check_black_24dp)
                    fab.hide()
                    fab.show()
                }
                setNewExam(adapter,anserList)
            }else
            {
                showResult(adapter,context,timer,anserList)
            }


        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun setTextAndAddSentence()
    {
        val setting = Setting()
        txt_subtitleList.setText("Tổng câu: ${setting.getSentenceCout()}/${setting.getTotalSentence()}")
    }
    fun resultDialog(dialog: Dialog)
    {
        if (setting.getPoint() >= 8)
        {
            dialog.lottieAnimationView.setAnimation("good.json")
            dialog.txt_TitleDialog.setText(R.string.title_good)
            dialog.txt_Message.setText(R.string.body_good)
            dialog.txt_Point.setText("Số điểm của bạn là: ${setting.getPoint()}")
        }
        if (setting.getPoint() >= 5 && setting.getPoint() < 8)
        {
            dialog.lottieAnimationView.setAnimation("medium.json")
            dialog.txt_TitleDialog.setText(R.string.title_medium)
            dialog.txt_Message.setText(R.string.body_medium)
            dialog.txt_Point.setText("Số điểm của bạn là: ${setting.getPoint()}")

        }
        if (setting.getPoint() < 5)
        {
            dialog.lottieAnimationView.setAnimation("fail.json")
            dialog.txt_TitleDialog.setText(R.string.title_fail)
            dialog.txt_Message.setText(R.string.body_fail)
            dialog.txt_Point.setText("Số điểm của bạn là: ${setting.getPoint()}")

        }

    }
    inner class Mycouter(millisInfuture: Long, countInterval: Long) : CountDownTimer(millisInfuture,countInterval)
    {
        override fun onFinish() {
            txt_subtitleList.setText("Thời Gian: 00:00")
            Toast.makeText(this@ListActivity,R.string.end_time,Toast.LENGTH_LONG).show()
            setting.lookupButton()
        }

        override fun onTick(millisUntilFinished: Long) {
            var tmpmillisUntilFinished = millisUntilFinished
            val minutes = TimeUnit.MILLISECONDS.toMinutes(tmpmillisUntilFinished)
            tmpmillisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes)

            val seconds = TimeUnit.MILLISECONDS.toSeconds(tmpmillisUntilFinished)

            txt_subtitleList.setText("Thời Gian: ${minutes}:${seconds}")
        }
    }
    private fun setNewExam(adapter: QuestionAdapter,anserList: Array<Int?>)
    {
        list!!.clear()
        fab.hide()
        fab.show()
        val tmplist = documentViewmodel.getDocumentExam(this,DATABASE_NAME,setting.getTopic())
        list!!.addAll(tmplist)
        setting.resetAll()
        var count = 0
        while (count < setting.getTotalSentence())
        {
            anserList[count] = -1
            count++
        }
        adapter.notifyDataSetChanged()

    }
    private fun showResult(adapter: QuestionAdapter,context: Context,timer: Mycouter,anserList: Array<Int?>)
    {
        setting.lookupButton()
        timer.cancel()
        var i = 0
        while(i < setting.getTotalSentence())
        {
            this!!.list!![i]?.addTotal()
            i++
        }
        documentViewmodel.updatePercent(context,DATABASE_NAME, this!!.list!!)
        val dialog = Dialog(context)
        val view = layoutInflater.inflate(R.layout.dialog_result,null)
        dialog.setContentView(view)
        dialog.create()
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        resultDialog(dialog)

        dialog.show()
        dialog.txt_Result.setOnClickListener {
            dialog.dismiss()
            setting.onResult()
            adapter.notifyDataSetChanged()
            check = true
            fab.setImageResource(R.drawable.ic_create_black_24dp)
            fab.hide()
            fab.show()
        }
        dialog.txt_NewExam.setOnClickListener {
            dialog.dismiss()
            list!!.clear()
            val tmplist = documentViewmodel.getDocumentExam(this,DATABASE_NAME,setting.getTopic())
            list!!.addAll(tmplist)
            setting.resetAll()
            var count = 0
            while (count < setting.getTotalSentence())
            {
                anserList[count] = -1
                count++
            }
            adapter.notifyDataSetChanged()
            setTextAndAddSentence()
        }
    }
    private fun showAnser()
    {

    }
    fun setImgTitle()
    {
        when(setting.getTopic())
        {
            1->img_title.setImageResource(R.drawable.hp1)
            2->img_title.setImageResource(R.drawable.hp2)
            3->img_title.setImageResource(R.drawable.hp3)
            4->img_title.setImageResource(R.drawable.data)
        }
    }
}
