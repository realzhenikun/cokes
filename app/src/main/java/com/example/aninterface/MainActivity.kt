package com.example.aninterface

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.aninterface.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var postViewpagerAdapter: PostViewpagerAdapter
    private lateinit var upListAdapter: UpListAdapter

    /*
        在onCreate函数外创建对象
        upList和postList在两个Activity中都有用到
     */
    private var postList = UpSender().createPostList()
    private var upList = UpSender().createUpList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //视图绑定
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
            初始化适配器
            传递数据
            视图绑定
         */
        upListAdapter = UpListAdapter()
        postViewpagerAdapter = PostViewpagerAdapter()

        upListAdapter.setData(upList)
        //关联up主和动态
        UpSender().connect(upList,postList)
        postViewpagerAdapter.setData(postList)

        binding.upRecyclerView.adapter = upListAdapter
        binding.postViewPager.adapter = postViewpagerAdapter


        //创建布局管理器
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.upRecyclerView.layoutManager = layoutManager

        /*
            实现点击up主头像或名字切换对应动态
            通过点击监听器实现
         */
        upListAdapter.setOnItemClickListener(object : UpListAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                binding.postViewPager.currentItem = position
            }
        })

        /*
            实现长按长按up主头像或名字跳转DetailActivity
            通过长按监听器实现
            通过Intent将up对象传入DetailActivity
            过时的startActivityForResult方法还未来得及替换
         */
        upListAdapter.setOnItemLongClickListener(object : UpListAdapter.OnItemLongClickListener {
            override fun onItemLongClick(up: Up): Boolean {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("up", up)
                startActivityForResult(intent, 1)
                return true
            }
        })

        /*
            实现滑动切换动态
            当切换到上方未显示出的up主的动态时,RecyclerView也会跟随切换显示
         */
        binding.postViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.upRecyclerView.scrollToPosition(position)
            }
        })
    }

    //重写 onActivityResult() 方法
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //resultCode为RESULT_OK说明Intent成功返回了upName
        if (resultCode == RESULT_OK) {
            val upName = data?.getStringExtra("upName")
            //判断upName是否为空
            if (upName != null) {
                deleteUp(upName)
            }
        }
    }

    //删除up主及其对应动态
    fun deleteUp(upName:String) {
        var index: Int = -1
        for (i in 0..<upList.size) {
            if (upName == upList[i].name) {
                upList.removeAt(i)
                postList.removeAt(i)
                index = i
                break
            }
        }
        if (index != -1) {
            upListAdapter.notifyItemRemoved(index)
            postViewpagerAdapter.notifyItemRemoved(index)
        }
    }

}
