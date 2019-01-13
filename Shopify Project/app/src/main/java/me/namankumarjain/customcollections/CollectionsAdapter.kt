package me.namankumarjain.customcollections

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CollectionsAdapter(val collection:CustomCollections): RecyclerView.Adapter<CustomViewHolder>(){


    override fun getItemCount(): Int {
        return collection.custom_collections.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val viewInflateLocation = LayoutInflater.from(parent.context)
        val rowHolder = viewInflateLocation.inflate(R.layout.card_collections, parent, false)
        return CustomViewHolder (rowHolder)
    }



}

class CustomViewHolder(v: View): RecyclerView.ViewHolder(v){

}