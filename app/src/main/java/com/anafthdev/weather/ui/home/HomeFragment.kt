package com.anafthdev.weather.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.anafthdev.weather.R
import com.anafthdev.weather.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class HomeFragment : Fragment() {

	private lateinit var binding: FragmentHomeBinding
	
	private var param1: String? = null
	private var param2: String? = null
	
	private val homeViewModel: HomeViewModel by viewModels()
	
	private var state: HomeState = HomeState()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setHasOptionsMenu(true)
		arguments?.let {
			param1 = it.getString(ARG_PARAM1)
			param2 = it.getString(ARG_PARAM2)
		}
	}
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = FragmentHomeBinding.bind(inflater.inflate(R.layout.fragment_home, container, false))
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		lifecycleScope.launch {
			homeViewModel.state.collect {
				state = it
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
			R.id.selectCity_HomeFragmentMenu -> {}
		}
		
		return true
	}
	
	private fun updateUI() {
		binding.tex.text = state.weather.name
	}
	
	companion object {
		
		@JvmStatic
		fun newInstance(param1: String, param2: String) =
			HomeFragment().apply {
				arguments = Bundle().apply {
					putString(ARG_PARAM1, param1)
					putString(ARG_PARAM2, param2)
				}
			}
	}
}