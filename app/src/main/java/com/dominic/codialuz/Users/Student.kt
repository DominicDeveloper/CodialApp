package com.dominic.codialuz.Users

class Student {
    var student_id:Int? = null
    var student_name:String? = null
    var student_surname:String?=  null
    var student_number:String?  = null
    var student_date:String? = null
    var student_group:Group? = null

    constructor(
        student_id: Int?,
        student_name: String?,
        student_surname: String?,
        student_number: String?,
        student_date: String?,
        student_group: Group?
    ) {
        this.student_id = student_id
        this.student_name = student_name
        this.student_surname = student_surname
        this.student_number = student_number
        this.student_date = student_date
        this.student_group = student_group
    }
    constructor()
    constructor(
        student_name: String?,
        student_surname: String?,
        student_number: String?,
        student_date: String?,
        student_group: Group?
    ) {
        this.student_name = student_name
        this.student_surname = student_surname
        this.student_number = student_number
        this.student_date = student_date
        this.student_group = student_group
    }

    constructor(student_name: String?, student_surname: String?) {
        this.student_name = student_name
        this.student_surname = student_surname
    }

    constructor(student_id: Int?, student_name: String?, student_surname: String?) {
        this.student_id = student_id
        this.student_name = student_name
        this.student_surname = student_surname
    }

    constructor(
        student_id: Int?,
        student_name: String?,
        student_surname: String?,
        student_number: String?
    ) {
        this.student_id = student_id
        this.student_name = student_name
        this.student_surname = student_surname
        this.student_number = student_number
    }

    constructor(
        student_id: Int?,
        student_name: String?,
        student_surname: String?,
        student_number: String?,
        student_date: String?
    ) {
        this.student_id = student_id
        this.student_name = student_name
        this.student_surname = student_surname
        this.student_number = student_number
        this.student_date = student_date
    }



}