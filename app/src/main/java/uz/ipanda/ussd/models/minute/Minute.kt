package uz.ipanda.ussd.models.minute

class Minute {
    var type: String? = null
    var price: String? = null
    var count: String? = null
    var code: String? = null
    var period: String? = null


    constructor(type: String?, price: String?, count: String?, code: String?, period: String?) {
        this.type = type
        this.price = price
        this.count = count
        this.code = code
        this.period = period
    }

    constructor()

    override fun toString(): String {
        return "Internet(type=$type, price=$price, count=$count, code=$code, period=$period)"
    }

}