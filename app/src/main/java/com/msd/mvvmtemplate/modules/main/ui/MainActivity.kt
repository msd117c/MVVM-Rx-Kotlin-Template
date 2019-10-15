package com.msd.mvvmtemplate.modules.main.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import com.msd.mvvmtemplate.R
import com.msd.mvvmtemplate.base.ui.BaseAppCompatActivity
import com.msd.mvvmtemplate.databinding.ActivityMainBinding
import com.msd.mvvmtemplate.modules.main.navigator.MainNavigator
import com.msd.mvvmtemplate.modules.main.viewmodel.MainViewModel

class MainActivity : BaseAppCompatActivity<MainViewModel, MainNavigator, ActivityMainBinding>(
    R.layout.activity_main,
    MainViewModel::class.java
) {

    override fun extractIntentData() {}

    override fun configureView(savedInstanceState: Bundle?) {
        binding.incrementButton.setOnClickListener {
            viewModel.incrementCounter()
        }
    }

    override fun configureViewModel() {
        super.configureViewModel()
        viewModel.data.observe(this, Observer { text ->
            binding.counter.text = text
        })
    }

    override fun initActivity() {
        viewModel.initViewModel()
    }

}
