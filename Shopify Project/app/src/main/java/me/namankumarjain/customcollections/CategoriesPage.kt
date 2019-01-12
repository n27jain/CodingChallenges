package me.namankumarjain.customcollections
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request


class CategoriesPage : AppCompatActivity() {


    private var client = OkHttpClient()
    //private val textView: TextView = findViewById(R.id.specialBox)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_page)
        val x = run("https://shopicruit.myshopify.com/admin/collects.json?collection_id=68424466488&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
        println(message = x)
    }

    private fun run(url: String): String? {
        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).execute().use { response -> return response.body()?.string() }
    }

}
