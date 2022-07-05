package com.example.demoarchitectcomponent.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demoarchitectcomponent.R
import com.example.demoarchitectcomponent.databinding.HeaderBinding
import com.example.demoarchitectcomponent.databinding.XkcdItemsBinding
import com.example.demoarchitectcomponent.room.XKCDInitialDbResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ClassCastException

class XkcdAdapter: ListAdapter<XkcdAdapter.DataItem, RecyclerView.ViewHolder>(XkcdDiffUtil()){

    private  val ITEM_VIEW_TYPE_HEADER = 0
    private  val ITEM_VIEW_TYPE = 1

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    lateinit var binding : XkcdItemsBinding

    private var items : MutableList<XKCDInitialDbResponseModel> = mutableListOf()

     var itemClickListener: (XKCDInitialDbResponseModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {

        // 1# using dataBinding
        val inflater = LayoutInflater.from(parent.context)
        

        binding = XkcdItemsBinding.inflate(inflater, parent, false)
        //for multiple View Holder
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE -> XkcdViewHolder(binding)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

        // 2# using layout inflater
      // XkcdViewHolder (LayoutInflater.from(parent.context).inflate(R.layout.xkcd_items, parent, false))


   /* fun setItems(myItems: List<XKCDInitialDbResponseModel>) {
        items.clear()
        items.addAll(myItems)
        notifyDataSetChanged()
    }*/

  /*  override fun onBindViewHolder(holder:XkcdViewHolder, position: Int) {
        when(holder){
            is RecyclerView.ViewHolder -> {
                val xkcdId = getItem(position) as DataItem.XkcdItem
                holder.bind(xkcdId.xkcdInitialDbResponseModel)
            }
        }
    }
*/
  override fun onBindViewHolder(holder:RecyclerView.ViewHolder, position: Int) {
      when(holder){
          is XkcdViewHolder -> {
              val xkcdId = getItem(position) as DataItem.XkcdItem
              //(holder as XkcdViewHolder).bind(xkcdId.xkcdInitialDbResponseModel)
              holder.bind(xkcdId.xkcdInitialDbResponseModel)
          }
      }
  }


    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.XkcdItem -> ITEM_VIEW_TYPE
        }
    }
    //1 # using dataBinding
   /*inner class XkcdViewHolder(binding: XkcdMainFragmentBinding): RecyclerView.ViewHolder(binding.root) {

    }*/

    // 2# using layout inflater
   inner class XkcdViewHolder(private val binding: XkcdItemsBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                //itemClickListener(getItem(layoutPosition))
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

    class TextViewHolder(view: View): RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header, parent, false)
                return TextViewHolder(view)
            }
        }
    }

//TODO ListAdapter Implemented
    class XkcdDiffUtil : DiffUtil.ItemCallback<DataItem>(){
        override fun areItemsTheSame(
            oldItem: DataItem,
            newItem: DataItem
        ): Boolean {
           return (oldItem.id == newItem.id)
        }

        override fun areContentsTheSame(
            oldItem: DataItem,
            newItem: DataItem
        ): Boolean {
           return oldItem == newItem
        }

    }

    fun addHeader(list : List<XKCDInitialDbResponseModel>){
        adapterScope.launch {
            val items = when(list){
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map{ DataItem.XkcdItem(it) }
            }
           withContext(Dispatchers.Main){
               submitList(items )
           }
        }
    }

    sealed class DataItem{
        data class XkcdItem(val xkcdInitialDbResponseModel: XKCDInitialDbResponseModel) : DataItem(){
            override val id: Int = xkcdInitialDbResponseModel.num
        }
        object Header : DataItem() {
            override val id = Int.MIN_VALUE
        }

        abstract val id: Int
    }


}


