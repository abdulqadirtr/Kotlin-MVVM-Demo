package com.example.demoarchitectcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demoarchitectcomponent.adapter.NoteAdapter
import com.example.demoarchitectcomponent.databinding.ActivityMainBinding
import com.example.demoarchitectcomponent.note.Note
import com.example.demoarchitectcomponent.viewModel.MainViewModel
import com.example.demoarchitectcomponent.viewModel.NoteViewModel

class MainActivity : AppCompatActivity() {

    //lateinit var viewModel: MainViewModel
    lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.getAllNotes().observe(this,  Observer<List<Note>>() {
            //Toast.makeText(MainActivity.this, "OnChanged", Toast.LENGTH_SHORT).show();
            onChanged
            Toast.makeText(this, "onChanged", Toast.LENGTH_SHORT).show()

        };
      /*  binding.btnCounter.text ="Welcome"
        binding.btnCounter.setOnClickListener {
            viewModel.getValue()
        }*/

//        binding.apply { ->btnCounter
//            btnCounter.text = "Welcome"
//            btnCounter.setOnClickListener{
//                viewModel.getValue()
//            }
//
//        }


//        viewModel.counterValue.observe(this, Observer {
//            binding.textView.text = it.toString()
//        })
    }
}