package com.example.lab2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ItemList : Fragment() {
    lateinit var items: ArrayList<Item>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = true
        val bundle: Bundle? = this.arguments
        if(bundle != null) { items = bundle.getParcelableArrayList("items") }
    }

    fun newFragment() : ItemList {
        return ItemList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.item_list, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.list) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = CustomAdapter(context, items)
        recyclerView.adapter = adapter
        return view
    }
}

