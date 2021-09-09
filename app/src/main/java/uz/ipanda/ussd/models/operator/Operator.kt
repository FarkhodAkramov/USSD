package uz.ipanda.ussd.models.operator

 class Operator{
     var code:String?=null
     var info:String?=null

     constructor(code: String?, info: String?) {
         this.code = code
         this.info = info
     }

     constructor()

     override fun toString(): String {
         return "Operator(code=$code, info=$info)"
     }


 }