package com.example.demoarchitectcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demoarchitectcomponent.databinding.ActivityMainBinding
import com.example.demoarchitectcomponent.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

      /*  binding.btnCounter.text ="Welcome"
        binding.btnCounter.setOnClickListener {
            viewModel.getValue()
        }*/

        binding.apply { ->btnCounter
            btnCounter.text = "Welcome"
            btnCounter.setOnClickListener{
                viewModel.getValue()
            }

        }


        viewModel.counterValue.observe(this, Observer {
            binding.textView.text = it.toString()
        })
    }
}