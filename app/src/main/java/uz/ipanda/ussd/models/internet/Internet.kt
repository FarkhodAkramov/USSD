package uz.ipanda.ussd.models.internet

class Internet {
    var type: String? = null
    var info: String? = null
    var count: Int? = null
    var code: String? = null


    constructor()
    constructor(type: String?, info: String?, count: Int?, code: String?) {
        this.type = type
        this.info = info
        this.count = count
        this.code = code
    }

    override fun toString(): String {
        return "Internet(type=$type, info=$info, count=$count, code=$code)"
    }


}