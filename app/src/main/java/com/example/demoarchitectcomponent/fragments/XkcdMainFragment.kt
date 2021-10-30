package com.example.demoarchitectcomponent.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.demoarchitectcomponent.BaseFragment
import com.example.demoarchitectcomponent.R
import com.example.demoarchitectcomponent.databinding.XkcdMainFragmentBinding
import com.example.demoarchitectcomponent.repository.XkcdRepository
import com.example.demoarchitectcomponent.viewModel.ViewModelFactory
import com.example.demoarchitectcomponent.viewModel.XkcdMainViewModel

class XkcdMainFragment : BaseFragment<XkcdMainFragmentBinding>() {

    private lateinit var binding: XkcdMainFragmentBinding

    companion object {
        fun newInstance() = XkcdMainFragment()
    }

    private lateinit var xkcdfragmentVM: XkcdMainViewModel


    override fun getLayoutId(): Int = R.layout.xkcd_main_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        xkcdfragmentVM = ViewModelProvider(this, ViewModelFactory(XkcdRepository())).get(XkcdMainViewModel::class.java)

        xkcdfragmentVM.getInitialComic()

        activity?.let {
            with(xkcdfragmentVM.myResponse){

               observe(it, Observer {
                    Log.d("XKCDBody", it.alt)
                    Log.d("XKCDTitle", it.title)
                    Log.d("XKCDImg", it.img)
                    Log.d("XKCDNumber", it.num.toString())
                    getDataBinding().comicTitle.text = it.title
                })
            }
        }

    }

}