package com.example.listapersonagens.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listapersonagens.databinding.ActivityMainBinding
import com.example.listapersonagens.designpatterns.creationals.RetrofitConfig

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
