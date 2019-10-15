package com.msd.mvvmtemplate.base.ui

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.msd.mvvmtemplate.base.navigator.BaseNavigator
import com.msd.mvvmtemplate.base.viewmodel.BaseViewModel
import com.msd.mvvmtemplate.base.viewmodel.ViewModelFactory
import com.msd.mvvmtemplate.base.viewmodel.states.State
import com.msd.mvvmtemplate.domain.connection.repository.ConnectionLiveData
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseAppCompatActivity<V : BaseViewModel<N>, N : BaseNavigator, B : ViewDataBinding> constructor(
    private val layoutId: Int,
    private val viewModelClass: Class<V>
) :
    AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var connectionLiveData: ConnectionLiveData

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<V>

    lateinit var viewModel: V

    @Inject
    lateinit var navigator: N

    protected lateinit var binding: B

    protected lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass)

        navigator.setActivity(this)

        binding = DataBindingUtil.setContentView(this, layoutId)

        extractIntentData()
        configureView(savedInstanceState)
        configureViewModel()

        initActivity()
    }

    override fun onResume() {
        super.onResume()
        viewModel.resumeViewModel()
    }

    abstract fun extractIntentData()
    abstract fun configureView(savedInstanceState: Bundle?)

    open fun configureViewModel() {
        viewModel.state.observe(this, Observer {
            when (it) {
                is State.Error<*> -> {
                    // Handle errors
                }
            }
        })
        // Observe connection changes
        connectionLiveData.observe(this, Observer { connection ->
            Toast.makeText(
                this, if (connection.isConnected) {
                    "You are connected to Internet"
                } else {
                    "You are not connected to Internet"
                }, Toast.LENGTH_LONG
            ).show()
        })
    }

    abstract fun initActivity()

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }
}