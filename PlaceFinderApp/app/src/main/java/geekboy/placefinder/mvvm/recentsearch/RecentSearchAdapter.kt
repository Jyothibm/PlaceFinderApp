package geekboy.placefinder.mvvm.recentsearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import geekboy.placefinder.R
import geekboy.placefinder.repository.local.db.recentsearch.RecentSearch

class RecentSearchAdapter(var listSearch: ArrayList<RecentSearch>, val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecentSearchAdapter.RecentSearchVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchVH {
        return RecentSearchVH(parent)
    }

    override fun getItemCount(): Int = listSearch.size


    override fun onBindViewHolder(holder: RecentSearchVH, position: Int) {

        holder.bind(listSearch[position].name)
        holder.getBindingObject().root.setOnClickListener {
            listener.onClick(listSearch[position].name)
        }

    }


    fun update(items: List<RecentSearch>) {
        this.listSearch = items as ArrayList<RecentSearch>
        notifyDataSetChanged()
    }


    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(items: List<RecentSearch>) {
            val adapter = adapter as RecentSearchAdapter
            adapter.update(items)
        }
    }

    class RecentSearchVH(
        parent: ViewGroup,
        private val binding: geekboy.placefinder.databinding.RowSearchBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_search,
            parent,
            false
        )
    ) : RecyclerView.ViewHolder(binding.root) {

        fun getBindingObject() = binding

        fun bind(item: String) {
            binding.searchString = item
        }

    }
}