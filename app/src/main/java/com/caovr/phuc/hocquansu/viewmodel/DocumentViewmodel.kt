package com.caovr.phuc.hocquansu.viewmodel

import androidx.room.Room
import android.content.Context
import com.caovr.phuc.hocquansu.model.Appdatabase
import com.caovr.phuc.hocquansu.model.Question
import com.caovr.phuc.hocquansu.model.Setting
import java.util.ArrayList

class DocumentViewmodel {
    fun getDocumentExam(context: Context,DATABASE_NAME:String,toppic:Int): ArrayList<Question>
    {
        // Su dung Room
      val db = Room.databaseBuilder(context,Appdatabase::class.java,DATABASE_NAME)
          .allowMainThreadQueries()
          .addMigrations(Appdatabase.MIGRATION_1_2)
          .build()
        if (toppic == 4)
        {
            val list = db.questionDao().getALL()
            return list as ArrayList<Question>
        }
        //Tach file ra phan dung cao va phan dung thap
        val listSmall = db.questionDao().getSmall(toppic)
        val listBig = db.questionDao().getBig(toppic)
        val setting = Setting()

        //Kiem tra xem nguoi dung co chon so cau lon hon voi tong so cau trong kho
      if( (listBig.size + listSmall.size) < setting.getTotalSentence() )
      {
          var result = setting.getTotalSentence()
          result -= Math.abs((listBig.size + listSmall.size) - setting.getTotalSentence())
          setting.setTotalSentence(result)
      }
        //Tinh phan chia so cau  trong tong so cua con nguoi dung tron ti le 40/60
      var bigExam = setting.getListBigSentence()
      var smallExam = setting.getListSamllSentence()

        //Kiem tra xem trong khi tach co loi xay ra khong de chinh lai
        if( (bigExam + smallExam) != setting.getTotalSentence() )
      {
          smallExam +=  Math.abs((bigExam + smallExam) - setting.getTotalSentence())
      }
        //Kiem tra kho cau lam dung nhieu co du de moc ra khong
      if ( listBig.size < bigExam)
      {
          var minus = bigExam - listBig.size
          bigExam -= minus
          smallExam += minus
      }
        //Kiem tra xem kho cau tra loi dung it co du de moc ra khong
      if ( listSmall.size < smallExam)
      {
          var minus = smallExam - listSmall.size
          smallExam -= minus
          bigExam += minus
      }

        var result:Question
        var check:Boolean
        val list = ArrayList<Question>()
        var i = 0
        //Random ngau nhien va add vao
        while( i < setting.getTotalSentence() )
        {
            check = false
            if ( i < smallExam )
            {
                result = listSmall[(0..listSmall.size - 1).random()]
                for (question in list)
                {
                    if (question.getId() == result.getId())
                          check = true
                }
                if (check == false)
                {
                    list.add(result)
                    i++
                }

            }


            else
            {
                result = listBig[(0..listBig.size - 1).random()]
                for (question in list)
                {
                    if (question.getId() == result.getId())
                        check = true
                }
                if (check == false)
                {
                    list.add(result)
                    i++
                }
            }

        }

        return list
    }
    fun updatePercent(context: Context,DATABASE_NAME: String,list:ArrayList<Question>)
    {
        val db = Room.databaseBuilder(context,Appdatabase::class.java,DATABASE_NAME)
            .allowMainThreadQueries()
            .addMigrations(Appdatabase.MIGRATION_1_2)
            .build()
        for (question in list)
        {
            question.getId()?.let {
                db.questionDao().updateTotalAndRight(
                    question.getTotal()!!, question.getCorrect()!!,
                    it
                )
            }
        }
    }
}


