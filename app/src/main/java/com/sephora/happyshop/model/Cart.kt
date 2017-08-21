package com.sephora.happyshop.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Kishore on 21/8/2017.
 */
class Cart :Serializable {



    var products: List<Product>? = ArrayList<Product>();

}