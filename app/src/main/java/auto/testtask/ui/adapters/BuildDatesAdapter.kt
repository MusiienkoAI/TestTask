package auto.testtask.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import auto.data.entities.common.BuildDate
import auto.testtask.bindings.callbacks.IBuildDatesCallback
import auto.testtask.databinding.ItemBuildDateBinding

class BuildDatesAdapter(private val iBuildDateCallback: IBuildDatesCallback) :
        BaseRecyclerAdapter<RecyclerView.ViewHolder, List<BuildDate>>(listOf()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ManufactureViewHolder(ItemBuildDateBinding.inflate(inflater, parent, false).apply {
            listener = iBuildDateCallback
        })
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val binding = (viewHolder as ManufactureViewHolder).binding
        val buildDate = items[position]
        binding.id = buildDate.date
        binding.listener = iBuildDateCallback
        binding.tvName.text = buildDate.date
    }


    inner class ManufactureViewHolder(val binding: ItemBuildDateBinding) : RecyclerView.ViewHolder(binding.root)
}