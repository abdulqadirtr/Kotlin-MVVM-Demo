package com.example.demoarchitectcomponent.fragments

import android.graphics.Bitmap
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import com.example.demoarchitectcomponent.databinding.XkcdMainFragmentBinding
import com.example.demoarchitectcomponent.repository.XkcdRepository
import com.example.demoarchitectcomponent.viewModel.ViewModelFactory
import com.example.demoarchitectcomponent.viewModel.XkcdMainViewModel

class XkcdMainFragment : Fragment() {

    private lateinit var binding: XkcdMainFragmentBinding

    companion object {
        fun newInstance() = XkcdMainFragment()
    }

    private lateinit var xkcdfragmentVM: XkcdMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // return inflater.inflate(R.layout.xkcd_main_fragment, container, false)
        binding = XkcdMainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

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
                   /* binding.comicImage.setImageURI(it.img.toUri())*/
                   /* Glide.with(this)
                        .load(it.img)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(binding.comicImage)*/

                    binding.comicTitle.text = it.title
                })
            }
        }

    }

}