package com.sephora.happyshop.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
/**
 * Created by Kishore on 21/8/2017.
 */
class Category(val category: String = ""): Serializable {

    var id: Int? = null

    var name: String? = null

    var price: Int? = null
    var imgUrl: String? = null
    var underSale: Boolean? = null



    var page: Int? = 0

    @SerializedName("products")
    var products: List<Product>? = null



    @SerializedName("product")
    var product: Product? = null






}