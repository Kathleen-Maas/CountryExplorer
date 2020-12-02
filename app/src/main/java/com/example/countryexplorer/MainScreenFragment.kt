package com.example.countryexplorer

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import com.example.countryexplorer.databinding.FragmentMainBinding
import kotlinx.coroutines.Job
import viewBinding
import java.util.concurrent.ThreadLocalRandom.current

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainScreenFragment : Fragment() {

    private val database = AppDatabase.getDatabase(requireContext())
    private val dao = database.countryDao()
    private val imp = RepositoryImp(ApiProvider.createService(ApiService::class.java))
    private val myVamp: MainScreenVamp by viewModels<MainScreenVamp> { MyViewModelFactory(imp) }
    private val ui: FragmentMainBinding by viewBinding { FragmentMainBinding.bind(it) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
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
            if(countries.isEmpty()){
                ui.locationRecycler.isVisible = false
                ui.errorMessage.isVisible = true
            } else {
                ui.locationRecycler.isVisible = true
                ui.errorMessage.isVisible = false

                val adapter = myVamp.countries.value?.let { CountriesAdapter(it) }
                ui.locationRecycler.adapter = adapter
            }
        }
    }
}