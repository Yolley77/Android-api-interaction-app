package com.example.lab2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager

class ViewPagerActivity : AppCompatActivity() {
    lateinit var items: ArrayList<Item>
    lateinit var pager: ViewPager
    lateinit var itemName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        pager = findViewById(R.id.pager)
        items = intent.getParcelableArrayListExtra("items")
        itemName = intent.getStringExtra("item_name")
        val fm: FragmentManager = supportFragmentManager
        val adapter = PagerAdapter(fm)
        pager.adapter = adapter

        for (i in 0 until items.size) // вот эта настройка пэйджера для вывода нужного итема
        {
            if (items[i].name == itemName) {
                pager.currentItem = i
                break
            }
        }

    }

    inner class PagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            val item: Item = items[position]
            val bundle = Bundle() // создаем временное хранилище данных
            bundle.putParcelable("item", item) // кладем нужный объект
            val fragment = BigItemFragment().newFragment()
            fragment.arguments = bundle // передаем в новый фрагмент
            return fragment
        }

        override fun getCount(): Int {
            return items.size
        }

    }

}
