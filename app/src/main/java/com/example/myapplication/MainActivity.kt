package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.page2.Page2Activity
import com.example.myapplication.page3.Page3Activity
import com.example.myapplication.page4.Page4Activity
import com.example.myapplication.page5.Page5Activity

class MainActivity : AppCompatActivity(), MainContract.View {

    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val presenter = MainPresenter(this)

        binding.btn1.setOnClickListener {
            presenter.Count()
        }

        binding.btn2.setOnClickListener {
            val intent = Intent(this, Page3Activity::class.java);
            startActivity(intent);
        }
        binding.btn3.setOnClickListener {
            val intent = Intent(this, Page4Activity::class.java);
            startActivity(intent);
        }
        binding.btn4.setOnClickListener {
            val intent = Intent(this, Page5Activity::class.java);
            startActivity(intent);
        }
    }

    override fun showToast(i: Int) {
        Toast.makeText(this, "$i", Toast.LENGTH_SHORT).show();
    }

    override fun goPage2() {
        val intent = Intent(this, Page2Activity::class.java);
        startActivity(intent);
    }
}