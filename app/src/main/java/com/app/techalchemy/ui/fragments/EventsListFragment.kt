package com.app.techalchemy.ui.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.techalchemy.interfaces.selectedEventListener
import com.app.techalchemy.adapter.EventsAdapter
import com.app.techalchemy.databinding.FragmentEventListBinding
import com.app.techalchemy.model.EventDetails
import com.app.techalchemy.utils.Status
import com.app.techalchemy.viewModels.EventListsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class EventsListFragment : BaseFragment<FragmentEventListBinding>(), selectedEventListener {

    private val viewModel: EventListsViewModel by viewModels()

    private lateinit var eventAdapter: EventsAdapter
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEventListBinding
        get() = FragmentEventListBinding::inflate

    override fun setup() {
        initRecyclerview()

        lifecycleScope.launchWhenStarted {
            viewModel.postState.collect {
                when (it.status) {
                    Status.LOADING -> {
                        // Show Progress
                        showDialog()
                    }
                    Status.ERROR -> {
                        // Hide Progress
                        // Show Error
                        Log.e("TAG", "ERROR: " + it.message)
                        hideDialog()
                    }
                    Status.SUCCESS -> {
                        hideDialog()
                        Log.e("TAG", "setup: " + it.data)
                        // Data
                        eventAdapter.setData(it.data!!.eventDetails)
                    }
                }
            }
        }
    }

    private fun initRecyclerview() {
        eventAdapter = EventsAdapter(ArrayList(),this,requireContext())

        binding.eventsList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = eventAdapter
        }
    }

    override fun event(event: EventDetails) {
        val directions = EventsListFragmentDirections.actionEventsListFragmentToEventDetailsFragment(eventDetails = event)
        navController.navigate(directions)
    }

}