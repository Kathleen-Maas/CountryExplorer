package com.example.countryexplorer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.channels.Channel
import java.text.NumberFormat
import java.util.*

class CountriesAdapter(private val mCountries: List<Country>): RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    val countryCodeChannel = Channel<Int>(1)

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView){
        val nameTextView = itemView.findViewById<TextView>(R.id.countryName)
        val populationTextView = itemView.findViewById<TextView>(R.id.countryPopulation)
        val flagImageView = itemView.findViewById<ImageView>(R.id.flagView)
        val detailButton = itemView.findViewById<Button>(R.id.detailsButton)

        fun bind(country : Country){
            nameTextView.text = country.name
            populationTextView.text = "Population: "+ NumberFormat.getNumberInstance(Locale.US).format(country.population)
            flagImageView.load(country.flag)

            detailButton.setOnClickListener(){
                countryCodeChannel.offer(country.numericCode)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val countryView = inflater.inflate(R.layout.country_item,parent,false)
        return ViewHolder(countryView)
    }

    override fun onBindViewHolder(viewHolder: CountriesAdapter.ViewHolder, position: Int) {
        viewHolder.bind(mCountries.get(position))
    }

    override fun getItemCount(): Int {
        return mCountries.size
    }
}