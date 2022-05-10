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
import com.anafthdev.weather.R
import com.anafthdev.weather.databinding.FragmentSelectCityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SelectCityFragment : Fragment() {
	
	private lateinit var binding: FragmentSelectCityBinding
	private lateinit var navController: NavController
	
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
		
		(requireActivity() as AppCompatActivity).supportActionBar?.let {
			it.show()
			it.setDisplayHomeAsUpEnabled(true)
			it.setDisplayShowHomeEnabled(true)
		}
		
		lifecycleScope.launch {
			selectCityViewModel.state.collect { newState ->
				state = newState
			}
		}
		
		initUI()
	}
	
	private fun initUI() {
		binding.rvCitiesSelectCityFragment.setOnClickListener {
			navController.navigate(R.id.mapFragment)
		}
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
	
}