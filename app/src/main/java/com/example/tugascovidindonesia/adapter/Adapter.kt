package com.example.tugascovidindonesia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugascovidindonesia.R
import com.example.tugascovidindonesia.model.DataItem
import kotlinx.android.synthetic.main.list_recycler.view.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class Adapter(private val province: ArrayList<DataItem>, private val clicklistener: (DataItem) -> Unit):
        RecyclerView.Adapter<ProvinceViewHolder>(),Filterable {

    var provinceCount = ArrayList<DataItem>()
    init {
        provinceCount = province
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_recycler,parent,false)
        return ProvinceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return provinceCount.size
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        holder.bind(provinceCount[position],clicklistener)
    }

    override fun getFilter(): Filter{
        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val search = p0.toString()
                provinceCount = if (search.isEmpty()) {
                    province
                }else{
                    val result = ArrayList<DataItem>()
                    for (row in province){
                        val search = row.provinsi?.toLowerCase(Locale.ROOT)?:""
                        if (search.contains(search.toLowerCase(Locale.ROOT))){
                            result.add(row)
                        }
                    }
                    result
                }
                val result = FilterResults()
                result.values = provinceCount
                return result
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                provinceCount = p1?.values as ArrayList<DataItem>
                notifyDataSetChanged()
            }

        }
    }
}

class ProvinceViewHolder(itemview : View):RecyclerView.ViewHolder(itemview) {
    fun bind(data:DataItem, clicklistener: (DataItem) -> Unit) {
        val name:TextView = itemView.namaProvinsi
        val case:TextView = itemView.kasusProvinsi

        val formater:NumberFormat = DecimalFormat("#,###")

        name.text = data.provinsi
        case.text = formater.format(data.kasusPosi?.toDouble())
    }
}