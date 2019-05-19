package com.example.lab2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class BigItemFragment : Fragment() {
    lateinit var item: Item
    lateinit var nameTextView : TextView
    lateinit var imageView : ImageView
    lateinit var helpTextView : TextView

    fun newFragment() : Fragment {
        return BigItemFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true // сохраняет состояние фрагмента при повороте экрана
        val bundle: Bundle? = this.arguments
        if (bundle != null) {
            item = bundle.getParcelable("item")
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.big_item, container, false)
        nameTextView = view.findViewById(R.id.bigName)
        imageView = view.findViewById(R.id.bigGraphic)
        helpTextView = view.findViewById(R.id.bigHelptext)

        nameTextView.text = item.name
        if (item.helptext != null)
            helpTextView.text = item.helptext
        else helpTextView.text = ""
        Picasso.get() // загрузка картинки
            .load(item.graphics)
            .placeholder(R.drawable.abc_ic_star_black_16dp)
            .error(R.color.colorAccent)
            .into(imageView)


        return view
    }
}
