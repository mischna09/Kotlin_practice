package com.example.myapplication.page4

import android.app.Application
import android.content.ClipData
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityPage4Binding
import com.example.myapplication.databinding.LayoutItem2Binding
import com.example.myapplication.page4.Page4Contract
import com.example.myapplication.page4.Page4Presenter
import java.text.FieldPosition

class Page4Activity : AppCompatActivity(), Page4Contract.View {
    private lateinit var binding: ActivityPage4Binding

    private var myAdapter: MyAdapter? = null
    private var appList = ArrayList<AppInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPage4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val presenter = Page4Presenter(this);

        initApplist()

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recycleview1.run {
            layoutManager = linearLayoutManager
            addItemDecoration(
                DividerItemDecoration(
                    this@Page4Activity,
                    LinearLayoutManager.VERTICAL
                )
            )
            myAdapter = MyAdapter(appList)
            adapter = myAdapter
        }


    }

    override fun createItem(title: String, content: String) {

    }

    private fun initApplist() {
        val list: List<ApplicationInfo> =
            packageManager.getInstalledApplications(PackageManager.MATCH_SYSTEM_ONLY)
        for (i in list.indices) {
            appList.add(AppInfo(list[i].loadIcon(packageManager), list[i].packageName))
        }
    }
}

class MyAdapter(val applist: List<AppInfo>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val appIcon: ImageView = view.findViewById(R.id.img_item)
        val appName: TextView = view.findViewById(R.id.text_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item2, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val appInfo = applist[position]
        with(holder) {
            appIcon.setImageDrawable(appInfo.iconDrawable)
            appName.text = appInfo.name
        }
    }

    override fun getItemCount() = applist.size
}

class AppInfo(val iconDrawable: Drawable, val name: String)