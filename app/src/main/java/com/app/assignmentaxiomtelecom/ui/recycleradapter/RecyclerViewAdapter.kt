package com.app.assignmentaxiomtelecom.ui.recycleradapter

import CatalogItem
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter (private val list: ArrayList<CatalogItem>)
    : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product: CatalogItem = list[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = list.size

}