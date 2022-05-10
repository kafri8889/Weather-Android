package com.anafthdev.weather.ui.select_city

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anafthdev.weather.R
import com.anafthdev.weather.databinding.FragmentSelectCityBinding
import com.anafthdev.weather.foundation.interfaces.OnItemClickListener
import com.anafthdev.weather.model.geocoding.City
import com.anafthdev.weather.ui.search_city.data.SearchCityRecyclerAdapter
import com.anafthdev.weather.ui.select_city.data.SelectCityRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SelectCityFragment : Fragment() {
	
	private lateinit var binding: FragmentSelectCityBinding
	private lateinit var navController: NavController
	private lateinit var selectCityRecyclerAdapter: SelectCityRecyclerAdapter
	
	private val selectCityViewModel: SelectCityViewModel by viewModels()
	
	private var state: SelectCityState = SelectCityState()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setHasOptionsMenu(true)
	}
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = FragmentSelectCityBinding.inflate(inflater)
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		navController = findNavController()
		
		selectCityRecyclerAdapter = SelectCityRecyclerAdapter(object : OnItemClickListener<City> {
			override fun onItemClick(item: City) {
				selectCityViewModel.dispatch(
					SelectCityAction.SetSelectedCity(
						city = item
					)
				)
			}
		})
		
		(requireActivity() as AppCompatActivity).supportActionBar?.let {
			it.show()
			it.setDisplayHomeAsUpEnabled(true)
			it.setDisplayShowHomeEnabled(true)
		}
		
		lifecycleScope.launch {
			selectCityViewModel.state.collect { newState ->
				state = newState
				requireActivity().runOnUiThread {
					updateUI()
				}
			}
		}
		
		initUI()
	}
	
	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.select_city_fragment, menu)
		super.onCreateOptionsMenu(menu, inflater)
	}
	
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		
		when (item.itemId) {
			R.id.search_SelectCityFragment -> navController.navigate(R.id.searchCityFragment)
			android.R.id.home -> navController.popBackStack()
		}
		
		return true
	}
	
	private fun initUI() {
		binding.addCitySelectCityFragment.setOnClickListener {
			navController.navigate(R.id.mapFragment)
		}
		
		binding.rvCitiesSelectCityFragment.apply {
			adapter = selectCityRecyclerAdapter
			layoutManager = LinearLayoutManager(requireContext())
		}
	}
	
	private fun updateUI() {
		selectCityRecyclerAdapter.setSelectedCity(state.selectedCity)
		selectCityRecyclerAdapter.submitList(state.availableCities)
	}
	
}