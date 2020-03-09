package com.interview.gasbuddytechnical.ui.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.interview.gasbuddytechnical.databinding.ItemImageBinding
import com.interview.gasbuddytechnical.ui.view.activities.DetailsActivity
import com.interview.technicalround.ui.model.ImageModel
import com.interview.technicalround.utils.CommonUtils
import java.util.*
import kotlin.collections.ArrayList


class ImageAdapter(var imageList: ArrayList<ImageModel>) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>(), Filterable {

    private var filterText: String? = ""
    private var showList = ArrayList<ImageModel>()

    init {
        showList.addAll(imageList)
    }

    private val filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: ArrayList<ImageModel> = ArrayList()
            if (constraint.isEmpty()) {
                filteredList.addAll(imageList)
            } else {
                val filterPattern =
                    constraint.toString().toLowerCase(Locale.getDefault()).trim { it <= ' ' }
                for (item in imageList) {
                    if (item.getUserName()?.toLowerCase(Locale.getDefault())?.contains(filterPattern)!!) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(
            constraint: CharSequence,
            results: FilterResults
        ) {
            showList.clear()
            showList.addAll(results.values as ArrayList<ImageModel>)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return showList.size
    }

    fun changeList() {
        showList.clear()
        showList.addAll(imageList)
        notifyDataSetChanged()
        if (!(filterText?.isEmpty()!!)) {
            getFilter().filter(filterText)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageBinding.imageModel = showList[position]
        holder.imageBinding.executePendingBindings()

        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent(holder.itemView.context, DetailsActivity::class.java)

                intent.putExtra(CommonUtils.IMAGE_ID, showList[position].getId())
                holder.itemView.context.startActivity(intent)
            }

        })
    }

    class ViewHolder(var imageBinding: ItemImageBinding) :
        RecyclerView.ViewHolder(imageBinding.root) {

    }

    override fun getFilter(): Filter {
        return filter
    }

    fun setFilterText(filterText: String?) {
        this.filterText = filterText
    }

}