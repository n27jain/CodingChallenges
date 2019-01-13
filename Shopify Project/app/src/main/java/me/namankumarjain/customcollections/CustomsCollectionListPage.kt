package me.namankumarjain.customcollections

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_customs_collection_list_page.*

class CustomsCollectionListPage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customs_collection_list_page)

        recyclerViewCustomCollections.layoutManager =LinearLayoutManager(this)
        recyclerViewCustomCollections.adapter = CollectionsAdapter()
    }
}
