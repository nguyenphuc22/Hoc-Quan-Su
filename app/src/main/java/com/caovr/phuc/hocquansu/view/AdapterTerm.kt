package com.caovr.phuc.hocquansu.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caovr.phuc.hocquansu.R
import com.caovr.phuc.hocquansu.model.Setting
import com.caovr.phuc.hocquansu.model.Term
import com.caovr.phuc.hocquansu.view.MainActivity
import kotlinx.android.synthetic.main.item_menu.view.*

class AdapterTerm(val list:List<Term>,val activity: MainActivity): RecyclerView.Adapter<AdapterTerm.ViewHolder>() {

    val setting = Setting()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_menu,null))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img_hp.setImageResource(list.get(position).getImg())
        holder.txt_subtitle.text = list.get(position).getSubtitle()
        holder.txt_title.text = list.get(position).getTitle()
        holder.ln_hp.setOnClickListener {
            setting.setTopic(position + 1)
            setting.resetAll()
            activity.intent()
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val txt_title = view.txt_title_hp
        val txt_subtitle = view.txt_subtitle_hp
        val img_hp = view.img_hp
        val ln_hp = view.ln_hp
    }

}