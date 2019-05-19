package com.example.lab2

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import java.util.ArrayList

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        LoadDataTask(this).execute()
    }

    inner class LoadDataTask(private val context: Context) : AsyncTask<Void, Void, ArrayList<Item>>() {

        override fun onPreExecute() {
            super.onPreExecute() // перед выполнением таска
            if (!isNetworkAvailable(context)) {
                Toast.makeText(context, "The connection is lost", Toast.LENGTH_LONG).show() //вывод сообщения о соединении с интернетом
            } else Toast.makeText(context, "The connection is ok", Toast.LENGTH_LONG).show()
        }

        override fun doInBackground(vararg params: Void): ArrayList<Item> {
            return JSONParser().getJSON()
        }

        override fun onPostExecute(items: ArrayList<Item>) {
            if (items.isNotEmpty()) {
                Toast.makeText(context,"Data is downloaded", Toast.LENGTH_LONG).show()
                SystemClock.sleep(2000)
                val mItems = items as ArrayList<Item>
                val i = Intent(baseContext, MainActivity::class.java)
                i.putParcelableArrayListExtra("itemsarray", mItems)
                startActivity(i)
                finish()
            } else {
                Toast.makeText(context,"No items", Toast.LENGTH_LONG).show()
                val i = Intent(baseContext, MainActivity::class.java)
                startActivity(i)
                finish()
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        System.exit(0)
    }
}

private fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnectedOrConnecting
}