package com.caovr.phuc.hocquansu.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caovr.phuc.hocquansu.R
import com.caovr.phuc.hocquansu.model.Question
import com.caovr.phuc.hocquansu.model.Setting
import kotlinx.android.synthetic.main.row_list.view.*

class QuestionAdapter(
    val context: Context,
    val listQuestion: ArrayList<Question>,
    val anserList: Array<Int?>,
    val listActivity: ListActivity
)
    : androidx.recyclerview.widget.RecyclerView.Adapter<QuestionAdapter.ViewHolder>()
{
    val setting = Setting()
    val question = Question()
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.row_list,p0,false))
    }

    override fun getItemCount(): Int {
        return listQuestion.size
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder.txtTitle.text = "Câu ${p1 + 1}: " + listQuestion.get(p1).getTitle()
        holder.txtAnserA.text = listQuestion.get(p1).getAnserA()
        holder.txtAnserB.text = listQuestion.get(p1).getAnserB()
        holder.txtAnserC.text = listQuestion.get(p1).getAnserC()
        holder.txtAnserD.text = listQuestion.get(p1).getAnserD()
        if (setting.getMode() == false && setting.getTopic() != 4)
        {
            Exam(holder,anserList,listQuestion,p1,context,listActivity)
        }else
        {
            Test(holder,anserList,listQuestion,p1,context)
        }

    }

    class ViewHolder (view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view)
    {

        val txtTitle = view.txtTitle
        val txtAnserA = view.txt_AnserA
        val txtAnserB = view.txt_AnserB
        val txtAnserC = view.txt_AnserC
        val txtAnserD = view.txt_AnserD
        val btnAnserA = view.btn_AnserA
        val btnAnserB = view.btn_AnserB
        val btnAnserC = view.btn_AnserC
        val btnAnserD = view.btn_AnserD


    }
    fun drawColorQuestion(holder: ViewHolder, anserList: Array<Int?>, number:Int,context: Context,listQuestion: ArrayList<Question>)
    {
        if(anserList[number] == -1 )
        {
            textViewDefault(holder)
            buttonIsNotClick(holder)
        }
        else
        {
                buttonIsClick(holder,anserList[number]!!)
                if(anserList[number]?.let { question.checkQuestion(it) } == 1)
                {
                    when(listQuestion[number].getResult())
                    {
                        1->holder.txtAnserA.setTextColor(context.getColor(R.color.colorPrimary))
                        2->holder.txtAnserB.setTextColor(context.getColor(R.color.colorPrimary))
                        3->holder.txtAnserC.setTextColor(context.getColor(R.color.colorPrimary))
                        4->holder.txtAnserD.setTextColor(context.getColor(R.color.colorPrimary))
                    }
                }
                if(anserList[number]?.let { question.checkQuestion(it) } == 0)
                {
                    when(anserList[number])
                    {
                        1-> holder.txtAnserA.setTextColor(context.getColor(R.color.colorText_Wrong))
                        2-> holder.txtAnserB.setTextColor(context.getColor(R.color.colorText_Wrong))
                        3-> holder.txtAnserC.setTextColor(context.getColor(R.color.colorText_Wrong))
                        4-> holder.txtAnserD.setTextColor(context.getColor(R.color.colorText_Wrong))

                    }
                    when(listQuestion[number].getResult())
                    {
                        1-> holder.txtAnserA.setTextColor(context.getColor(R.color.colorPrimary))
                        2-> holder.txtAnserB.setTextColor(context.getColor(R.color.colorPrimary))
                        3-> holder.txtAnserC.setTextColor(context.getColor(R.color.colorPrimary))
                        4-> holder.txtAnserD.setTextColor(context.getColor(R.color.colorPrimary))
                    }

                }
        }

    }
    fun showResult(holder: ViewHolder, anserList: Array<Int?>, number:Int,context: Context,listQuestion: ArrayList<Question>)
    {
        if(anserList[number]?.let { question.checkQuestion(it) } == 0)
        {
            when(anserList[number])
            {
                1-> holder.txtAnserA.setTextColor(context.getColor(R.color.colorText_Wrong))
                2-> holder.txtAnserB.setTextColor(context.getColor(R.color.colorText_Wrong))
                3-> holder.txtAnserC.setTextColor(context.getColor(R.color.colorText_Wrong))
                4-> holder.txtAnserD.setTextColor(context.getColor(R.color.colorText_Wrong))

            }
            when(listQuestion[number].getResult())
            {
                1-> holder.txtAnserA.setTextColor(context.getColor(R.color.colorPrimary))
                2-> holder.txtAnserB.setTextColor(context.getColor(R.color.colorPrimary))
                3-> holder.txtAnserC.setTextColor(context.getColor(R.color.colorPrimary))
                4-> holder.txtAnserD.setTextColor(context.getColor(R.color.colorPrimary))
            }
        }
        else
        {
            when(listQuestion[number].getResult())
            {
                1->holder.txtAnserA.setTextColor(context.getColor(R.color.colorPrimary))
                2->holder.txtAnserB.setTextColor(context.getColor(R.color.colorPrimary))
                3->holder.txtAnserC.setTextColor(context.getColor(R.color.colorPrimary))
                4->holder.txtAnserD.setTextColor(context.getColor(R.color.colorPrimary))
            }
        }
    }

    fun Exam(
        holder: ViewHolder, anserList: Array<Int?>, listQuestion: ArrayList<Question>,
        number:Int,
        context: Context,
        listActivity: ListActivity)
    {

        drawColorQuestion(holder,anserList,number,context,listQuestion)
        holder.btnAnserA.setOnClickListener {
                setting.addSentenceCout()
                anserList[number] = 1
                addPoint(listQuestion, anserList, number)
                drawColorQuestion(holder, anserList, number, context, listQuestion)
                notClickButton(holder)
                if (listQuestion[number].checkQuestion(1) == 1)
                {
                    listActivity.setTextAndAddSentence()
                }
        }
        holder.btnAnserB.setOnClickListener {
                setting.addSentenceCout()
                anserList[number] = 2
                addPoint(listQuestion, anserList, number)
                drawColorQuestion(holder, anserList, number, context, listQuestion)
                notClickButton(holder)
                if (listQuestion[number].checkQuestion(2) == 1)
                {
                    listActivity.setTextAndAddSentence()
                }
        }
        holder.btnAnserC.setOnClickListener {
                setting.addSentenceCout()
                anserList[number] = 3
                addPoint(listQuestion, anserList, number)
                drawColorQuestion(holder, anserList, number, context, listQuestion)
                notClickButton(holder)
                if (listQuestion[number].checkQuestion(3) == 1)
                {
                    listActivity.setTextAndAddSentence()
                }
        }
        holder.btnAnserD.setOnClickListener {
                setting.addSentenceCout()
                anserList[number] = 4
                addPoint(listQuestion, anserList, number)
                drawColorQuestion(holder, anserList, number, context, listQuestion)
                notClickButton(holder)
                if (listQuestion[number].checkQuestion(4) == 1)
                {
                    listActivity.setTextAndAddSentence()
                }
        }
    }
    private fun notClickButton(holder: ViewHolder)
    {
        holder.btnAnserA.isClickable = false
        holder.btnAnserB.isClickable = false
        holder.btnAnserC.isClickable = false
        holder.btnAnserD.isClickable = false
    }

    fun changButton(holder: ViewHolder,option: Int)
    {
        buttonIsNotClick(holder)
        buttonIsClick(holder,option)
    }
    fun setAnserAndAddCount(anserList: Array<Int?>, number: Int, anser:Int)
    {
        if (anserList[number] == -1)
        {
            val setting = Setting()
            setting.addSentenceCout()
        }
        anserList[number] = anser
    }
    fun Test(
        holder: ViewHolder, anserList: Array<Int?>, listQuestion: ArrayList<Question>,
        number:Int,
        context: Context)
    {
        textViewDefault(holder)
        if (setting.checkShowResult() == true)
        {
            showResult(holder,anserList,number,context,listQuestion)
        }
        if (anserList[number] == -1)
        {
            buttonIsNotClick(holder)
        }
        if (setting.getTopic() != 4) {
            changButton(holder, anserList[number]!!)
            holder.btnAnserA.setOnClickListener {
                if (setting.checkLook() == false) {
                    setAnserAndAddCount(anserList, number, 1)
                    changButton(holder, anserList[number]!!)
                    addPoint(listQuestion, anserList, number)
                }
            }
            holder.btnAnserB.setOnClickListener {
                if (setting.checkLook() == false) {
                    setAnserAndAddCount(anserList, number, 2)
                    changButton(holder, anserList[number]!!)
                    addPoint(listQuestion, anserList, number)
                }
            }
            holder.btnAnserC.setOnClickListener {
                if (setting.checkLook() == false) {
                    setAnserAndAddCount(anserList, number, 3)
                    changButton(holder, anserList[number]!!)
                    addPoint(listQuestion, anserList, number)
                }
            }
            holder.btnAnserD.setOnClickListener {
                if (setting.checkLook() == false) {
                    setAnserAndAddCount(anserList, number, 4)
                    changButton(holder, anserList[number]!!)
                    addPoint(listQuestion, anserList, number)
                }
            }
        }
    }

    fun buttonIsNotClick(holder: ViewHolder)
    {
        holder.btnAnserA.setBackgroundResource(R.drawable.custom_button)
        holder.btnAnserB.setBackgroundResource(R.drawable.custom_button)
        holder.btnAnserC.setBackgroundResource(R.drawable.custom_button)
        holder.btnAnserD.setBackgroundResource(R.drawable.custom_button)
        holder.btnAnserA.setTextColor(context.getColor(R.color.colorBlack))
        holder.btnAnserB.setTextColor(context.getColor(R.color.colorBlack))
        holder.btnAnserC.setTextColor(context.getColor(R.color.colorBlack))
        holder.btnAnserD.setTextColor(context.getColor(R.color.colorBlack))

    }
    fun buttonIsClick(holder: ViewHolder,option:Int)
    {
        when(option)
        {
            1->{
                holder.btnAnserA.setBackgroundResource(R.drawable.custom_buttonisclick)
                holder.btnAnserA.setTextColor(context.getColor(R.color.colorBackgroundWhite))
            }
            2->{
                holder.btnAnserB.setBackgroundResource(R.drawable.custom_buttonisclick)
                holder.btnAnserB.setTextColor(context.getColor(R.color.colorBackgroundWhite))
            }
            3->{
                holder.btnAnserC.setBackgroundResource(R.drawable.custom_buttonisclick)
                holder.btnAnserC.setTextColor(context.getColor(R.color.colorBackgroundWhite))
            }
            4->{
                holder.btnAnserD.setBackgroundResource(R.drawable.custom_buttonisclick)
                holder.btnAnserD.setTextColor(context.getColor(R.color.colorBackgroundWhite))
            }
        }
    }
    fun textViewDefault(holder: ViewHolder)
    {
        holder.txtAnserA.setTextColor(context.getColor(R.color.colorBlack))
        holder.txtAnserB.setTextColor(context.getColor(R.color.colorBlack))
        holder.txtAnserC.setTextColor(context.getColor(R.color.colorBlack))
        holder.txtAnserD.setTextColor(context.getColor(R.color.colorBlack))
    }
    private fun addPoint(listQuestion: ArrayList<Question>, anserList: Array<Int?>, number: Int)
    {
        val setting = Setting()
        if (listQuestion[number].checkQuestion(anserList[number]!!) == 1)
        {
            listQuestion[number].addCorrect()
            setting.addPoint()
        }
    }
}