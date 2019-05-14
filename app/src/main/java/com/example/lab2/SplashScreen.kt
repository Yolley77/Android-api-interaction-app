package com.example.lab2

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.widget.Toast
import java.util.ArrayList

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    inner class LoadDataTask(private val context: Context) : AsyncTask<Void, Void, List<ListItem>>() {
        override fun doInBackground(vararg params: Void): List<ListItem> {
            return JSONParser().getJSON()
        }

        override fun onPreExecute() {
            super.onPreExecute() // перед выполнением таска
            if (!isNetworkAvailable(context)) {
                Toast.makeText(context, "The connection is lost", Toast.LENGTH_LONG).show() //вывод сообщения о соединении с интернетом
            } else Toast.makeText(context, "The connection is ok", Toast.LENGTH_LONG).show()
        }

        override fun onPostExecute(items: List<ListItem>) {
            if (items.isNotEmpty()) Toast.makeText(context,"Data is downloaded", Toast.LENGTH_LONG).show() // сообщение о том, что данные загружены
            SystemClock.sleep(2000)
            val mItems = items as ArrayList<ListItem>
            val i = Intent(baseContext, MainActivity::class.java)
            i.putParcelableArrayListExtra("itemsarray", mItems)
            startActivity(i)
            finish()
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