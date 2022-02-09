package uz.gita.cartoonapp.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import uz.gita.cartoonapp.R
import uz.gita.cartoonapp.databinding.ScreenMainBinding
import uz.gita.cartoonapp.presentation.adapter.AllDataAdapter
import uz.gita.cartoonapp.presentation.adapter.ContentStateLoadAdapter
import uz.gita.cartoonapp.presentation.viewModel.MainScreenViewModel
import uz.gita.cartoonapp.presentation.viewModel.impl.MainScreenViewModelImpl
import uz.gita.cartoonapp.utils.scope

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainScreenViewModel by viewModels<MainScreenViewModelImpl>()
    private val adapter = AllDataAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        loadViews()
        loadFlows()

        adapter.setItemClickListener {
            findNavController().navigate(R.id.webScreen)
        }

        swipeRefresh.setOnRefreshListener {
            viewModel.getContents()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun loadFlows() = binding.scope {
        lifecycleScope.launchWhenCreated {
            viewModel.contentPagerLiveData.onEach {
                adapter.submitData(it)
            }.collect()
        }

        lifecycleScope.launchWhenCreated {
            viewModel.pagingAAdapterFlow.collect {
                recycler.setHasFixedSize(true)
                recycler.layoutManager = LinearLayoutManager(requireContext())

                recycler.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = ContentStateLoadAdapter(adapter::retry),
                    footer = ContentStateLoadAdapter(adapter::retry)
                )
            }
        }
    }

    private fun loadViews() {
        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collect { loadState ->
                if (loadState.refresh is LoadState.Loading) {
                    binding.shimmer.visibility = View.VISIBLE
                    binding.shimmer.startShimmer()
                } else {
                    binding.shimmer.stopShimmer()
                    binding.shimmer.visibility = View.GONE
                    binding.recycler.visibility = View.VISIBLE
                }
            }
        }
    }
}