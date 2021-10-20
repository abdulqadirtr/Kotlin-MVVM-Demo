package com.example.demoarchitectcomponent.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demoarchitectcomponent.R
import com.example.demoarchitectcomponent.viewModel.XkcdMainViewModel

class XkcdMainFragment : Fragment() {

    companion object {
        fun newInstance() = XkcdMainFragment()
    }

    private lateinit var viewModel: XkcdMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.xkcd_main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(XkcdMainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}