package com.oladiniabayomi.digitalnews.content

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Content : Serializable {

    @SerializedName("rendered")
    var rendered : String? = null

}
