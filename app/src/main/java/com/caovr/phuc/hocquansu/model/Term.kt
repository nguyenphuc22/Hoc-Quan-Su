package com.caovr.phuc.hocquansu.model

import com.caovr.phuc.hocquansu.R

class Term(    private var title:String? = "title",
               private var subtitle:String? = "subtitle",
               private var img:Int? = 0
) {
     fun getImg():Int
    {
        return this.img!!
    }
    fun getTitle(): String?
    {
        return this.title
    }
    fun getSubtitle():String?
    {
        return this.subtitle
    }
    fun setImg(int: Int)
    {
        this.img = int
    }
    fun setTitle(string:String)
    {
        this.title = string
    }
    fun setSubtitle(string: String)
    {
        this.subtitle = string
    }
    fun makeListTerm():List<Term>
    {
        var list= ArrayList<Term>()

        list.add(Term("Học Phần 1","Đường lối quốc phòng an ninh đảng",R.drawable.hp1))
        list.add(Term("Học Phần 2","Công tác quốc phòng và an ninh",R.drawable.hp2))
        list.add(Term("Học Phần 3","Vũ khí quân sự chiến thuật",R.drawable.hp3))
        list.add(Term("Tài Liệu","Đáp án tất cả các câu",R.drawable.data))
        return list
    }



}