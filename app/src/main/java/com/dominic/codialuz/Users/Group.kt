package com.dominic.codialuz.Users

import java.io.Serializable

class Group:Serializable {
    var group_id:Int? = null
    var group_name:String? = null
    var group_mentor:Mentor?= null
    var group_time:String? = null
    var group_lesson_days:String? = null
    var isopen:String? = null
    var person:Person? = null
    constructor()
    constructor(group_id: Int?) {
        this.group_id = group_id
    }

    constructor(
        group_id: Int?,
        group_name: String?,
        group_mentor: Mentor?,
        group_time: String?,
        group_lesson_days: String?,
        isopen: String?,
        person: Person?
    ) {
        this.group_id = group_id
        this.group_name = group_name
        this.group_mentor = group_mentor
        this.group_time = group_time
        this.group_lesson_days = group_lesson_days
        this.isopen = isopen
        this.person = person
    }

    constructor(
        group_name: String?,
        group_mentor: Mentor?,
        group_time: String?,
        group_lesson_days: String?,
        isopen: String?,
        person: Person?
    ) {
        this.group_name = group_name
        this.group_mentor = group_mentor
        this.group_time = group_time
        this.group_lesson_days = group_lesson_days
        this.isopen = isopen
        this.person = person
    }

    constructor(
        group_name: String?,
        group_mentor: Mentor?,
        group_time: String?,
        group_lesson_days: String?,
        isopen: String?
    ) {
        this.group_name = group_name
        this.group_mentor = group_mentor
        this.group_time = group_time
        this.group_lesson_days = group_lesson_days
        this.isopen = isopen
    }

    constructor(
        group_id: Int?,
        group_name: String?,
        group_mentor: Mentor?,
        group_time: String?,
        group_lesson_days: String?,
        isopen: String?
    ) {
        this.group_id = group_id
        this.group_name = group_name
        this.group_mentor = group_mentor
        this.group_time = group_time
        this.group_lesson_days = group_lesson_days
        this.isopen = isopen
    }

    constructor(group_id: Int?, group_name: String?) {
        this.group_id = group_id
        this.group_name = group_name
    }


}