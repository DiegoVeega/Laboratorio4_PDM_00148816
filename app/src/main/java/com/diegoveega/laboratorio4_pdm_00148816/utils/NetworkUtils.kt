package com.diegoveega.laboratorio4_pdm_00148816.utils

import android.net.Uri
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class NetworkUtils{
    val MOVIES_APÍ_URL = "http://www.omdbapi.com/"
    val TOKEN_API = "495ea6bd"

    fun bueldSearchUri(movieName: String){
        val buildUri = Uri.parse(MOVIES_APÍ_URL)
                .buildUpon()
                .appendQueryParameter("apikey", TOKEN_API)
                .appendQueryParameter("t", movieName)
                .build()

        return try {
            URL(buildUri.toString())
        }catch (e:MalformedURLException){
            URL("")
        }
    }

    @Throws(IOException::class)
    fun getRespenseFromHttpUri(uri:URL): String{
        val uriConnection = uri.openConnection()
        try{
            val `in` = uriConnection.inputStream
            val scanner = Scanner(`in`)
            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()
            return if (hasInput){
                scanner.next()
            }else{

            }

        }finally {
            uriConnection.disconnect()
        }
    }
}