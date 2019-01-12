package me.namankumarjain.customcollections
import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_categories_page.*
import okhttp3.*
import java.io.IOException


class CategoriesPage : AppCompatActivity() {


    private var client = OkHttpClient()
    var TAG = "DebugMessage"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_page)
        val text = retrieveJson("https://shopicruit.myshopify.com/admin/custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")

        specialBox.text = text.toString()
    }

    private fun retrieveJson(url: String): String? {
        val request = Request.Builder()
                .url(url)
                .build()
        var body:String? = null
        client.newCall(request).enqueue(object : Callback{
            override fun onResponse(call: Call, response: Response?) {
                body = response?.body()?.string()
                Log.d(TAG, body)
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "Failure")
                body = null
            }

        })
        return body
    }

}
