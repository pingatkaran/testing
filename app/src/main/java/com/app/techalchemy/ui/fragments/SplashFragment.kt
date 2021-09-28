package com.app.techalchemy.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.techalchemy.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    override fun setup() {

    }

}