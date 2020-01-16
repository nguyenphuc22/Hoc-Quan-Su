package com.caovr.phuc.hocquansu.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Question")
data class Question(
    @PrimaryKey
    @ColumnInfo(name = "Id") private var id:Int? = 0,
    @ColumnInfo(name = "Title") private var title:String? = null,
    @ColumnInfo(name = "Ansera") private var anserA:String? = null,
    @ColumnInfo(name = "Anserb") private var anserB:String? = null,
    @ColumnInfo(name = "Anserc") private var anserC:String? = null,
    @ColumnInfo(name = "Anserd") private var anserD:String? = null,
    @ColumnInfo(name = "Result") private var result:Int? = 0,
    @ColumnInfo(name = "Total") private var total:Int? = 1,
    @ColumnInfo(name = "Correct") private var Correct:Int? = 1,
    @ColumnInfo(name = "Topic")  private  var topic:Int? = 0
) {
    fun getId(): Int?
    {

        return this.id
    }
    fun getTitle():String
    {
        return this.title.toString()
    }
    fun getAnserA():String
    {
        return this.anserA.toString()
    }
    fun getAnserB():String
    {
        return this.anserB.toString()
    }
    fun getAnserC():String
    {
        return this.anserC.toString()
    }
    fun getAnserD():String
    {
        return this.anserD.toString()
    }
    fun getResult(): Int?
    {
        return this.result?.toInt()
    }

    fun getCorrect(): Int?
    {
        return this.Correct?.toInt()
    }
    fun getTotal(): Int?
    {
        return this.total?.toInt()
    }
    fun setId(id:Int?)
    {
        this.id = id!!
    }
    fun setTitle(title:String?)
    {
        this.title = title
    }
    fun setAnserA(AnserA:String?)
    {
        this.anserA = AnserA
    }
    fun setAnserB(AnserB:String?)
    {
        this.anserB = AnserB
    }
    fun setAnserC(AnserC:String?)
    {
        this.anserC = AnserC
    }
    fun setAnserD(AnserD:String?)
    {
        this.anserD = AnserD
    }
    fun setResult(Result:Int?)
    {
        this.result = Result
    }
    // Neu no null auto chuyen thanh -1 nen gio phai chuyen thanh 1
    fun setCorrect(Turn:Int?)
    {
        if (Turn != -1)
        this.Correct = Turn
        else
            this.Correct = 1
    }
    // Neu no null auto chuyen thanh -1 nen gio phai chuyen thanh 1
    fun setTotal(Turn:Int?)
    {
        if(Turn != -1)
            this.total = Turn
        else
            this.total = 1
    }

    fun checkQuestion(AnserUser:Int): Int
    {
        if (AnserUser == this.getResult())
            return 1
        else
            return 0
    }
    fun getTopic():Int?
    {
        return this.topic!!
    }
    fun setToppic(number:Int)
    {
        this.topic = number
    }
    fun addCorrect()
    {
        this.Correct = this.Correct?.plus(1)
    }
    fun addTotal()
    {
        this.total = this.total?.plus(1)
    }
}