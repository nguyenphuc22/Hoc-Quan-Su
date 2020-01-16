package com.caovr.phuc.hocquansu.model

class Setting {

    companion object
    {
        private var modeButton = true
        private var totalSentence:Int = 30
        private var sentenceCout = 0
        private var point = 0.0
        private val DATABASE_NAME = "data.db"
        private var topic = 0
        private var result = false
        private var time = 20
        private var mode = true
    }
    fun getMode():Boolean
    {
        return mode
    }
    fun setMode(check:Boolean)
    {
        mode = check
    }
    fun setTime(number: Int)
    {
        time = number
    }
    fun getTime():Int
    {
        return time
    }
    fun checkShowResult():Boolean
    {
        if (result == true)
        {
            return true
        }
        return false
    }
    fun offResult()
    {
        result = false
    }
    fun onResult()
    {
        result = true
    }
    fun checkLook():Boolean
    {
        if (modeButton == false)
            return true
        return false
    }
    fun lookupButton()
    {
        modeButton = false
    }
    fun unLookBotton()
    {
        modeButton = true
    }
    fun getTopic():Int
    {
        return topic
    }
    fun setTopic(number: Int)
    {
        topic = number
    }
    fun getSentenceCout():Int
    {
        return sentenceCout
    }

    fun addSentenceCout()
    {
        sentenceCout += 1
    }

    fun resetSentenceCout()
    {
        sentenceCout = 0
    }
    fun resetPoint()
    {
        point = 0.0
    }
    fun addPoint()
    {
        point += 10.0 / totalSentence
    }

    fun getPoint():Double
    {
        return point
    }

    fun getListSamllSentence():Int
    {
        var small = totalSentence *( (1.0 *40)/100 )
        return small.toInt()
    }

    fun getListBigSentence():Int
    {
        var big = totalSentence * ((1.0 * 60)/100)
        return big.toInt()
    }

    fun getTotalSentence():Int
    {
        return totalSentence!!
    }
    fun setTotalSentence(number:Int)
    {
        totalSentence = number
    }
    fun getDatabse_Name():String
    {
        return DATABASE_NAME
    }
    fun resetAll()
    {
        resetPoint()
        resetSentenceCout()
        unLookBotton()
        offResult()
    }
}