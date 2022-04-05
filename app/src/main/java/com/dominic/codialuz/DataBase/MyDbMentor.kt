package com.dominic.codialuz.DataBase


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dominic.codialuz.Constant.CtMentor
import com.dominic.codialuz.Constant.CtMentor.ID
import com.dominic.codialuz.Constant.CtMentor.STUDENT_GROUP_ID
import com.dominic.codialuz.Constant.CtMentor.TABLE_NAME
import com.dominic.codialuz.Interfaces.DbMentorService
import com.dominic.codialuz.Users.Group
import com.dominic.codialuz.Users.Mentor
import com.dominic.codialuz.Users.Person
import com.dominic.codialuz.Users.Student

class MyDbMentor(context: Context):SQLiteOpenHelper(context,CtMentor.DB_NAME,null,CtMentor.DB_VERSION),DbMentorService{
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "create table ${CtMentor.COURSE_TABLE} (${CtMentor.MAIN_COURSE_ID} integer not null primary key autoincrement unique, ${CtMentor.COURSE_NAME} text not null, ${CtMentor.COURSE_ABOUT} text not null);"
        val query_mentor = "create table $TABLE_NAME ($ID integer not null primary key autoincrement unique, ${CtMentor.NAME} text not null, ${CtMentor.SURNAME} text not null, ${CtMentor.NUMBER} text not null, ${CtMentor.COURSE_ID} integer not null, foreign key(${CtMentor.MAIN_COURSE_ID}) references ${CtMentor.COURSE_TABLE} (${CtMentor.COURSE_ID}));"
        val group_2 = "create table group_table (${CtMentor.GROUP_ID} integer not null primary key autoincrement unique, ${CtMentor.GROUP_NAME} text not null unique, ${CtMentor.GROUP_MENTOR} integer not null, ${CtMentor.GROUP_TIME} text not null, ${CtMentor.GROUP_DATE} text not null,${CtMentor.GROUP_ISOPEN} text not null, ${CtMentor.GROUP_COURSE_ID} integer not null, foreign key(${CtMentor.MAIN_COURSE_ID}) references ${CtMentor.COURSE_TABLE}(${CtMentor.GROUP_COURSE_ID}),foreign key ($ID) references $TABLE_NAME(${CtMentor.GROUP_MENTOR}));"
        val query_student = "create table student_table (${CtMentor.STUDENT_ID} integer not null primary key autoincrement unique, ${CtMentor.STUDENT_NAME} text not null, ${CtMentor.STUDENT_FAMILE} text not null, ${CtMentor.STUDENT_PHONE} text not null, ${CtMentor.STUDENT_JOINED} text not null, ${CtMentor.STUDENT_GROUP_ID} integer not null, foreign key(${CtMentor.STUDENT_GROUP_ID}) references group_table (${CtMentor.GROUP_ID}));"
        db?.execSQL(query)
        db?.execSQL(query_mentor)
        db?.execSQL(group_2)
        db?.execSQL(query_student)

    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists $TABLE_NAME")
        onCreate(db)

    }

    override fun getAllPerson(): ArrayList<Person> {
        val db = this.readableDatabase
        val list = ArrayList<Person>()
        val query = "select * from ${CtMentor.COURSE_TABLE}"
        val cursor = db.rawQuery(query,null)
        if (cursor.moveToFirst()){
          do {
              val person = Person(cursor.getInt(0),cursor.getString(1),cursor.getString(2))
              list.add(person)
          }while (cursor.moveToNext())

        }
        return list
    }
    override fun addPerson(person: Person) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(CtMentor.COURSE_NAME,person.curs_name)
        contentValues.put(CtMentor.COURSE_ABOUT,person.curs_about)
        db.insert(CtMentor.COURSE_TABLE,null,contentValues)
        db.close()
    }
    override fun getAllCourse(): ArrayList<Person> {
        val list = ArrayList<Person>()
        val query = "select * from ${CtMentor.COURSE_TABLE}"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query,null)

        if (cursor.moveToFirst()){
            do{
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val about = cursor.getString(2)
                val person = Person(id,name,about)
                list.add(person)
            }while (cursor.moveToNext())
        }
        return list
    }
    override fun addCourse(person: Person) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(CtMentor.COURSE_NAME,person.curs_name)
        contentValue.put(CtMentor.COURSE_ABOUT,person.curs_about)
        database.insert(CtMentor.COURSE_TABLE,null,contentValue)
        database.close()

    }
    override fun upDate(person: Person): Int {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(CtMentor.COURSE_NAME,person.curs_name)
        contentValue.put(CtMentor.COURSE_ABOUT,person.curs_about)
        return database.update(CtMentor.COURSE_TABLE,contentValue,"${CtMentor.COURSE_TABLE} = ?", arrayOf(person.curs_id.toString()))
    }

    override fun getAllMentor(): ArrayList<Mentor> {
        val db = this.readableDatabase
        val list = ArrayList<Mentor>()
        val query = "select * from $TABLE_NAME"
        val cursor = db.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val mentor = Mentor(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),getCourseByid(cursor.getInt(4)))
            list.add(mentor)
            }while (cursor.moveToNext())
        }
        return list
    }
    override fun addMentor(mentor: Mentor) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(CtMentor.SURNAME,mentor.Surname)
        contentValues.put(CtMentor.NAME,mentor.Name)
        contentValues.put(CtMentor.NUMBER,mentor.Number)
        contentValues.put(CtMentor.COURSE_ID,mentor.person?.curs_id)
        db.insert(TABLE_NAME,null,contentValues)
        db.close()

    }
    override fun editMentor(mentor: Mentor):Int {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(ID,mentor.id)
        contentValue.put(CtMentor.NAME,mentor.Name)
        contentValue.put(CtMentor.SURNAME,mentor.Surname)
        contentValue.put(CtMentor.NUMBER,mentor.Number)
        return  database.update(TABLE_NAME,contentValue,"$ID = ?", arrayOf(mentor.id.toString()))
    }
    override fun deleteMentor(mentor: Mentor) {
       val list = getAllGroup()

        val db = this.writableDatabase
        list.forEach {
            if (it.group_mentor?.id == mentor.id){
                delSt(it)
                deleteGroup(it)

            }
        }
        db.delete(TABLE_NAME,"$ID = ?", arrayOf(mentor.id.toString()))
        db.close()
    }
    private fun delSt(group: Group){
        val list = getAllStudent()
        list.forEach {
            if (it.student_group?.group_id == group.group_id){
                deleteStudent(it)
            }
        }
    }
    override fun getCourseByid(id: Int): Person {
        val db = this.readableDatabase
        val cursor = db.query(CtMentor.COURSE_TABLE, arrayOf(CtMentor.MAIN_COURSE_ID,CtMentor.COURSE_NAME),"${CtMentor.MAIN_COURSE_ID} = ?",
            arrayOf(id.toString()),null,null,null)

        cursor.moveToFirst()
        val person = Person(cursor.getInt(0), cursor.getString(1))

        return person
    }

    override fun getAllGroup(): ArrayList<Group> {
        val db = this.readableDatabase
        val list = ArrayList<Group>()
        val query = "select * from group_table"
        val cursor = db.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val group = Group(cursor.getInt(0),cursor.getString(1),
                    getMentorById(cursor.getInt(2)),cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),getCourseByid(cursor.getInt(6)))
                list.add(group)
            }while (cursor.moveToNext())
        }
        return list
    }
    override fun addGroup(group: Group) {
        val db = this.writableDatabase
        val contentValues =  ContentValues()
        contentValues.put(CtMentor.GROUP_NAME,group.group_name)
        contentValues.put(CtMentor.GROUP_MENTOR,group.group_mentor?.id)
        contentValues.put(CtMentor.GROUP_TIME,group.group_time)
        contentValues.put(CtMentor.GROUP_DATE,group.group_lesson_days)
        contentValues.put(CtMentor.GROUP_ISOPEN,group.isopen)
        contentValues.put(CtMentor.GROUP_COURSE_ID,group.person?.curs_id)
        db.insert("group_table",null,contentValues)
        db.close()
    }
    override fun getMentorById(id: Int): Mentor {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_NAME, arrayOf(ID,CtMentor.NAME),"$ID = ?",
            arrayOf(id.toString()),null,null,null)

        cursor.moveToFirst()
        val mentor = Mentor(cursor.getInt(0), cursor.getString(1))

        return mentor
    }
    fun getMentorByName(name:String):Mentor{
        val db = this.readableDatabase
        val cursor = db.query(TABLE_NAME, arrayOf(CtMentor.NAME, ID),"${CtMentor.NAME} = ?", arrayOf(name),null,null,null)
        cursor.moveToFirst()
        val mentor = Mentor(cursor.getString(0),cursor.getInt(1))
        return mentor

    }
    override fun editGroup(group: Group): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(CtMentor.GROUP_ID,group.group_id)
        contentValues.put(CtMentor.GROUP_NAME,group.group_name)
        contentValues.put(CtMentor.GROUP_MENTOR,group.group_mentor?.id)
        contentValues.put(CtMentor.GROUP_TIME,group.group_time)
        contentValues.put(CtMentor.GROUP_DATE,group.group_lesson_days)
        contentValues.put(CtMentor.GROUP_ISOPEN,group.isopen)
        contentValues.put(CtMentor.GROUP_COURSE_ID,group.person?.curs_id)
        return db.update("group_table",contentValues,"${CtMentor.GROUP_ID} = ?", arrayOf(group.group_id.toString()))

    }

    override fun startLesson(group: Group): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(CtMentor.GROUP_ID,group.group_id)
        contentValues.put(CtMentor.GROUP_NAME,group.group_name)
        contentValues.put(CtMentor.GROUP_MENTOR,group.group_mentor?.id)
        contentValues.put(CtMentor.GROUP_TIME,group.group_time)
        contentValues.put(CtMentor.GROUP_DATE,group.group_lesson_days)
        contentValues.put(CtMentor.GROUP_ISOPEN,group.isopen)
        contentValues.put(CtMentor.GROUP_COURSE_ID,group.person?.curs_id)
        return db.update("group_table",contentValues,"${CtMentor.GROUP_ID} = ?", arrayOf(group.group_id.toString()))
    }

    override fun deleteGroup(group: Group) {
        val database = this.writableDatabase
        database.delete("group_table","${CtMentor.GROUP_ID} = ?", arrayOf(group.group_id.toString()))

    }
    fun deleteOnlyGroup(group: Group){
        deleteStudentByGroupId(group)
        val database = this.writableDatabase
        database.delete("group_table","${CtMentor.GROUP_ID} = ?", arrayOf(group.group_id.toString()))
        database.close()
    }

    override fun deleteStudentByGroupId(group: Group) {
        val query = "select * from student_table where $STUDENT_GROUP_ID = ${group.group_id}"
        val database = this.writableDatabase
        val cursor = database.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val student = Student(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),getGroupById(cursor.getInt(5)))
                deleteOnlyStudent(student)
            }while (cursor.moveToNext())
        }
    }
    fun deleteOnlyStudent(student: Student){
        val database = this.writableDatabase
        database.delete("student_table","${CtMentor.STUDENT_ID} = ?", arrayOf(student.student_id.toString()))
        database.close()
    }

    override fun getCourseById(id: Int): Person {
        val db = this.readableDatabase
        val cursor = db.query(CtMentor.COURSE_TABLE, arrayOf(CtMentor.MAIN_COURSE_ID,CtMentor.COURSE_NAME),"${CtMentor.MAIN_COURSE_ID} = ?",
            arrayOf(id.toString()),null,null,null)

        cursor.moveToFirst()
        val person = Person(cursor.getInt(0), cursor.getString(1))

        return person

    }

    override fun getAllStudent():ArrayList<Student> {
        val db = this.readableDatabase
        val list = ArrayList<Student>()
        val query = "select * from student_table"
        val cursor = db.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                 val student = Student(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),getGroupById(cursor.getInt(5)))
                   list.add(student)
            }while (cursor.moveToNext())
        }
        return list

    }

    override fun addStudent(student: Student) {
        val db = this.writableDatabase
        val contentValues =  ContentValues()
        contentValues.put(CtMentor.STUDENT_NAME,student.student_name)
        contentValues.put(CtMentor.STUDENT_FAMILE,student.student_surname)
        contentValues.put(CtMentor.STUDENT_PHONE,student.student_number)
        contentValues.put(CtMentor.STUDENT_JOINED,student.student_date)
        contentValues.put(CtMentor.STUDENT_GROUP_ID,student.student_group?.group_id)
        db.insert("student_table",null,contentValues)
        db.close()

    }

    override fun editStudent(student: Student): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(CtMentor.STUDENT_ID,student.student_id)
        contentValues.put(CtMentor.STUDENT_NAME,student.student_name)
        contentValues.put(CtMentor.STUDENT_FAMILE,student.student_surname)
        contentValues.put(CtMentor.STUDENT_PHONE,student.student_number)
        contentValues.put(CtMentor.STUDENT_JOINED,student.student_date)
        contentValues.put(CtMentor.STUDENT_GROUP_ID,student.student_group?.group_id)

        return db.update("student_table",contentValues,"${CtMentor.STUDENT_ID} = ?", arrayOf(student.student_id.toString()))


    }

    override fun deleteStudent(student: Student) {
        val database = this.writableDatabase
        database.delete("student_table","${CtMentor.STUDENT_ID} = ?", arrayOf(student.student_id.toString()))
       // database.close()
    }

    override fun getGroupById(id: Int): Group {
        val db = this.readableDatabase
        val cursor = db.query("group_table", arrayOf(CtMentor.GROUP_ID,CtMentor.GROUP_NAME,CtMentor.GROUP_MENTOR,CtMentor.GROUP_TIME,CtMentor.GROUP_DATE,CtMentor.GROUP_ISOPEN,CtMentor.GROUP_COURSE_ID),"${CtMentor.GROUP_ID} = ?",
            arrayOf(id.toString()),null,null,null)

        cursor.moveToFirst()
        val group = Group(cursor.getInt(0),cursor.getString(1),getMentorById(cursor.getInt(2)),cursor.getString(3),cursor.getString(4),cursor.getString(5),getCourseByid(cursor.getInt(6)))

        return group
    }



}