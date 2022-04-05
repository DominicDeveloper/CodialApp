package com.dominic.codialuz

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.dominic.codialuz.Adapters.MyGroupAdapter
import com.dominic.codialuz.Constant.Hold
import com.dominic.codialuz.DataBase.MyDbMentor
import com.dominic.codialuz.Users.Group
import com.dominic.codialuz.Users.Person
import com.dominic.codialuz.Users.Student
import kotlinx.android.synthetic.main.edit_group.view.*
import kotlinx.android.synthetic.main.fragment_created.view.*
import kotlinx.android.synthetic.main.fragment_finally.view.*
import kotlinx.android.synthetic.main.item_delete_group.view.*

class CreatedFragment : Fragment() {
    lateinit var root:View
    lateinit var groupList:ArrayList<Group>
    lateinit var myGroupAdapter: MyGroupAdapter
    lateinit var myDbMentor: MyDbMentor
    lateinit var studentsnumber:ArrayList<Student>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_created, container, false)
        groupList = ArrayList()
        myDbMentor = MyDbMentor(requireContext())
        studentsnumber = ArrayList()

        onStaticGroups()

        return  root
    }
    private fun onStaticGroups(){
        val person:Person = Hold.person_2!!
        val list:ArrayList<Group> = myDbMentor.getAllGroup()
        list.forEach {
            if (it.isopen == "1" && it.person?.curs_id == person.curs_id) {
                    groupList.add(it)
                }
        }
        Update()


    }
    private fun Update(){
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
                setV.item_group_nentor.setAdapter(adapter)
                setV.item_group_add.setOnClickListener {
                    group.group_name = setV.item_group_name.text.toString().trim()
                    group.group_mentor?.Name = setV.item_group_nentor.text.toString()
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
                Hold.group = group
                Hold.perse = true
                findNavController().navigate(R.id.finallyFragment, bundleOf("visibility" to "false"))

            }

        })

        root.mycreatedgrouprecycle.adapter = myGroupAdapter

    }




}
