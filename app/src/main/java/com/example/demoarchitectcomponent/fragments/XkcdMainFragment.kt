package com.example.demoarchitectcomponent.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.demoarchitectcomponent.BaseFragment
import com.example.demoarchitectcomponent.R
import com.example.demoarchitectcomponent.databinding.XkcdMainFragmentBinding
import com.example.demoarchitectcomponent.repository.XkcdRepository
import com.example.demoarchitectcomponent.viewModel.ViewModelFactory
import com.example.demoarchitectcomponent.viewModel.XkcdMainViewModel
import com.google.android.material.snackbar.Snackbar

class XkcdMainFragment : BaseFragment<XkcdMainFragmentBinding>() {

    companion object {
        fun newInstance() = XkcdMainFragment()
        var comic_num: Long = 0
        var first_comic_num: Long = 1
        var last_comic_num: Long = 0
    }

    private lateinit var xkcdfragmentVM: XkcdMainViewModel


    override fun getLayoutId(): Int = R.layout.xkcd_main_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        xkcdfragmentVM = ViewModelProvider(this, ViewModelFactory(XkcdRepository())).get(XkcdMainViewModel::class.java)

        xkcdfragmentVM.getInitialComic()

        initObserve()

        //TODO move viewmodel to BASEFRAGMENT

        getDataBinding().randomBtn.setOnClickListener {
            comic_num = (first_comic_num until last_comic_num).random()
            xkcdfragmentVM.getAllComics(comic_num)
        }

        getDataBinding().prevBtn.setOnClickListener {
            if(comic_num > first_comic_num) {
                comic_num -= 1
                xkcdfragmentVM.getAllComics(comic_num)
            } else {
                Snackbar.make(requireView(), "You Have reached at start", Snackbar.LENGTH_SHORT).show()
            }
        }

        getDataBinding().nextBtn.setOnClickListener {
            if(comic_num < last_comic_num) {
                comic_num += 1
                xkcdfragmentVM.getAllComics(comic_num)
            } else {
                Snackbar.make(requireView(), "You Have reached at end", Snackbar.LENGTH_SHORT).show()
            }
        }


    }

    private fun initObserve() {
        with(xkcdfragmentVM.allComicsResponse){
            observe(viewLifecycleOwner, Observer {
                getDataBinding().comicTitle.text = it.title
                Glide.with(this@XkcdMainFragment)
                    .load(it.img)
                    .into(getDataBinding().comicImage)
            })
        }

        activity?.let {
            with(xkcdfragmentVM.myResponse){

                observe(it, Observer {
                    /*   Log.d("XKCDBody", it.alt)
                       Log.d("XKCDTitle", it.title)
                       Log.d("XKCDImg", it.img)
                       Log.d("XKCDNumber", it.num.toString())*/
                    getDataBinding().comicTitle.text = it.title
                    Glide.with(this@XkcdMainFragment)
                        .load(it.img)
                        .into(getDataBinding().comicImage)
                    last_comic_num = it.num.toLong()
                    comic_num = it.num.toLong()
                })

            }
        }
    }

}