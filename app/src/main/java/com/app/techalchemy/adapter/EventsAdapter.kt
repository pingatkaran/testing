package com.app.techalchemy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.techalchemy.interfaces.selectedEventListener
import com.app.techalchemy.databinding.ItemEventBinding
import com.app.techalchemy.model.EventDetails
import com.bumptech.glide.Glide

class EventsAdapter(private var posts: List<EventDetails>, var sendTimeInterface: selectedEventListener,private val context: Context) : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>(){

    private lateinit var binding: ItemEventBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):EventsViewHolder {
       binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EventsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {

        if (posts[position].isPartnered){
            binding.txtIsPartner.visibility = View.VISIBLE
            binding.txtIsPartner.text = "Partnered"
        }else{
            binding.txtIsPartner.visibility = View.VISIBLE
            binding.txtIsPartner.text = "Not Partnered"
        }

        binding.txtSport.text = posts[position].sport.toString()
        binding.eventName.text = posts[position].name.toString()
        binding.txtprice.text = "£"+ posts[position].price.toString()

        binding.root.setOnClickListener {
            sendTimeInterface.event(posts[position])
        }

        binding.dateTime.text = posts[position].dateTime
        binding.totalPrize.text = "£" + posts[position].totalPrize.toString()
        binding.ticketsSold.text =
            posts[position].ticketsSold.toString() + "/" + posts[position].maxTickets.toString() + " attending total"

        binding.txtLocation.text = posts[position].location.toString()

        Glide.with(context)
            .load(posts[position].mainImage)
            .centerCrop()
            .into(binding.imgView)
    }

    override fun getItemCount(): Int = posts.size


    class EventsViewHolder(view: View) : RecyclerView.ViewHolder(view)

    fun setData(posts: List<EventDetails>){
        this.posts = posts
        notifyDataSetChanged()
    }

}