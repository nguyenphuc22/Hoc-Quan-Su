package com.caovr.phuc.hocquansu.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface QuestionDao {
    @Query("SELECT * FROM Question WHERE (1.0 * Correct)/ Total * 100 < 50 AND Topic LIKE :topic")
    fun getSmall(topic:Int):List<Question>
    @Query("SELECT * FROM Question WHERE (1.0 * Correct)/ Total * 100 >= 50 AND Topic LIKE :toppic")
    fun getBig(toppic:Int):List<Question>
    @Query("SELECT * FROM Question")
    fun getALL():List<Question>
    @Query("UPDATE Question SET Total = :total , Correct = :correct WHERE Id = :id")
    fun updateTotalAndRight(total:Int,correct:Int,id:Int)
}