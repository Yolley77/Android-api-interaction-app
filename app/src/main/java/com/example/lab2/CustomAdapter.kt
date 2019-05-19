package com.example.lab2

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

open class CustomAdapter(private val context: Context?, val items: ArrayList<Item>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item: Item = items[position]
        viewHolder.onBindView(item)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private var graphic = view.findViewById<ImageView>(R.id.graphic)
        private var name = view.findViewById<TextView>(R.id.name)
        private var helptext = view.findViewById<TextView>(R.id.helptext)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            if(view != null) {
                val intent = Intent(context, ViewPagerActivity::class.java)
                intent.putParcelableArrayListExtra("items", items)
                intent.putExtra("item_name", items[adapterPosition].name)
                startActivity(view.context, intent, null)
            }
        }

        fun onBindView(item: Item) {
            name.text = item.name
            helptext.text = if (item.helptext != null) {
                item.helptext
            } else {
                null        // WARNING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }
            Picasso.get()
                .load(item.graphics)
                .placeholder(R.drawable.abc_ic_star_black_16dp)
                .error(R.color.colorAccent)
                .into(graphic)
        }
    }
}