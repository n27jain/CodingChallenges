package com.example.javaraja.shopifyandroidfinalchallenge
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.ProgressBar
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_collection_details_page.*
import kotlinx.android.synthetic.main.card_products.view.*
import okhttp3.*
import java.io.IOException


class CollectionDetailsPage : AppCompatActivity() {

    var TAG = "DebugMessage"
    var client = OkHttpClient()
    var jsonParseTool = GsonBuilder().create()
    var title:String = ""
    var imageUrl:String = ""
    var body_html:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection_details_page)
        recyclerView_Collections_Data.layoutManager = LinearLayoutManager(this)

        title = intent.getStringExtra(CustomViewHolder.COLLECTION_TITLE)
        imageUrl = intent.getStringExtra(CustomViewHolder.COLLECTION_URL) // image URL
        body_html = intent.getStringExtra(CustomViewHolder.BODY_HTML)

        // Set up the card for feeling adventurous
        collection_title_card.text = title
        collection_html_card.text = body_html
        Picasso.get().load(imageUrl).into(collection_image_card);

        var id = intent.getStringExtra(CustomViewHolder.COLLECTION_ID)
        var apiPartOne: String = "https://shopicruit.myshopify.com/admin/collects.json?collection_id="
        var apiPartTwo: String = "&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6"
        var apiConcat = apiPartOne+id+apiPartTwo

        parseJson(apiConcat)
    }


    private fun parseJson(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call, response: Response?) {
                var bodyOfProducts = response?.body()?.string()

                var collectionJsonObject = jsonParseTool.fromJson(bodyOfProducts, Products::class.java)

                val productsWithDetails = ArrayList<ProductDetails>()


                   for(product in collectionJsonObject.collects){
                       var concatProductUrl = "https://shopicruit.myshopify.com/admin/products.json?ids=" + product.product_id+ "&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6"

                       val newRequest = Request.Builder()
                           .url(concatProductUrl)
                           .build()

                       var newResponse = client.newCall(newRequest).execute()

                       var bodyOfProductDetails = newResponse?.body()?.string()

                       println(bodyOfProductDetails)
                       var productJsonObject = jsonParseTool.fromJson(bodyOfProductDetails, ProductDetails::class.java)
                       productsWithDetails.add(productJsonObject)
                   }
                runOnUiThread {
                    recyclerView_Collections_Data.adapter = CollectionDetailAdapter(productsWithDetails, title, imageUrl)
                }
            }
        })
    }
}
class Products(val collects: List<Product>)
class Product(val product_id:Long )

class ProductDetails(val products: List<VariantProducts> )
class VariantProducts(val id: Float, val title: String, val variants:List<Variant>)
class Variant(val inventory_quantity:Int)
