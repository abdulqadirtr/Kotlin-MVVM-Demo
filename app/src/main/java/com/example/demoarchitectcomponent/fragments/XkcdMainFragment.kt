package com.example.demoarchitectcomponent.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.View
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.demoarchitectcomponent.BaseFragment
import com.example.demoarchitectcomponent.R
import com.example.demoarchitectcomponent.XkcdModel
import com.example.demoarchitectcomponent.adapter.XkcdAdapter
import com.example.demoarchitectcomponent.databinding.XkcdMainFragmentBinding
import com.example.demoarchitectcomponent.di.XkcdApplication
import com.example.demoarchitectcomponent.repository.XkcdRepository
import com.example.demoarchitectcomponent.room.XKCDInitialDbResponseModel
import com.example.demoarchitectcomponent.room.XKCDRoomViewModel
import com.example.demoarchitectcomponent.viewModel.ViewModelFactory
import com.example.demoarchitectcomponent.viewModel.XkcdMainViewModel import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

class XkcdMainFragment : BaseFragment<XkcdMainFragmentBinding>() {

    companion object {
        var comic_num: Long = 0
        var first_comic_num: Long = 1
        var last_comic_num: Long = 0
    }

    private lateinit var xkcdMainViewModel: XkcdMainViewModel
    private var xkcdAdapter : XkcdAdapter = XkcdAdapter()

   /* @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory*/


    override fun getLayoutId(): Int = R.layout.xkcd_main_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.application as XkcdApplication).applicationComponent.inject(this)

        xkcdMainViewModel = ViewModelProvider(this, ViewModelFactory(XkcdRepository())).get(XkcdMainViewModel::class.java)

        xkcdMainViewModel.getFirstComic()


        //TODO move viewmodel to BASEFRAGMENT

        with(getDataBinding()) {
          randomBtn.setOnClickListener {
                comic_num = (first_comic_num until last_comic_num).random()
                xkcdMainViewModel.getAllComics(comic_num)
            }
            prevBtn.setOnClickListener {
                if (comic_num > first_comic_num) {
                    comic_num -= 1
                    xkcdMainViewModel.getAllComics(comic_num)
                } else {
                    Snackbar.make(requireView(), "You Have reached at start", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
            nextBtn.setOnClickListener {
                if (comic_num < last_comic_num) {
                    comic_num += 1
                    xkcdMainViewModel.getAllComics(comic_num)
                } else {
                    Snackbar.make(requireView(), "You Have reached at end", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }

        //TODO Dummy data for Recyclerview

       var myCurrencies = listOf(
           XkcdModel(1, "Dollar", ""),
           XkcdModel(1, "Euro", ""),
           XkcdModel(1, "Lira ", ""),
           XkcdModel(1, "Pound", ""),
           XkcdModel(1, "Rupees", ""),
           XkcdModel(1, "Dinar", ""),
       )

        getDataBinding().xkcdList.adapter = xkcdAdapter
        xkcdAdapter.setItems(myCurrencies)
        xkcdAdapter.itemClickListener = {
            Snackbar.make( view , it.xkcdName, Snackbar.LENGTH_LONG).show()
        }

        initObserve()
    }

    private fun initObserve() {
        var xkcdRoomViewModel: XKCDRoomViewModel = XKCDRoomViewModel(requireActivity().application)
        with(xkcdMainViewModel){

            allComicsResponse.observe(viewLifecycleOwner, Observer {
                getDataBinding().comicTitle.text = it.title
                Glide.with(this@XkcdMainFragment)
                    .load(it.img)
                    .into(getDataBinding().comicImage)

                //TODO Roomdatabase implemented get and set values need to be implement for offline
                //roomdatabase insert part
                viewLifecycleOwner.lifecycleScope.launch{
                    xkcdRoomViewModel.insert(XKCDInitialDbResponseModel(it.alt,it.day,it.img,it.link,it.month,it.news,it.num, it.safe_title,it.title,it.transcript,it.year))
                }
            })

            xkcdRoomViewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
               //observe database for store values

            })


            currentComicsResponse.observe(viewLifecycleOwner, Observer {
                getDataBinding().comicTitle.text = it.title
                Glide.with(this@XkcdMainFragment)
                    .load(it.img)
                    .into(getDataBinding().comicImage)
                last_comic_num = it.num.toLong()
            })
        }

    }

}