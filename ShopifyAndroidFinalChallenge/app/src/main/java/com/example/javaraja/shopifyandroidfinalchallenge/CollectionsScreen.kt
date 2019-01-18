package com.example.javaraja.shopifyandroidfinalchallenge

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_collections_screen.*
import okhttp3.*
import java.io.IOException

class CollectionsScreen : AppCompatActivity() {

    private var client = OkHttpClient()
    var jsonParseTool: Gson = GsonBuilder().create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Custom Collections List Page"
        setContentView(R.layout.activity_collections_screen)
        recyclerViewCustomCollections.layoutManager = LinearLayoutManager(this)

        parseJson("https://shopicruit.myshopify.com/admin/custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    }

    private fun parseJson(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response?) {
                val body = response?.body()?.string()
                val jsonObject = jsonParseTool.fromJson(body, CustomCollections::class.java)

                runOnUiThread {
                    recyclerViewCustomCollections.adapter = CollectionsAdapter(jsonObject)
                }
            }
            override fun onFailure(call: Call, e: IOException) {
            }
        })
    }
}

class CustomCollections(val custom_collections: List<Collection>)
class Collection(val id: Long, val title: String, val image: Image, val body_html:String)
class Image(val src: String)
