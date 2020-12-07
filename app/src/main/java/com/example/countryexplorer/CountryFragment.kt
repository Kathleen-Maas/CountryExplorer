package com.example.countryexplorer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.countryexplorer.databinding.FragmentCountryBinding
import com.example.countryexplorer.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class CountryFragment : Fragment() {

//    private val database by lazy { AppDatabase.getDatabase(requireContext()) }
//    private val dao by lazy { database.countryDao() }
//    private val imp by lazy { RepositoryImp(ApiProvider.createService(ApiService::class.java), dao) }
//    private val myVamp: CountryVamp by viewModels<CountryVamp> { MyViewModelFactoryDetail(imp) }
    private val myVamp : CountryVamp by viewModels()
    lateinit var ui: FragmentCountryBinding
    val args : CountryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentCountryBinding.inflate(inflater, container, false)
        ui = binding
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val country = myVamp.getCountry(args.code)
        ui.Name.text = country.name
        ui.Area.text = "Area: " + country.area + " sq km"
        ui.alphaCode.text = "Alpha Code: " + country.alpha3Code
        ui.capital.text = "Capital: " + country.capital
        ui.population.text = "Population: " + NumberFormat.getNumberInstance(Locale.US).format(country.population)
        ui.region.text = "Region: " + country.subregion
        ui.imageView.load(country.flag)

        ui.backButton.setOnClickListener(){
            findNavController().navigate(R.id.action_CountryFragment_to_MainFragment)
        }



    }
}