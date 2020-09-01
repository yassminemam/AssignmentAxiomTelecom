package com.app.assignmentaxiomtelecom.ui.recycleradapter

import CatalogItem
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.assignmentaxiomtelecom.R
import com.squareup.picasso.Picasso

class ProductViewHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_product, parent, false)) {
    private var productBrandTV: TextView? = null
    private var productPrice: TextView? = null
    private var productIV: ImageView? = null

    init {
        productBrandTV = itemView.findViewById(R.id.tv_product_brand)
        productPrice = itemView.findViewById(R.id.tv_product_price)
        productIV = itemView.findViewById(R.id.iv_item_img)
    }

    fun bind(product: CatalogItem) {
        productBrandTV?.text = product.brand
        productPrice?.text = product.priceEur.toString()
        Picasso.get().load(product.picture).into(productIV);
    }

}