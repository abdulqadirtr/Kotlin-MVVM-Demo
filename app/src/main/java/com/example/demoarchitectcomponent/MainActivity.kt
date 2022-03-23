package com.example.demoarchitectcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demoarchitectcomponent.databinding.ActivityMainBinding
import com.example.demoarchitectcomponent.fragments.XkcdMainFragment
import com.example.demoarchitectcomponent.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)
        //TODO add toolbar in activity

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.apply { ->btnCounter
            btnCounter.text = "Welcome"
            btnCounter.setOnClickListener{
                viewModel.getValue()
            }
        }

        binding.apply { ->btnGoToXKCD
                if (binding.fragmentContainer.isVisible) {
                    binding.fragmentContainer.visibility = View.GONE
                    binding.btnCounter.visibility = View.VISIBLE
                    binding.textView.visibility = View.VISIBLE
                    binding.btnGoToXKCD.text = "Got To XKCD"
                } else {
                    val xkcdMainFragment = XkcdMainFragment()
                    binding.btnCounter.visibility = View.GONE
                    binding.textView.visibility = View.GONE
                    binding.btnGoToXKCD.text = "Close XKCD Fragment"
                    binding.fragmentContainer.visibility = View.VISIBLE
                    // Get the support fragment manager instance
                    val manager = supportFragmentManager

                    // Begin the fragment transition using support fragment manager
                    val transaction = manager.beginTransaction()

                    // Replace the fragment on container
                    transaction.replace(R.id.fragment_container, xkcdMainFragment)
                    transaction.addToBackStack(null)

                    // Finishing the transition
                    transaction.commit()
                }


        }


        viewModel.counterValue.observe(this, Observer {
            binding.textView.text = it.toString()
        })
    }
}