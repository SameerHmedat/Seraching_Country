package com.example.searchingapplication21_6_2022

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.element_items_row.view.*

class ElementAdapter( var newList:List<Element>):
    RecyclerView.Adapter<ElementAdapter.ElementViewHolder>() {

    private lateinit var mListener:OnItemClickedListener

    interface  OnItemClickedListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener:OnItemClickedListener){
        mListener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.element_items_row
            ,parent,false)

        return ElementViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val currentItem: Element = newList[position]
        holder.bind(currentItem)

    }

    override fun getItemCount(): Int {
        return newList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterList: ArrayList<Element>) {
        newList= filterList
        notifyDataSetChanged()
    }

    class ElementViewHolder(itemView: View,mListener: OnItemClickedListener ):
        RecyclerView.ViewHolder(itemView) {

        fun bind(info: Element) {
            itemView.btn_number.text = info.name
        }

        init {
            itemView.btn_number.setOnClickListener {
                mListener.onItemClick(adapterPosition)
            }}
    }
}

