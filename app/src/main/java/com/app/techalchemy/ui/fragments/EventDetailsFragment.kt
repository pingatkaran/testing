package com.app.techalchemy.ui.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.techalchemy.R
import com.app.techalchemy.adapter.TagAdapter
import com.app.techalchemy.databinding.FragmentEventDetailsBinding
import com.app.techalchemy.utils.Status
import com.app.techalchemy.viewModels.EventListsViewModel
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_event.*
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class EventDetailsFragment : BaseFragment<FragmentEventDetailsBinding>() {

    val args: EventDetailsFragmentArgs by navArgs()

    private val viewModel: EventListsViewModel by viewModels()
    private lateinit var moviesAdapter: TagAdapter
    private lateinit var dialog: BottomSheetDialog
    private lateinit var successDialog: BottomSheetDialog
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEventDetailsBinding
        get() = FragmentEventDetailsBinding::inflate

    override fun setup() {

        lifecycleScope.launchWhenStarted {
            viewModel.purchaseState.collect {
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
                        SuccessDialog(it.data!!.purchase.id.toString())
                        dialog.dismiss()
                        // Data
                    }
                }
            }
        }


        if (args.eventDetails!!.isPartnered) {
            binding.txtIsPartner.visibility = View.VISIBLE
            binding.txtIsPartner.text = "Partnered"
        } else {
            binding.txtIsPartner.visibility = View.INVISIBLE
        }

        Glide.with(requireActivity())
            .load(args.eventDetails!!.mainImage)
            .centerCrop()
            .into(binding.imgView)

        binding.txtSport.text = args.eventDetails!!.sport.toString()
        binding.eventName.text = args.eventDetails!!.name.toString()
        binding.txtprice.text = "£" + args.eventDetails!!.price.toString()
        binding.dateTime.text = args.eventDetails!!.dateTime.toString()
        binding.totalPrize.text = "£" + args.eventDetails!!.totalPrize.toString()
        binding.ticketsSold.text =
            args.eventDetails!!.ticketsSold.toString() + "/" + args.eventDetails!!.maxTickets.toString() + " attending"
        binding.txtDescription.text = args.eventDetails!!.description.toString()
        binding.btnPrice.text = "£" + args.eventDetails!!.price.toString() + " - I'm IN!"
        binding.txtVenue.text = args.eventDetails!!.venueInformation.toString()
        binding.txtCreatorName.text = args.eventDetails!!.eventCreator.toString()
        binding.txtLocation.text = args.eventDetails!!.location.toString()
        binding.teamInfo.text = args.eventDetails!!.teamInformation.toString()
        val arr = args.eventDetails!!.tags.replace("\"", "").replace("[", "").replace("]", "")
        val lstValues: List<String> = arr.split(",")

        moviesAdapter = TagAdapter(lstValues)
        val mLayoutManager = LinearLayoutManager(requireActivity())
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.tagsList.layoutManager = mLayoutManager
        binding.tagsList.itemAnimator = DefaultItemAnimator()
        binding.tagsList.adapter = moviesAdapter


        binding.btnPrice.setOnClickListener {
            dialog = BottomSheetDialog(requireActivity())
            val view = layoutInflater.inflate(R.layout.layout_purchase, null)
            val dialogTitle = view.findViewById<AppCompatTextView>(R.id.dialogTitle)
            val dialogDateTime = view.findViewById<AppCompatTextView>(R.id.dialogDateTime)

            val dialogTxtlocation = view.findViewById<AppCompatTextView>(R.id.dialogTxtlocation)
            val dialogTotalPrice = view.findViewById<AppCompatTextView>(R.id.dialogTotalPrice)
            val dialogBtnPay = view.findViewById<AppCompatButton>(R.id.dialogBtnPay)
            val dialogClose = view.findViewById<AppCompatImageView>(R.id.dialogClose)
            dialogTitle.text = args.eventDetails!!.name
            dialogDateTime.text = args.eventDetails!!.dateTime
            dialogTxtlocation.text = args.eventDetails!!.location
            dialogTotalPrice.text = "£" + args.eventDetails!!.price.toString()

            dialogBtnPay.setOnClickListener {
                viewModel.postEventPuchase(dateTime = args.eventDetails!!.dateTime, purchaseAmt =args.eventDetails!!.price, eventId = args.eventDetails!!.id )
            }

            dialogClose.setOnClickListener {
                successDialog.dismiss()
            }

            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(true)
            dialog.setContentView(view)
            dialog.show()
        }

    }

    fun SuccessDialog(bookingID : String) {
        successDialog = BottomSheetDialog(requireActivity())
        val view = layoutInflater.inflate(R.layout.layout_success, null)

        val txtBookingId = view.findViewById<AppCompatTextView>(R.id.txtBookingId)
        val dialogBtnClose = view.findViewById<AppCompatButton>(R.id.dialogBtnClose)
        val close = view.findViewById<AppCompatImageView>(R.id.close)

        close.setOnClickListener {
            successDialog.dismiss()
        }

        txtBookingId.text = "#" + bookingID

        dialogBtnClose.setOnClickListener {
            successDialog.dismiss()
        }

        successDialog.setCancelable(true)
        successDialog.setCanceledOnTouchOutside(true)
        successDialog.setContentView(view)
        successDialog.show()
    }

}