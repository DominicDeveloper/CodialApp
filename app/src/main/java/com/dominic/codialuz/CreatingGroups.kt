package com.dominic.codialuz

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.style.UpdateAppearance
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.dominic.codialuz.Adapters.MyGroupAdapter
import com.dominic.codialuz.Constant.Hold
import com.dominic.codialuz.DataBase.MyDbMentor
import com.dominic.codialuz.Users.Group
import com.dominic.codialuz.Users.Mentor
import com.dominic.codialuz.Users.Student
import kotlinx.android.synthetic.main.edit_group.view.*
import kotlinx.android.synthetic.main.fragment_creating_groups.view.*
import kotlinx.android.synthetic.main.item_delete_group.view.*
import kotlinx.android.synthetic.main.item_group_list.view.*

class CreatingGroups : Fragment() {
    lateinit var root:View
    lateinit var groupList:ArrayList<Group>
    lateinit var myGroupAdapter: MyGroupAdapter
    lateinit var myDbMentor: MyDbMentor
    lateinit var studentsnumber:ArrayList<Student>
    var lesson_time = arrayListOf("10:00 - 12:00","12:00 - 14:00","14:00 - 16:00","16:00 - 18:00","18:00 - 20:00")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_creating_groups, container, false)
        groupList = ArrayList()
        studentsnumber = ArrayList()
        myDbMentor = MyDbMentor(requireContext())
        sync()

        return root
    }
    @SuppressLint("SetTextI18n")
    fun Update(){
            myGroupAdapter = MyGroupAdapter(requireContext(),groupList,object : MyGroupAdapter.RvClick{
                override fun onClickDelete(group: Group, cardView: CardView) {
                   val askDel = AlertDialog.Builder(requireContext()).create()
                    val askView= LayoutInflater.from(requireContext()).inflate(R.layout.item_delete_group,null,false)
                    askDel.setView(askView)
                    askDel.show()
                    askView.text_yes_del_group.setOnClickListener {
                        myDbMentor.deleteOnlyGroup(group)
                        groupList.remove(group)
                        Toast.makeText(requireContext(), "O`chirildi", Toast.LENGTH_SHORT).show()
                        askDel.dismiss()
                        Update()
                    }
                    askView.text_no_del_group.setOnClickListener {
                        askDel.cancel()
                    }
                }

                override fun onClickEdit(group: Group, cardView: CardView) {
                    val alertEdit = AlertDialog.Builder(requireContext()).create()
                    val setV = LayoutInflater.from(requireContext()).inflate(R.layout.edit_group,null,false)
                    alertEdit.setView(setV)
                    alertEdit.show()
                    setV.item_group_name.setText(group.group_name)
                    setV.item_group_nentor.setText(group.group_mentor?.Name)
                    setV.item_group_time.setText(group.group_time)
                    val getMentor:ArrayList<Group> = myDbMentor.getAllGroup()
                    val adapterlist = ArrayList<String>()
                    getMentor.forEach {
                        if (it.group_mentor?.id == group.group_mentor?.id){

                            adapterlist.add(it.group_mentor?.Name.toString())
                        }
                    }
                    val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,adapterlist)
                    val adapter_time = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,lesson_time)
                    setV.item_group_nentor.setAdapter(adapter)
                    setV.item_group_time.setAdapter(adapter_time)
                    setV.item_group_add.setOnClickListener {
                        group.group_name = setV.item_group_name.text.toString()
                        group.group_mentor?.Name = setV.item_group_nentor.text.toString().trim()
                        group.group_time = setV.item_group_time.text.toString()
                        myDbMentor.editGroup(group)
                        Hold.group = group
                        alertEdit.dismiss()
                        Toast.makeText(requireContext(), "O`zgartirildi", Toast.LENGTH_SHORT).show()
                        Update()
                    }
                    setV.item_group_cancel.setOnClickListener {
                        alertEdit.cancel()
                    }
                }

                override fun onClickView(group: Group, cardView: CardView) {
                    Hold.group  = group
                    findNavController().navigate(R.id.finallyFragment)

                }
            })


            root.mycreatingrecycleview.adapter = myGroupAdapter



    }
    private fun sync(){
        val list:ArrayList<Group> = myDbMentor.getAllGroup()
        list.forEach {
            if (it.isopen == "0" && it.person?.curs_id == Hold.person?.curs_id){
               groupList.add(it)
            }
        }
        Update()


    }

    override fun onResume() {
        super.onResume()

    }




}