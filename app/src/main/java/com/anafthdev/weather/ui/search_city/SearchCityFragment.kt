package com.anafthdev.weather.ui.search_city

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
import com.anafthdev.weather.databinding.FragmentSearchCityBinding
import com.anafthdev.weather.foundation.extension.deviceLocale
import com.anafthdev.weather.foundation.interfaces.OnItemClickListener
import com.anafthdev.weather.model.geocoding.City
import com.anafthdev.weather.ui.search_city.data.SearchCityRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SearchCityFragment : Fragment() {
	
	private lateinit var binding: FragmentSearchCityBinding
	private lateinit var navController: NavController
	private lateinit var searchCityRecyclerAdapter: SearchCityRecyclerAdapter
	
	private val searchCityViewModel: SearchCityViewModel by viewModels()
	
	private var state: SearchCityState = SearchCityState()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setHasOptionsMenu(true)
	}
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = FragmentSearchCityBinding.inflate(inflater)
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		navController = findNavController()
		
		(requireActivity() as AppCompatActivity).supportActionBar?.hide()
		
		searchCityRecyclerAdapter = SearchCityRecyclerAdapter(object : OnItemClickListener<City> {
			override fun onItemClick(item: City) {
				Timber.i("lisssss: $item")
				searchCityViewModel.dispatch(
					SearchCityAction.InsertCity(
						listOf(item)
					)
				)
				
				navController.popBackStack()
			}
		})
		
		lifecycleScope.launch {
			searchCityViewModel.state.collect { newState ->
				Timber.i("newState: $newState")
				state = newState
				updateUI()
			}
		}
		
		initUI()
		updateUI()
	}
	
	private fun initUI() {
		binding.homeSearchCityFragment.setOnClickListener {
			navController.popBackStack()
		}
		
		binding.rvCitiesSearchCityFragment.apply {
			layoutManager = LinearLayoutManager(requireContext())
			adapter = searchCityRecyclerAdapter
		}
		
		binding.searchViewSearchCityFragment.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String): Boolean {
				return true
			}
			
			override fun onQueryTextChange(newText: String): Boolean {
				Timber.i("query: $newText")
				searchCityViewModel.dispatch(
					SearchCityAction.Search(
						q = newText,
						language = deviceLocale.language
					)
				)
				return true
			}
		})
	}
	
	private fun updateUI() {
		searchCityRecyclerAdapter.submitList(state.cities)
	}
	
}