package uz.ipanda.ussd.models.sms

class Sms {
    var type:String?=null
    var info:String?=null
    var count:Int?=null

    constructor(type: String?, info: String?, count: Int?) {
        this.type = type
        this.info = info
        this.count = count
    }

    constructor()

    override fun toString(): String {
        return "Sms(type=$type, info=$info, count=$count)"
    }


}