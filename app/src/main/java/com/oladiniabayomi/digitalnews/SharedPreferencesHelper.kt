package com.oladiniabayomi.digitalnews

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreferencesHelper {


        val sharedPref = "sharedPref"
        val sentRequest = "sent"
        val key = "key"

     //   val sharedPref = "sharedPref"
        val lastime = "true"
        val lastimeKey = "key"



   fun saveSharedPrefs(context: Application){
       val   sharedPreferences : SharedPreferences = context.getSharedPreferences(sharedPref, MODE_PRIVATE)
       val editor =  sharedPreferences.edit()

       editor.putString( key, sentRequest)
     editor.commit()
    }

    fun getSharedPrefs(context: Application): String?{
        val   sharedPreferences : SharedPreferences = context.getSharedPreferences(sharedPref, MODE_PRIVATE)
        var  text =  sharedPreferences.getString(key, "")

        return text
    }

   fun initLastime(context: Application){
       val   sharedPreferences : SharedPreferences = context.getSharedPreferences(sharedPref, MODE_PRIVATE)
       val editor =  sharedPreferences.edit()

       editor.putString( lastimeKey, lastime)
       editor.commit()
    }




    fun getinitLastime(context: Application): String?{
        val   sharedPreferences : SharedPreferences = context.getSharedPreferences(sharedPref, MODE_PRIVATE)
       var  text =  sharedPreferences.getString( lastimeKey, "")

       return text
    }



}
