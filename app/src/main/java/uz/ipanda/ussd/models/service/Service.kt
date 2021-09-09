package uz.ipanda.ussd.models.service

class Service {
    var name: String? = null
    var code: String? = null
    var info: String? = null

    constructor(name: String?, code: String?, info: String?) {
        this.name = name
        this.code = code
        this.info = info
    }

    constructor()

    override fun toString(): String {
        return "Service(name=$name, code=$code, info=$info)"
    }


}