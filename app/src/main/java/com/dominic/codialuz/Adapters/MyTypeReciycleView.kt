package com.dominic.codialuz.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.dominic.codialuz.R
import com.dominic.codialuz.Users.Person
import kotlinx.android.synthetic.main.list_types.view.*

class MyTypeReciycleView(val context: Context, val list:List<Person>,val rvClick: RvClick) : RecyclerView.Adapter<MyTypeReciycleView.VH>() {


        inner class VH(var itemRv: View): RecyclerView.ViewHolder(itemRv){
            fun onBind(person: Person){
                itemRv.item_list_items.text = person.curs_name
                itemRv.itemtypes_imageAbout.setOnClickListener {
                   rvClick.onClick(person,itemRv.findViewById(R.id.itemtypes_imageAbout))
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            return  VH(LayoutInflater.from(parent.context).inflate(R.layout.list_types,parent,false))
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
           fun onClick(person: Person,imageView: ImageView)

        }
}
