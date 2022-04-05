package com.dominic.codialuz.Users

import java.io.Serializable

class Person:Serializable{
    var curs_id:Int? = null
    var curs_name:String? = null
    var curs_about:String? = null
    lateinit var mentor: Mentor

    constructor(curs_name: String?, curs_about: String?) {
        this.curs_name = curs_name
        this.curs_about = curs_about
    }

    constructor(curs_id: Int?) {
        this.curs_id = curs_id
    }

    constructor()

    constructor(curs_name: String?, curs_about: String?, mentor: Mentor) {
        this.curs_name = curs_name
        this.curs_about = curs_about
        this.mentor = mentor
    }
    constructor(curs_id: Int?,curs_name: String?){
        this.curs_id = curs_id
        this.curs_name = curs_name
    }

    constructor(curs_id: Int?, curs_name: String?, curs_about: String?, mentor: Mentor) {
        this.curs_id = curs_id
        this.curs_name = curs_name
        this.curs_about = curs_about
        this.mentor = mentor
    }

    constructor(curs_id: Int?, curs_name: String?, curs_about: String?) {
        this.curs_id = curs_id
        this.curs_name = curs_name
        this.curs_about = curs_about
    }


}




