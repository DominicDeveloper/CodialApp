package com.dominic.codialuz.Users

class Mentor {
    var Surname:String? = null
    var Name:String? = null
    var Number:String? = null
    var person:Person? = null
    var id:Int? = null
    constructor()


    constructor(Surname: String?, Name: String?, Number: String?, person: Person, id: Int?) {
        this.Surname = Surname
        this.Name = Name
        this.Number = Number
        this.person = person
        this.id = id
    }

    constructor(Surname: String?, Name: String?) {
        this.Surname = Surname
        this.Name = Name
    }

    constructor(id: Int?) {
        this.id = id
    }


    constructor(Surname: String?, Name: String?, Number: String?) {
        this.Surname = Surname
        this.Name = Name
        this.Number = Number
    }


    constructor(id: Int?,Surname: String?,Name: String?,Number: String?,person: Person){
        this.id = id
        this.Surname = Surname
        this.Name = Name
        this.Number = Number
        this.person = person
    }

    constructor(Surname: String?, Name: String?, Number: String?, person: Person?) {
        this.Surname = Surname
        this.Name = Name
        this.Number = Number
        this.person = person
    }
    constructor(id: Int?,Name: String?){
        this.id = id
        this.Name = Name
    }

    constructor(Name: String?) {
        this.Name = Name
    }
    constructor(Name: String?,id: Int?){
        this.Name = Name
        this.id = id
    }
    constructor(id: Int?,Name: String?,Surname: String?,Number: String?){
        this.id = id
        this.Name = Name
        this.Surname = Surname
        this.Number  = Number
    }


}