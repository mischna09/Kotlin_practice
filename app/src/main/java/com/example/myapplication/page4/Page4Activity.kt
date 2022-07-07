package com.example.myapplication.page4

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityPage4Binding
import com.example.myapplication.databinding.LayoutItem2Binding
import java.lang.IndexOutOfBoundsException
import java.util.*

class Page4Activity : AppCompatActivity(), Page4Contract.View {
    private lateinit var binding: ActivityPage4Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPage4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val presenter = Page4Presenter(this);

        /*initApplist()

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recycleview1.run {
            layoutManager = linearLayoutManager
            myAdapter = MyAdapter(appList)
            adapter = myAdapter
        }*/

        //another recycleview
        val test_array = mutableListOf<TestModel>()
        for( i in 0..3){
            test_array.add(TestModel("$i",0))
        }
        val recycleview2 = binding.recycleview2
        val testAdapter = TestAdapter(this, test_array)
        testAdapter.submitList(test_array)

        val linearLayoutManager2 = WrapLinearLayoutManager(this)
        linearLayoutManager2.orientation = LinearLayoutManager.VERTICAL
        recycleview2.adapter = testAdapter
        recycleview2.layoutManager = linearLayoutManager2

        binding.btnPage4Add1.setOnClickListener{
            test_array.add(TestModel("${(10..20).random()}",0))

            // #submitlist 不同傳入相同的list，隨便弄一個新的即可
            testAdapter.submitList(test_array.toMutableList())
        }
        binding.btnPage4Reduce1.setOnClickListener{
            test_array.removeAt(test_array.lastIndex)
            testAdapter.submitList(test_array.toMutableList())
        }
        binding.btnPage4Shuffle.setOnClickListener{
            //val random_array = testAdapter.currentList.shuffled()
            val random_array = test_array.shuffled()
            testAdapter.submitList(random_array)
        }
    }

    override fun createItem(title: String, content: String) {

    }

    /*private fun initApplist() {
        val list: List<ApplicationInfo> =
            packageManager.getInstalledApplications(PackageManager.MATCH_SYSTEM_ONLY)
        for (i in list.indices) {
            appList.add(AppInfo(list[i].loadIcon(packageManager), list[i].packageName))
        }
    }*/
}

/*class MyAdapter(val applist: List<AppInfo>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
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

class AppInfo(val iconDrawable: Drawable, val name: String)*/

/********        another adapter        ***********/
class TestAdapter(private var context: Context, private var data: MutableList<TestModel>) :
    ListAdapter<TestModel, TestAdapter.ViewHolder>(DiffCallback()) {
    private lateinit var binding : LayoutItem2Binding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val cell = LayoutInflater.from(context).inflate(R.layout.layout_item2, parent, false)
        //val viewHolder = ViewHolder(cell)
        binding = LayoutItem2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        //物件導向
        val viewHolder = ViewHolder(binding)
        viewHolder.text_title = binding.textTitle
        viewHolder.img_item = binding.imgItem
        viewHolder.layout_back = binding.layoutBack

        return viewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //設定個別物件
        val model = data[position]
        holder.text_title.text = model.title
        holder.img_item.setImageResource(R.drawable.ic_baseline_access_time_24)
        holder.layout_back.setOnClickListener{
            Toast.makeText(context,model.title,Toast.LENGTH_SHORT).show()
        }
    }

    class ViewHolder(val binding: LayoutItem2Binding) : RecyclerView.ViewHolder(binding.root){
        //初始化物件
        lateinit var text_title : TextView
        lateinit var img_item : ImageView
        lateinit var layout_back : LinearLayout
    }
}
//data class
data class TestModel(var title: String, var img_res: Int)

//DiffUtil
class DiffCallback : DiffUtil.ItemCallback<TestModel>() {
    override fun areItemsTheSame(oldItem: TestModel, newItem: TestModel) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: TestModel,newItem: TestModel) =
        oldItem == newItem
}

//WrapLinearLayoutManager
class WrapLinearLayoutManager : LinearLayoutManager{
    constructor(context : Context?) : super(context)

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context,attrs,defStyleAttr,defStyleRes){}

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: IndexOutOfBoundsException){
            Log.e("Error","IndexOutOfBoundsException in RecycleView happens")
        }

    }
}