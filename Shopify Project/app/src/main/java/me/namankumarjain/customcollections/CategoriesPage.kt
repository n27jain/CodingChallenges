package me.namankumarjain.customcollections
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_categories_page.*
import okhttp3.*
import java.io.IOException


class CategoriesPage : AppCompatActivity() {


    private var client = OkHttpClient()
    private var jsonParseTool = GsonBuilder().create()
    var TAG = "DebugMessage"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_page)

            val text = retrieveJson("https://shopicruit.myshopify.com/admin/custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
            val checkMeOut = parseJson(text)

            pressRecycle.setOnClickListener {
                val intent = Intent(this, CustomsCollectionListPage::class.java)
                intent.putExtra("key", "Kotlin")
                startActivity(intent)
            }
    }

    private fun retrieveJson(url: String): String? {

        val request = Request.Builder()
                .url(url)
                .build()
        var body:String? = null
        client.newCall(request).enqueue(object : Callback{
            override fun onResponse(call: Call, response: Response?) {
                body = response?.body()?.string()
            }
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "Failure")
                body = null
            }
        })
        return body
    }

    private fun parseJson(jsonString: String?): CustomCollections? {
        if(jsonString!=null) {
            return jsonParseTool.fromJson(jsonString, CustomCollections::class.java)
        }
        return null
    }

}

class CustomCollections( val custom_collections: List<Collection> )

class Collection(val id: Long, val title: String)