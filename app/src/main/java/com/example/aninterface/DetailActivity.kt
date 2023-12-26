package com.example.aninterface

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aninterface.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
            getParcelableExtra返回的是Up?
            因为不确定是否为空
            所以需要判断是否为空
            过时的getParcelableExtra方法暂时未替换
         */
        val up: Up? = intent.getParcelableExtra("up")

        if (up != null) {
            val button = binding.unsubscribeButton

            binding.upImage.setImageResource(up.image)
            binding.upName.text = up.name
            binding.fans.text = up.fans.toString()

            button.setOnClickListener {
                Toast.makeText(this, "取关成功", Toast.LENGTH_SHORT).show()
                val intent = Intent()
                intent.putExtra("upName", up.name)
                setResult(RESULT_OK, intent)
                finish()
            }
        }

    }
}