package com.example.temp.history.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.temp.databinding.ItemHistoryRecyclerBinding

class HistoryAdapter(val context: FragmentActivity?): RecyclerView.Adapter<ViewHolder>() {

    var historyDatas = mutableListOf<HistoryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemHistoryRecyclerBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(context, itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(historyDatas[position])
    }

    override fun getItemCount(): Int = historyDatas.size


    class ViewHolder(val context: FragmentActivity?, val binding: ItemHistoryRecyclerBinding): RecyclerView.ViewHolder(binding.root){

        private val img : ImageView = binding.imgHistory
        private val name: TextView = binding.txtCountryName
        private val date: TextView = binding.txtCountryDate

        fun bind(item: HistoryData){
            name.text = item.name
            date.text = item.date
        }

    }

}