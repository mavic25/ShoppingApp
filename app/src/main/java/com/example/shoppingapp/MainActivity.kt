package com.example.shoppingapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Tag

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitBuilder= Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)


        val retrofitData=retrofitBuilder.getProductData()

        retrofitData.enqueue(object : Callback<MyData?> {

            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                //What you want to do when API call is a success
                var responseBody= response.body()
                val productArray=responseBody?.products

                val tvProduct = findViewById<TextView>(R.id.tvName)
                tvProduct.text=productArray.toString()

            }


            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                //if API call fails
                Log.d(TAG, "onFailure: " + t.message)
            }
        })



    }
}