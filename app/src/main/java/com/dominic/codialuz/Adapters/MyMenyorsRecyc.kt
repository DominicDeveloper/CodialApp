package com.dominic.codialuz.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dominic.codialuz.R
import com.dominic.codialuz.Users.Mentor
import kotlinx.android.synthetic.main.list_mentors.view.*

class MyMentorsRecyc(val context: Context, val list:List<Mentor>, val rvClick: RvClick) : RecyclerView.Adapter<MyMentorsRecyc.VH>() {

    inner class VH(var itemRv: View): RecyclerView.ViewHolder(itemRv){
        @SuppressLint("SetTextI18n")
        fun onBind(mentor: Mentor){


            itemRv.listmentor_name.text = "${mentor.Name} ${mentor.Surname}"
            itemRv.listmentor_edit.setOnClickListener {
                rvClick.onClick(mentor,itemRv.findViewById(R.id.listmentor_edit))

            }
            itemRv.listmentor_delete.setOnClickListener {
                rvClick.onClickDelete(mentor,itemRv.findViewById(R.id.listmentor_delete))
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return  VH(LayoutInflater.from(parent.context).inflate(R.layout.list_mentors,parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }
    fun notif(position: Int)
    {
        this.notifyItemInserted(position)
    }
    interface RvClick{
        fun onClick(mentor: Mentor, cardView: CardView)
        fun onClickDelete(mentor: Mentor,cardView: CardView)

    }

}