package com.anafthdev.weather.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.anafthdev.weather.R
import com.anafthdev.weather.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

	private lateinit var binding: FragmentHomeBinding
	private lateinit var navController: NavController
	
	private val homeViewModel: HomeViewModel by viewModels()
	
	private var state: HomeState = HomeState()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setHasOptionsMenu(true)
	}
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = FragmentHomeBinding.inflate(inflater)
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		navController = findNavController()
		
		(requireActivity() as AppCompatActivity).supportActionBar?.let {
			it.setDisplayHomeAsUpEnabled(false)
			it.setDisplayShowHomeEnabled(false)
		}
		
		lifecycleScope.launch {
			homeViewModel.state.collect { newState ->
				state = newState
				updateUI()
			}
		}
		
		binding.buttonFetch.setOnClickListener {
			homeViewModel.dispatch(
				HomeAction.GetWeather(
					lat = -6.474009961699578,
					lon = 106.79156490092377,
					apiKey = getString(R.string.weather_api_key)
				)
			)
		}
	}
	
	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		super.onCreateOptionsMenu(menu, inflater)
		
		inflater.inflate(R.menu.home_fragment, menu)
	}
	
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.selectCity_HomeFragmentMenu -> navController.navigate(R.id.selectCityFragment)
		}
		
		return true
	}
	
	private fun updateUI() {
		binding.tex.text = state.weather.name
	}
}