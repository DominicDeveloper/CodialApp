package com.dominic.codialuz.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dominic.codialuz.R
import com.dominic.codialuz.Users.Group
import com.dominic.codialuz.Users.Person
import com.dominic.codialuz.Users.Student
import kotlinx.android.synthetic.main.item_group_list.view.*
import kotlinx.android.synthetic.main.list_types.view.*

class MyGroupAdapter(val context: Context, val list:ArrayList<Group>, val rvClick: RvClick) : RecyclerView.Adapter<MyGroupAdapter.VH>() {

    inner class VH(var itemRv: View): RecyclerView.ViewHolder(itemRv){
        fun onBind(group: Group){
            itemRv.itemgroup_gruruhni_nomi.text = group.group_name.toString()
            itemRv.itemgroup_delete.setOnClickListener {
                rvClick.onClickDelete(group,itemRv.findViewById(R.id.itemgroup_delete))
            }
            itemRv.itemgroup_edit.setOnClickListener {
                rvClick.onClickEdit(group,itemRv.findViewById(R.id.itemgroup_edit))
            }
            itemRv.itemgroup_view.setOnClickListener {
                rvClick.onClickView(group,itemRv.findViewById(R.id.itemgroup_view))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return  VH(LayoutInflater.from(parent.context).inflate(R.layout.item_group_list,parent,false))
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
        fun onClickDelete(group: Group,cardView:CardView)
        fun onClickEdit(group: Group,cardView: CardView)
        fun onClickView(group: Group,cardView: CardView)

    }


}