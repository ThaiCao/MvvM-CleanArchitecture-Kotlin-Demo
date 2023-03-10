package com.example.structure.feature.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.structure.BuildConfig
import com.example.structure.R
import com.example.structure.databinding.FragmentHomeBinding
import com.example.structure.feature.home.viewholder.HomeViewHolderFactory
import com.example.structure.presentation.feature.home.HomeViewModel
import com.example.structure.uibase.adapter.DifferentAdapter
import com.example.structure.uibase.extend.viewBinding
import com.example.structure.uibase.fragment.BaseFragment
import com.example.structure.uibase.handler.StateUiHandler
import com.example.structure.uibase.handler.StateUiOwner
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home), StateUiOwner {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val homeNavigator: HomeNavigator by inject()
    private val viewModel: HomeViewModel by viewModel()
    override val viewModels get() = StateUiHandler.ViewModels(viewModel)

    private val adapter = DifferentAdapter(
        viewHolderFactory = HomeViewHolderFactory(
            onItemHotMenuClick = { item, position ->
                android.util.Log.e("TEST_DATA", "Hot click $position - ${item.name}")
                homeNavigator.openMovieDetail(item.id.toString())
            },
            onViewMoreHotMenuClick = {
                android.util.Log.e("TEST_DATA", "ViewMore Hot Click")
                homeNavigator.openMovieList()
            },
            onItemNewMenuClick = { item, position ->
                android.util.Log.e("TEST_DATA", "New click $position - ${item.name}")
                homeNavigator.openMovieDetail(item.id.toString())
            },
            onViewMoreNewMenuClick = {
                android.util.Log.e("TEST_DATA", "ViewMore New Click")
                homeNavigator.openMovieList()
            },
            onItemPopularMenuClick = { item, position ->
                android.util.Log.e("TEST_DATA", "Popular click $position - ${item.name}")
                homeNavigator.openMovieDetail(item.id.toString())
            },
            onViewMorePopularMenuClick = {
                android.util.Log.e("TEST_DATA", "ViewMore Popular Click")
                homeNavigator.openMovieList()
            }
        )::create
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        android.util.Log.e("TEST_DATA", "HomeFragment onViewCreated")
        bindView()
        observer()
        viewModel.getHomeHotMenu(BuildConfig.API_KEY)
        viewModel.getHomeNewMenu(BuildConfig.API_KEY)
        viewModel.getHomeMenu(BuildConfig.API_KEY)
    }

    private fun bindView() = with(binding) {
        rvHome.adapter = adapter
        rvHome.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun observer() = with(viewModel) {
        onShowHomeDetail.observe(viewLifecycleOwner) {
            android.util.Log.e("TEST_DATA", "onShowHomeDetail= $it ")
            adapter.submitList(it)
        }
        onHomeMenuError.observe(viewLifecycleOwner) {
            adapter.notifyDataSetChanged()
        }
    }

}
