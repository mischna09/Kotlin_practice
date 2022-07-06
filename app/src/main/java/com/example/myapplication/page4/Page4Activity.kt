package com.example.myapplication.page4

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

        //another recycleview
        val test_array = arrayListOf<TestModel>()
        for( i in 0..100){
            test_array.add(TestModel("$i",0))
        }
        val recycleview2 = binding.recycleview2
        val testAdapter = TestAdapter(this, test_array)
        val linearLayoutManager2 = LinearLayoutManager(this)
        linearLayoutManager2.orientation = LinearLayoutManager.VERTICAL
        recycleview2.layoutManager = linearLayoutManager2
        recycleview2.adapter = testAdapter

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

/********        another adapter        ***********/
class TestAdapter : RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private var context: Context
    private var data: ArrayList<TestModel>

    constructor(context: Context, data: ArrayList<TestModel>) : super() {
        this.context = context
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cell = LayoutInflater.from(context).inflate(R.layout.layout_item2, parent, false)
        val viewHolder = ViewHolder(cell)
        viewHolder.text_title = cell.findViewById(R.id.text_title)
        viewHolder.img_item = cell.findViewById(R.id.img_item)

        cell.setOnClickListener{
            Toast.makeText(context,viewHolder.text_title.text,Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = data[position]
        holder.text_title.text = model.title
        holder.img_item.setImageResource(R.drawable.ic_baseline_access_time_24)
    }

    class ViewHolder : RecyclerView.ViewHolder {
        lateinit var text_title: TextView
        lateinit var img_item: ImageView

        constructor(itemView: View) : super(itemView)
    }
}

class TestModel {
    var title: String
    var img_res: Int

    constructor(title: String, img_res: Int) {
        this.title = title
        this.img_res = img_res
    }
}