package com.example.javaraja.shopifyandroidfinalchallenge

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_products.view.*

class CollectionDetailAdapter(private val productDetails: ArrayList<ProductDetails>, val title: String, private val url: String) :
    RecyclerView.Adapter<DetailsViewHolder>() {
    var counter = 0

    override fun getItemCount(): Int {
        // sets the counter size for the for loop that produces each disposable card.
        return productDetails.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        // inflate the disposable object created in the card_collection.xml
        val viewInflateLocation = LayoutInflater.from(parent.context)
        val rowHolder = viewInflateLocation.inflate(R.layout.card_products, parent, false)
        return DetailsViewHolder(rowHolder)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        // modify the view / populate it to dynamic data
        val thisCollection = productDetails[position].products[0] // there will only be one product per item
        val productTitle = thisCollection.title
        holder.view.collectionName?.text = title
        holder.view.productName?.text = productTitle
        var inventoryItemTotal = 0
        for (variantsOfProduct in thisCollection.variants) { // check every variant
            inventoryItemTotal += variantsOfProduct.inventory_quantity
        }
        holder.view.numberOfInventoryItems?.text = inventoryItemTotal.toString()
        Picasso.get().load(url).into(holder.view.collectionImage)
        counter++
    }
}

class DetailsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    // Access the properties of the view
}