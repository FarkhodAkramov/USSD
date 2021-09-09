package uz.ipanda.ussd.models.tariff

import java.io.Serializable

class Tariff : Serializable {
     var name:String?=null
     var payment:String?=null
     var info:String?=null
     var min:String?=null
     var mg:String?=null
     var sms:String?=null

     constructor(
         name: String?,
         payment: String?,
         info: String?,
         min: String?,
         mg: String?,
         sms: String?
     ) {
         this.name = name
         this.payment = payment
         this.info = info
         this.min = min
         this.mg = mg
         this.sms = sms
     }

     constructor()

     override fun toString(): String {
         return "Tariff(name=$name, payment=$payment, info=$info, min=$min, mg=$mg, sms=$sms)"
     }

 }