package uz.ipanda.ussd.models.news

import java.io.Serializable

class News : Serializable {

    var imageURL:String?=null
    var info:String?=null
    var title:String?=null

    constructor(imageURL: String?, info: String?, title: String?) {
        this.imageURL = imageURL
        this.info = info
        this.title = title
    }

    constructor()

    override fun toString(): String {
        return "News(imageURL=$imageURL, info=$info, title=$title)"
    }

}