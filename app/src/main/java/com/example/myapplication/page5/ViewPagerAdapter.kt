package com.example.myapplication.page5

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.databinding.FragmentPage5ABinding
import com.example.myapplication.databinding.FragmentPage5BBinding
import com.example.myapplication.databinding.LayoutItemBinding

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    var list: ArrayList<Fragment> = ArrayList()
    fun updateList(list: ArrayList<Fragment>) {
        this.list = list
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    override fun createFragment(position: Int): Fragment {
        //return ViewPagerFragment(position)
        return list[position]
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
        binding.btnPage5FragACommit.setOnClickListener {
            Toast.makeText(context, "A", Toast.LENGTH_SHORT).show();
        }
        binding.fabPage5FragA.setOnClickListener{
            binding.editPage5FragA1.text = Editable.Factory.getInstance().newEditable("Reset!")
        }

        return binding.root
    }

    override fun onResume() {
        Toast.makeText(context, "RE", Toast.LENGTH_SHORT).show();
        super.onResume()
    }
}

class BFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPage5BBinding.inflate(inflater, container, false)
        //val view = LayoutInflater.from(context).inflate(R.layout.fragment_page5_b, null)
        return binding.root
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