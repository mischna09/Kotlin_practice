package com.example.myapplication.page5

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityPage5Binding
import com.example.myapplication.databinding.FragmentPage5ABinding
import com.example.myapplication.databinding.LayoutItemBinding
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

        var fragmentlist: ArrayList<Fragment> = ArrayList()
        fragmentlist.add(AFragment())
        fragmentlist.add(BFragment())
        fragmentlist.add(ViewPagerFragment())
        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.updateList(fragmentlist)
        //改變方向
        val viewpager2 = binding.viewpager22
        val tabPage5 = binding.tabPage5
        viewpager2.adapter = viewPagerAdapter
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

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    var list: ArrayList<Fragment> = ArrayList()
    fun updateList(list: ArrayList<Fragment>) {
        this.list = list
    }

    override fun getItemCount(): Int {
        return 3;
    }

    override fun createFragment(position: Int): Fragment {
        //return ViewPagerFragment(position)
        return list[position]
    }
}

class ViewPagerFragment() : Fragment() {

    private var position: Int = 0
    private lateinit var binding: LayoutItemBinding

    constructor(position: Int) : this() {
        this.position = position
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutItemBinding.inflate(inflater, container, false)
        //val root = inflater.inflate(R.layout.layout_item, container, false)

        binding.textTitle.text = (position + 1).toString()
        binding.run {
            when (position) {
                0 -> layoutBack.setBackgroundColor(Color.GREEN)
                1 -> layoutBack.setBackgroundColor(Color.YELLOW)
                2 -> layoutBack.setBackgroundColor(Color.RED)
            }
        }


        return binding.root
    }
}

class AFragment : Fragment() {
    private lateinit var binding: FragmentPage5ABinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPage5ABinding.inflate(inflater, container, false)
        //val view = LayoutInflater.from(context).inflate(R.layout.fragment_page5_a,null)
        binding.btnPage5FragACommit.setOnClickListener {
            Toast.makeText(context, "A", Toast.LENGTH_SHORT).show();
        }
        return binding.root
    }
}

class BFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_page5_b, null)
        return view
    }
}