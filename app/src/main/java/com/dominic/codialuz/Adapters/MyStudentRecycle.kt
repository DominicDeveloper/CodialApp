package com.dominic.codialuz.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dominic.codialuz.R
import com.dominic.codialuz.Users.Student
import kotlinx.android.synthetic.main.studnent_list.view.*

class MyStudentRecycle(val context: Context, val list:ArrayList<Student>, val rvClick: RvClick) : RecyclerView.Adapter<MyStudentRecycle.VH>() {

        inner class VH(var itemRv: View): RecyclerView.ViewHolder(itemRv){
            @SuppressLint("SetTextI18n")
            fun onBind(student: Student){

                itemRv.liststudent_name.text = "${student.student_surname}  ${student.student_name}"
                itemRv.liststudent_date.text = student.student_date
                itemRv.liststudent_delete.setOnClickListener {
                    rvClick.onClickDelete(student,itemRv.findViewById(R.id.liststudent_delete))
                }
                itemRv.liststudent_edit.setOnClickListener {
                    rvClick.onClickEdit(student,itemRv.findViewById(R.id.liststudent_edit))
                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            return  VH(LayoutInflater.from(parent.context).inflate(R.layout.studnent_list,parent,false))
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
            fun onClickDelete(student: Student, cardView: CardView)
            fun onClickEdit(student: Student, cardView: CardView)

        }


    }