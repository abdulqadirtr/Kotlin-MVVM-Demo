package com.example.demoarchitectcomponent.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoarchitectcomponent.R
import com.example.demoarchitectcomponent.XkcdModel
import com.example.demoarchitectcomponent.databinding.XkcdMainFragmentBinding

class XkcdAdapter: RecyclerView.Adapter<XkcdAdapter.XkcdViewHolder>(){

    var xkcdData: ArrayList<XkcdModel> = arrayListOf()

    lateinit var binding : XkcdMainFragmentBinding

    private var items : MutableList<XkcdModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=

        // 1# using dataBinding
     /*   val inflater = LayoutInflater.from(parent.context)
        binding = XkcdMainFragmentBinding.inflate(inflater,parent,false)
        return XkcdViewHolder(binding)*/

        // 2# using layout inflater
       XkcdViewHolder (LayoutInflater.from(parent.context).inflate(R.layout.xkcd_items, parent, false))


    override fun getItemCount(): Int {
       return items.size
    }

    fun setItems(myItems: List<XkcdModel>) {
        items.addAll(myItems)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: XkcdViewHolder, position: Int)  = holder.bind(items[position])
    //1 # using dataBinding
   /*inner class XkcdViewHolder(binding: XkcdMainFragmentBinding): RecyclerView.ViewHolder(binding.root) {

    }*/

    // 2# using layout inflater
   inner class XkcdViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val xkcdID = itemView.findViewById<TextView>(R.id.xkcd_item_id)
        val xkcdName = itemView.findViewById<TextView>(R.id.xkcd_item_name)
        val xkcdImageURL = itemView.findViewById<ImageView>(R.id.xkcd_item_url)
       fun bind(items : XkcdModel){
           xkcdID.text = items.xkcdId.toString()
           xkcdName.text = items.xkcdName
           //xkcdImageURL.im = items.xkcdUrl
       }

   }

}

