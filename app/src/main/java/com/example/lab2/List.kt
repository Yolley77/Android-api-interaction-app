package com.example.lab2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class List : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var items: ArrayList<Item>
        super.onCreate(savedInstanceState)
        this.retainInstance = true
        val bundle: Bundle? = this.arguments
        if(bundle != null) { items = bundle.getParcelableArrayList("items") }
    }

    fun newFragment() : List {
        return List()
    }

    override fun OnCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.list, container, false)

        return view
    }

}
