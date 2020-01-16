package com.caovr.phuc.hocquansu.model

import android.content.Context

class SharePreference(val context: Context) {
    private val NAME_PREF:String = "Data"
    val sharePref = context.getSharedPreferences(NAME_PREF,Context.MODE_PRIVATE)
    fun save(KEY_NAME:String,value:Int)
    {
        val editor = sharePref.edit()
        editor.putInt(KEY_NAME,value)
        editor.apply()
        editor.commit()
    }
    fun save(KEY_NAME:String,value:Boolean)
    {
      val editor = sharePref.edit()
        editor.putBoolean(KEY_NAME,value)
        editor.apply()
        editor.commit()
    }
    fun clean()
    {
        val editor = sharePref.edit()
        editor.clear()
        editor.commit()
    }
    fun get(KEY_NAME: String,defValue: Boolean):Boolean
    {
        return sharePref.getBoolean(KEY_NAME,defValue)
    }
    fun get(KEY_NAME: String,defValue: Int):Int
    {
        return sharePref.getInt(KEY_NAME,defValue)
    }
}