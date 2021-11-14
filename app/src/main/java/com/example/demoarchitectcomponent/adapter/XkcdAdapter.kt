package com.example.demoarchitectcomponent.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoarchitectcomponent.R
import com.example.demoarchitectcomponent.XkcdModel
import com.example.demoarchitectcomponent.databinding.XkcdMainFragmentBinding

class XkcdAdapter: RecyclerView.Adapter<XkcdAdapter.XkcdViewHolder>(){

    var xkcdData: ArrayList<XkcdModel> = arrayListOf()

    lateinit var binding : XkcdMainFragmentBinding

    private var items : List<XkcdModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XkcdViewHolder{

        // 1# using dataBinding
     /*   val inflater = LayoutInflater.from(parent.context)
        binding = XkcdMainFragmentBinding.inflate(inflater,parent,false)
        return XkcdViewHolder(binding)*/

        // 2# using layout inflater
        return XkcdViewHolder (LayoutInflater.from(parent.context).inflate(R.layout.xkcd_items, parent, false))

    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: XkcdViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    //1 # using dataBinding
   /*inner class XkcdViewHolder(binding: XkcdMainFragmentBinding): RecyclerView.ViewHolder(binding.root) {

    }*/

    // 2# using layout inflater
   inner class XkcdViewHolder(view: View): RecyclerView.ViewHolder(view) {

       fun bind(){

       }

   }

}

