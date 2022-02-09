package uz.gita.cartoonapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.cartoonapp.databinding.ItemPagingLoadingBinding

class ContentStateLoadAdapter(
    val retry: () -> Unit
) : LoadStateAdapter<ContentStateLoadAdapter.PassengerLoadStateAdapter>() {
    inner class PassengerLoadStateAdapter(
        private val binding : ItemPagingLoadingBinding,
        private val retry : () -> Unit
    ) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.buttonRetry.setOnClickListener { retry.invoke() }
        }
        fun bind(loadState: LoadState) {
            with(binding) {
                progressbar.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState is LoadState.Error
                textError.isVisible = loadState is LoadState.Error
            }
        }
    }
    override fun onBindViewHolder(holder: PassengerLoadStateAdapter, loadState: LoadState) = holder.bind(loadState)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState)= PassengerLoadStateAdapter(ItemPagingLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false), retry)

}