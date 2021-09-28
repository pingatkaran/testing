package com.app.techalchemy.ui.fragments

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.app.techalchemy.utils.NetworkUtils

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    open var useSharedViewModel: Boolean = false
    private var mContext: Context? = null

    var mProgressDialog: ProgressDialog? = null

    open fun onBackPressed() {}

    lateinit var navController: NavController
    protected open lateinit var binding: B
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> B


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        mProgressDialog = ProgressDialog(requireContext());

        setup()

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled) {
                        isEnabled = false
                        onBackPressed()
                    }
                }
            })
    }

    abstract fun setup()

    private fun handleNetworkChanges(applicationContext: Context) {
        NetworkUtils.getNetworkLiveData(applicationContext).observe(this, Observer { isConnected ->

        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context;
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
    }

    fun showDialog() {
        if (mProgressDialog != null && !mProgressDialog!!.isShowing()) mProgressDialog!!.show()
    }

    fun hideDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing()) mProgressDialog!!.dismiss()
    }
}