package com.example.demoarchitectcomponent.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoarchitectcomponent.XkcdModel
import com.example.demoarchitectcomponent.databinding.XkcdItemsBinding
import com.example.demoarchitectcomponent.room.XKCDInitialDbResponseModel

class XkcdAdapter: RecyclerView.Adapter<XkcdAdapter.XkcdViewHolder>(){

    var xkcdData: ArrayList<XkcdModel> = arrayListOf()

    lateinit var binding : XkcdItemsBinding

    private var items : MutableList<XKCDInitialDbResponseModel> = mutableListOf()

     var itemClickListener: (XKCDInitialDbResponseModel) -> Unit = {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : XkcdViewHolder {

        // 1# using dataBinding
        val inflater = LayoutInflater.from(parent.context)
        binding = XkcdItemsBinding.inflate(inflater, parent, false)
        return XkcdViewHolder(binding)
    }

        // 2# using layout inflater
      // XkcdViewHolder (LayoutInflater.from(parent.context).inflate(R.layout.xkcd_items, parent, false))


    override fun getItemCount(): Int {
       return items.size
    }

    fun setItems(myItems: List<XKCDInitialDbResponseModel>) {
        items.clear()
        items.addAll(myItems)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: XkcdViewHolder, position: Int)  = holder.bind(items[position])
    //1 # using dataBinding
   /*inner class XkcdViewHolder(binding: XkcdMainFragmentBinding): RecyclerView.ViewHolder(binding.root) {

    }*/

    // 2# using layout inflater
   inner class XkcdViewHolder(private val binding: XkcdItemsBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                itemClickListener(items[layoutPosition])
            }


        }
       fun bind(items : XKCDInitialDbResponseModel){
           binding.xkcdModelName = items
           binding.executePendingBindings()

          /* binding.xkcdItemId.text = items.xkcdId.toString()
           binding.xkcdItemName.text = items.xkcdName*/

          // binding.xkcdItemUrl.setImageDrawable(items.xkcdUrl)
           //xkcdImageURL.im = items.xkcdUrl
       }

   }

}

