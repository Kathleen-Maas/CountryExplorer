package com.example.countryexplorer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import com.example.countryexplorer.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class MainScreenFragment : Fragment() {

//    private val database by lazy { AppDatabase.getDatabase(requireContext()) }
//    private val dao by lazy { database.countryDao() }
//    private val imp by lazy { RepositoryImp(ApiProvider.createService(ApiService::class.java), dao) }
//    private val myVamp: MainScreenVamp by viewModels<MainScreenVamp> { MyViewModelFactory(imp) }
    private val myVamp : MainScreenVamp by viewModels()


    lateinit var ui: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentMainBinding.inflate(inflater, container, false)
        ui = binding
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Debug", "Hi")

        //making image loader
        val imageLoader = ImageLoader.Builder(requireContext())
            .componentRegistry {
                add(SvgDecoder(requireContext()))
            }
            .build()

        Coil.setImageLoader(imageLoader)

        ui.refreshButton.setOnClickListener(){
            myVamp.onRefreshTapped()
        }

        myVamp.countries.observe(viewLifecycleOwner){ countries ->
            Log.d("Debug", "Size of List is " + countries.size)
            if(countries.isEmpty()){
                ui.locationRecycler.isVisible = false
                ui.errorMessage.isVisible = true
            } else {
                ui.locationRecycler.isVisible = true
                ui.errorMessage.isVisible = false

                val adapter = myVamp.countries.value?.let { CountriesAdapter(it) }
                ui.locationRecycler.adapter = adapter

                if (adapter != null) {
                    adapter.countryCodeChannel
                        .consumeAsFlow()
                        .onEach { code -> navigate(code) }
                        .launchIn(lifecycleScope)
                }
            }
        }
    }

    private fun navigate(code: Int){
        val action = MainScreenFragmentDirections.actionMainFragmentToCountryFragment(code)
        findNavController().navigate(action)
    }
}