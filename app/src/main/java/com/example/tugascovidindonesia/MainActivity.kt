package com.example.tugascovidindonesia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.example.tugascovidindonesia.adapter.Adapter
import com.example.tugascovidindonesia.model.DataItem
import com.example.tugascovidindonesia.model.ResponseModel
import com.example.tugascovidindonesia.network.ApiService
import com.example.tugascovidindonesia.network.RetrofitBuilder.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var adapterX : Adapter
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapterX.filter.filter(p0)
                return false
            }

        })

        getProvince()
    }

    private fun getProvince() {
        val api = retrofit.create(ApiService::class.java)
        api.getAllProvince().enqueue(object:Callback<ResponseModel>{
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Error",Toast.LENGTH_SHORT)
            }

            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                recyclerId.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapterX = Adapter(response.body()!!.data as ArrayList<DataItem>)
                    {}
                    adapter = adapterX
                }
            }

        })
    }
}