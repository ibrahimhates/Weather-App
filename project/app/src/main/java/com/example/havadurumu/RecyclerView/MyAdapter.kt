package com.example.havadurumu.RecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.havadurumu.R
import com.example.havadurumu.api.models.Result

class MyAdapter(private val listener: OnItemClickListener,private val data: List<Result>, val context: Context) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val day: TextView = view.findViewById(R.id.day)
        val date: TextView = view.findViewById(R.id.date)
        val degreeDay: TextView = view.findViewById(R.id.degreeDay)
        val degreeNight: TextView = view.findViewById(R.id.degreeNight)
        val status: TextView = view.findViewById(R.id.status)
        val weatherIcon: ImageView = view.findViewById(R.id.weatherIcon)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data[position]
        holder.date.text = item.date
        holder.day.text = item.day
        holder.status.text = item.status
        holder.degreeDay.text = item.degree+"°C"
        holder.degreeNight.text = item.night+"°C"
        var imageURL = item.icon
        Glide.with(context)
            .load(imageURL)
            .into(holder.weatherIcon);
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
open interface OnItemClickListener {
    fun onItemClick(position: Int)
}