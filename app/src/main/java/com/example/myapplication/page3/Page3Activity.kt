package com.example.myapplication.page3

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.databinding.ActivityPage3Binding
import com.example.myapplication.databinding.LayoutItemBinding

class Page3Activity : AppCompatActivity(), Page3Contract.View {
    private lateinit var binding: ActivityPage3Binding

    private lateinit var text:  TextView;
    private lateinit var layout_parent:  LinearLayout;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPage3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val presenter = Page3Presenter(this);

        val viewPagerAdapter = ViewPagerAdapter(this)
        //改變方向
        //viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        binding.viewpager2.run{
            adapter = viewPagerAdapter
            binding.btnGo2.setOnClickListener{
                setCurrentItem(2,true)
            }
            binding.btnGo2f.setOnClickListener{
                setCurrentItem(2,false)
            }
            /*registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {

                    super.onPageSelected(position)
                }
                override fun onPageScrollStateChanged(state: Int) {
                    if(state == 2 && currentItem == 1){
                        setCurrentItem(3,false)
                    }
                    super.onPageScrollStateChanged(state)
                }
            })*/
        }
    }

    override fun createItem(title: String, content:String){

    }
}
class ViewPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 7;
    }
    override fun createFragment(position: Int): Fragment {
        return ViewPagerFragment(position)
    }
}
class ViewPagerFragment(): Fragment() {

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
        binding = LayoutItemBinding.inflate(inflater,container,false)
        //val root = inflater.inflate(R.layout.layout_item, container, false)

        binding.textTitle.text = (position + 1).toString()
        binding.run {
            when(position){
                0 ->    layoutBack.setBackgroundColor(Color.GREEN)
                1 ->    layoutBack.setBackgroundColor(Color.YELLOW)
                2 ->    layoutBack.setBackgroundColor(Color.RED)
                3 ->    layoutBack.setBackgroundColor(Color.GREEN)
                4 ->    layoutBack.setBackgroundColor(Color.YELLOW)
            }
        }


        return binding.root
    }
}