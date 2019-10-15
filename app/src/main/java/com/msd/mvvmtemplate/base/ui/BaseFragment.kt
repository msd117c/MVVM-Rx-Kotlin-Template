package com.msd.mvvmtemplate.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.msd.mvvmtemplate.base.navigator.BaseNavigator
import com.msd.mvvmtemplate.base.viewmodel.BaseViewModel
import com.msd.mvvmtemplate.base.viewmodel.ViewModelFactory
import com.msd.mvvmtemplate.base.viewmodel.states.State
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<V : BaseViewModel<N>, N : BaseNavigator,
        B : ViewDataBinding> constructor(
    private val layoutId: Int,
    private val viewModelClass: Class<V>
) : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<V>

    lateinit var viewModel: V

    @Inject
    lateinit var navigator: N

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, layoutId, container, false
        )

        extractIntentData()
        configureView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureViewModel()

        viewModel.state.observe(this, Observer {
            when (it) {
                is State.Error<*> -> {

                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        initFragment()
    }

    abstract fun extractIntentData()
    abstract fun configureView()
    abstract fun configureViewModel()
    abstract fun initFragment()
}