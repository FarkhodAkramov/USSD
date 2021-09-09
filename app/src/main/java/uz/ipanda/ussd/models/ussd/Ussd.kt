package uz.ipanda.ussd.models.ussd

 class Ussd{
     var code:String?=null
     var info:String?=null

     constructor(code: String?, info: String?) {
         this.code = code
         this.info = info
     }

     constructor()

     override fun toString(): String {
         return "Ussd(code=$code, info=$info)"
     }


 }