package com.example.countryexplorer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountriesAdapter(private val mCountries: List<Country>): RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView){
        val nameTextView = itemView.findViewById<TextView>(R.id.countryName)
        val populationTextView = itemView.findViewById<TextView>(R.id.countryPopulation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val countryView = inflater.inflate(R.layout.country_item,parent,false)
        return ViewHolder(countryView)
    }

    override fun onBindViewHolder(viewHolder: CountriesAdapter.ViewHolder, position: Int) {
        val country: Country = mCountries.get(position)
        val nameText = viewHolder.nameTextView
        nameText.text = country.name
        val populationText = viewHolder.populationTextView
        populationText.text = "Population: "+country.population
    }

    override fun getItemCount(): Int {
        return mCountries.size
    }
}