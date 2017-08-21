package com.sephora.happyshop.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Kishore on 21/8/2017.
 */

class Product: Serializable  {


    var id: Int? = null
    var name: String? = null
    var category: String? = null

    @SerializedName("price")
    var price: Int? = null

    @SerializedName("img_url")
    var imgUrl: String? = null

    var description: String? = null

    @SerializedName("under_sale")
    var underSale: Boolean? = false



}

//@RealmClass
//open class UserCartCount : RealmObject() {
//
//}