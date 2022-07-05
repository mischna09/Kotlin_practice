package com.example.myapplication.page2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityPage2Binding
import com.example.myapplication.databinding.LayoutItemBinding
import com.google.android.material.textfield.TextInputEditText

class Page2Activity : AppCompatActivity(),Page2Contract.View {
    private lateinit var binding: ActivityPage2Binding

    private lateinit var text:  TextView;
    private lateinit var layout_parent:  LinearLayout;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPage2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val presenter = Page2Presenter(this);

        layout_parent = binding.layoutMain
        binding.button2.setOnClickListener{
            layout_parent.removeAllViews()
            presenter.fillInContent()
        }
        binding.swipelayout.run {
            setOnRefreshListener {
                layout_parent.removeAllViews()
                presenter.fillInContent()
                isRefreshing = false
            }
        }
    }

    override fun createItem(title: String, content:String){

        val layout = layoutInflater.inflate(R.layout.layout_item,null,false)

        //layout.setBackgroundColor(Color.BLUE)

        LayoutItemBinding.bind(layout).run{
            //root.setBackgroundColor(Color.GRAY)
            textTitle.text = title
            textContent.text = content
            root.setOnClickListener{
                Toast.makeText(this@Page2Activity,title,Toast.LENGTH_SHORT).show()
            }
        }

        layout_parent.addView(layout)
    }
}