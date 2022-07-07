package com.example.myapplication.page5

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.ActivityPage5Binding
import com.google.android.material.tabs.TabLayout

class Page5Activity : AppCompatActivity(), Page5Contract.View {
    private lateinit var binding: ActivityPage5Binding

    private lateinit var text: TextView;
    private lateinit var layout_parent: LinearLayout;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPage5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val presenter = Page5Presenter(this);

        val fragmentlist: ArrayList<Fragment> = ArrayList()
        fragmentlist.add(AFragment())
        fragmentlist.add(BFragment())
        fragmentlist.add(ViewPagerFragment())
        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.updateList(fragmentlist)
        //改變方向
        val viewpager2 = binding.viewpager22
        val tabPage5 = binding.tabPage5
        viewpager2.adapter = viewPagerAdapter
        //禁止滑動
        //viewpager2.isUserInputEnabled = false

        //viewPager2切換
        viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabPage5.getTabAt(position)?.select()
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

        //Tab切換
        tabPage5.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager2.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    override fun createItem(title: String, content: String) {

    }
}





