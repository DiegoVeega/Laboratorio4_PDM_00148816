package com.diegoveega.laboratorio4_pdm_00148816.activities

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.diegoveega.laboratorio4_pdm_00148816.R
import com.diegoveega.laboratorio4_pdm_00148816.model.Movie
import com.diegoveega.laboratorio4_pdm_00148816.utils.NetworkUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private inner class fectMovie : AsyncTask<String, Void, String>(){
        override fun doInBackground(vararg params: String?): String {
            if(params.isNullOrEmpty()) return ""

            val movieName = params[0]
            val movieURL = NetworkUtils().bueldSearchUri(movieName)

            return try{
                NetworkUtils().getRespenseFromHttpUri(movieURL)
            }catch (e: IOException){
                ""
            }

        }

        override fun onPostExecute(movieInfo: String?) {
            super.onPostExecute(movieInfo)

            if(!movieInfo.isEmpty()){
                val movieJson = JSONObject(movieInfo)

                if(movieJson.getString("Response")== "True"){
                    val movie = Gson().fromJson<Movie>(movieInfo, Movie::class.java)

                }else{
                    Snackbar.make(main_ll, "NO", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}
