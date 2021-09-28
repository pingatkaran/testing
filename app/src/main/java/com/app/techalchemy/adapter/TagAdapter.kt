package com.app.techalchemy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.app.techalchemy.R

class TagAdapter(private var moviesList: List<String>) : RecyclerView.Adapter<TagAdapter.MyViewHolder>() {
   inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      var title: AppCompatTextView = view.findViewById(R.id.tagtext)
   }
   @NonNull
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val itemView = LayoutInflater.from(parent.context)
      .inflate(R.layout.chip_item, parent, false)
      return MyViewHolder(itemView)
   }
   override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      val movie = moviesList[position]
      holder.title.text = "#"+movie
   }
   override fun getItemCount(): Int {
      return moviesList.size
   }
}
