package com.example.javaraja.shopifyandroidfinalchallenge

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_collections.view.*

class CollectionsAdapter(val collection: CustomCollections) : RecyclerView.Adapter<CustomViewHolder>() {


    override fun getItemCount(): Int {
        // sets the counter size for the for loop that produces each disposable card.
        return collection.custom_collections.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // inflate the disposable object created in the card_collection.xml
        val viewInflateLocation = LayoutInflater.from(parent.context)
        val rowHolder = viewInflateLocation.inflate(R.layout.card_collections, parent, false)
        return CustomViewHolder(rowHolder, collection, viewType)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        // modify the view / populate it to dynamic data
        holder.index = position // update the index of the holder with the correct index.
        val thisCollection = collection.custom_collections[position]
        val title = thisCollection.title
        holder.view.titleOfCollection.text = title
        val outWards = thisCollection.image.src
    }
}

class CustomViewHolder(val view: View, var collection: CustomCollections, var index: Int) :
    RecyclerView.ViewHolder(view) {
    // Access the properties of the view
    companion object { // acts like enum for strings
        val COLLECTION_TITLE = "title"
        val COLLECTION_ID = "id"
        val COLLECTION_URL = "url"
        val BODY_HTML = "body_html"
    }
    init {
        view.setOnClickListener {
            val intent = Intent(view.context, CollectionDetailsPage::class.java)
            val thisCollection = collection.custom_collections.get(index)
            intent.putExtra(COLLECTION_TITLE, thisCollection.title)
            intent.putExtra(COLLECTION_ID, thisCollection.id.toString())
            intent.putExtra(COLLECTION_URL, thisCollection.image.src)
            intent.putExtra(BODY_HTML, thisCollection.body_html)
            view.context.startActivity(intent) // view.context is a Kotlin necessary syntax.*/
        }
    }
}