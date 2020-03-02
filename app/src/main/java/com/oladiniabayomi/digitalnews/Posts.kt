package com.oladiniabayomi.digitalnews

import com.google.gson.annotations.SerializedName

class Posts {

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("date")
    var date : String? = null

    @SerializedName("link")
    var link : String? = null


    @SerializedName("content")
    var content : Content? = null


}
    class Content {

        @SerializedName("rendered")
        var rendered : String? = null

    }
