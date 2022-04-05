package com.dominic.codialuz

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dominic.codialuz.Adapters.MyMentorsRecyc
import com.dominic.codialuz.DataBase.MyDbMentor
import com.dominic.codialuz.Users.Mentor
import com.dominic.codialuz.Users.Person
import kotlinx.android.synthetic.main.add_mentor.view.*
import kotlinx.android.synthetic.main.do_you_want_to_delete.view.*
import kotlinx.android.synthetic.main.edit_mentor_information.view.*
import kotlinx.android.synthetic.main.fragment_persons.view.*

class PersonsFragment : Fragment() {
    lateinit var root:View
    lateinit var mentorlist:ArrayList<Mentor>
    lateinit var myMentorsRecyc: MyMentorsRecyc
    lateinit var myDbMentor: MyDbMentor
    lateinit var person: Person
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_persons, container, false)
        mentorlist = ArrayList()
        myDbMentor = MyDbMentor(requireContext())
        person = arguments?.getSerializable("person") as Person
        sync()
        val iconBack:ImageView = root.findViewById(R.id.back_icon)
        val iconAdd:ImageView = root.findViewById(R.id.add_icon)
        val title:TextView = root.findViewById(R.id.myapp_name_icon)
        title.text = person.curs_name
        iconAdd.setOnClickListener {
            myAddFunction()
        }
        iconBack.setOnClickListener {
            findNavController().popBackStack()
        }



        return root
    }
    private fun myAddFunction(){
        val myDialog = AlertDialog.Builder(requireContext()).create()
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.add_mentor,null,false)
        myDialog.setView(view)
        myDialog.show()
        view.btn_add_mentor.setOnClickListener {

           val mentor = Mentor(view.item_add_mentor_surname.text.toString(),view.item_add_mentor_name.text.toString(),view.item_add_mentor_number.text.toString(),person)
            if (mentor.Name!!.isNotEmpty()){
                myDbMentor.addMentor(Mentor(view.item_add_mentor_surname.text.toString(),view.item_add_mentor_name.text.toString(),view.item_add_mentor_number.text.toString(),person))
                onResume()
                myDialog.cancel()
                Toast.makeText(requireContext(), "Saqlandi!", Toast.LENGTH_SHORT).show()
            }else
            {
                Toast.makeText(requireContext(), "Iltimos malumotlarni to`liq kiriting!", Toast.LENGTH_SHORT).show()
            }

        }



    }
    private fun ReadList(){

        myMentorsRecyc = MyMentorsRecyc(requireContext(),mentorlist,object : MyMentorsRecyc.RvClick{
            override fun onClick(mentor: Mentor, cardView: CardView) { // edit
                anime(cardView)
                val alertEdit = AlertDialog.Builder(requireContext()).create()
                val setView = LayoutInflater.from(requireContext()).inflate(R.layout.edit_mentor_information,null,false)
                alertEdit.setView(setView)
                alertEdit.show()
                setView.edit_mentors_name.setText(mentor.Surname)
                setView.edit_mentors_surname.setText(mentor.Name)
                setView.edit_mentors_number.setText(mentor.Number)
                setView.item_edit_mentor_edit.setOnClickListener { 
                  mentor.Name = setView.edit_mentors_name.text.toString()
                    mentor.Surname = setView.edit_mentors_surname.text.toString()
                    mentor.Number = setView.edit_mentors_number.text.toString()
                    myDbMentor.editMentor(mentor)
                    ReadList()
                    alertEdit.cancel()
                    Toast.makeText(requireContext(), "Saqlandi!", Toast.LENGTH_SHORT).show()
                }
                setView.item_edit_mentor_close.setOnClickListener { 
                    alertEdit.cancel()
                    Toast.makeText(requireContext(), "Bekor qilindi!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onClickDelete(mentor: Mentor, cardView: CardView) { // delete
                anime(cardView)
                val mydeleteDialog = AlertDialog.Builder(requireContext()).create()
                val myView = LayoutInflater.from(requireContext()).inflate(R.layout.do_you_want_to_delete,null,false)
                mydeleteDialog.setView(myView)
                mydeleteDialog.show()
                myView.text_no.setOnClickListener {
                    mydeleteDialog.cancel()
                    anime(myView.text_no)
                }
                myView.text_yes.setOnClickListener {
                    anime(myView.text_yes)
                    myDbMentor.deleteMentor(mentor)
                    mentorlist.remove(mentor)
                    ReadList()
                    mydeleteDialog.dismiss()
                    Toast.makeText(requireContext(), "O`chirildi!", Toast.LENGTH_SHORT).show()
                }

            }
        })
        root.my_mentors_recyclerview.adapter = myMentorsRecyc

    }
    private fun anime(view: View){
        val anim = AnimationUtils.loadAnimation(requireContext(),R.anim.anim_click)
        view.startAnimation(anim)
    }
    private fun sync(){
        val sync = myDbMentor.getAllMentor()
        sync.forEach {
            if (it.person?.curs_id == person.curs_id){
                mentorlist.add(it)
            }
        }
        ReadList()

    }

    override fun onResume() {
        super.onResume()
        mentorlist.clear()
        sync()

    }

}