package com.dominic.codialuz.Interfaces

import com.dominic.codialuz.Users.Group
import com.dominic.codialuz.Users.Mentor
import com.dominic.codialuz.Users.Person
import com.dominic.codialuz.Users.Student

interface DbMentorService {
    fun getAllPerson():ArrayList<Person>
    fun addPerson(person: Person)
    fun getAllCourse(): ArrayList<Person>
    fun addCourse(person: Person)
    fun upDate(person: Person): Int

    fun getAllMentor():ArrayList<Mentor>
    fun addMentor(mentor: Mentor)
    fun editMentor(mentor: Mentor):Int
    fun deleteMentor(mentor: Mentor)
    fun getCourseByid(id:Int):Person


    fun getAllGroup():ArrayList<Group>
    fun addGroup(group: Group)
    fun getMentorById(id: Int):Mentor
    fun editGroup(group: Group):Int
    fun deleteGroup(group: Group)
    fun getCourseById(id:Int):Person
    fun startLesson(group: Group):Int

    fun getAllStudent():ArrayList<Student>
    fun addStudent(student: Student)
    fun editStudent(student: Student):Int
    fun deleteStudent(student: Student)
    fun getGroupById(id: Int):Group
    fun deleteStudentByGroupId(group: Group)


}