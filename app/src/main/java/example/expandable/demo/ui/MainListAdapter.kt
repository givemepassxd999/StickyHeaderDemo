package example.expandable.demo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import example.expandable.demo.R
import example.expandable.demo.databinding.EmptyHolderBinding
import example.expandable.demo.databinding.HeaderHolderBinding
import example.expandable.demo.databinding.ItemHolderBinding
import example.expandable.demo.databinding.TopHolderBinding
import example.expandable.demo.ui.HolderObject.Companion.TYPE_HEADER
import example.expandable.demo.ui.HolderObject.Companion.TYPE_ITEM
import example.expandable.demo.ui.HolderObject.Companion.TYPE_TOP

class MainListAdapter : ListAdapter<HolderObject, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = HeaderHolderBinding.inflate(layoutInflater, parent, false)
                HeaderViewHolder(binding.root)
            }

            TYPE_TOP -> {
                val binding = TopHolderBinding.inflate(layoutInflater, parent, false)
                TopHolder(binding.root)
            }

            TYPE_ITEM -> {
                val binding = ItemHolderBinding.inflate(layoutInflater, parent, false)
                SampleViewHolder(binding.root)
            }

            else -> {
                val binding = EmptyHolderBinding.inflate(layoutInflater, parent, false)
                EmptyViewHolder(binding.root)
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                holder.run { bindView() }
            }

            is TopHolder -> {
                holder.run { bindView() }
            }

            is SampleViewHolder -> {
                holder.run {
                    (getItem(position) as? HolderObject.Item)?.let {
                        bindView(it)
                    }

                }
            }
        }
    }

    override fun getItemCount(): Int = currentList.size

    override fun getItemViewType(position: Int) = currentList[position].type

    fun isHeader(position: Int) = getItem(position).type == TYPE_TOP

    fun getHeaderView(list: RecyclerView, position: Int): View? {
        val layoutInflater = LayoutInflater.from(list.context)
        for (index in 0..position) {
            if (currentList[index].type == TYPE_TOP) {
                return TopHolderBinding.inflate(layoutInflater, list, false).root
            }
        }
        return null
    }

    inner class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView() {}
    }

    inner class TopHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView() {}
    }

    inner class SampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvContents = itemView.findViewById<TextView>(R.id.tvContents)

        fun bindView(data: HolderObject.Item) {
            tvContents.text = data.content
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<HolderObject>() {
        override fun areItemsTheSame(
            oldItem: HolderObject,
            newItem: HolderObject
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: HolderObject,
            newItem: HolderObject
        ): Boolean {
            return oldItem.type == newItem.type
        }
    }
}

