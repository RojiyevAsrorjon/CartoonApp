package uz.gita.cartoonapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.cartoonapp.R
import uz.gita.cartoonapp.data.remote.responses.ResultsItem

class AllDataAdapter : PagingDataAdapter<ResultsItem, AllDataAdapter.ViewHolder>(DiffItem) {
    private var listener: (() -> Unit)? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = itemView.findViewById<ImageView>(R.id.imageUrl)
        val name = itemView.findViewById<TextView>(R.id.name)
        val status = itemView.findViewById<TextView>(R.id.status)
        val imageStatus = itemView.findViewById<ImageView>(R.id.statusIcon)
        val species = itemView.findViewById<TextView>(R.id.species)
        val lastSeen = itemView.findViewById<TextView>(R.id.lastPlace)
        val firstSeen = itemView.findViewById<TextView>(R.id.firstPlace)

        init {
            itemView.setOnClickListener {
                listener?.invoke()
            }
        }

        fun bind() {
            val data = getItem(absoluteAdapterPosition)
            if (data != null) {
                name.text = data.name
                status.text = data.status
                species.text = data.species
                firstSeen.text = data.origin.name
                lastSeen.text = data.location.name
                Glide.with(itemView).load(data.image).into(image)
                imageStatus.setBackgroundResource(
                    when (data.status) {
                        "Dead" -> R.drawable.ic_dead
                        "Alive" -> R.drawable.ic_alive
                        else -> R.drawable.ic_unknown
                    }
                )
            }
        }
    }

    object DiffItem : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem == newItem
        }

    }

    fun setItemClickListener(block: () -> Unit) {
        listener = block
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cartoon, parent, false))

}