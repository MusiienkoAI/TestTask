package auto.testtask.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import auto.data.entities.common.Manufacturer
import auto.testtask.bindings.callbacks.IManufacturerCallback
import auto.testtask.databinding.ItemManufactureBinding

class ManufacturesAdapter(private val iManufacturerCallback: IManufacturerCallback) :
    BaseRecyclerAdapter<RecyclerView.ViewHolder, List<Manufacturer>>(listOf()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ManufactureViewHolder(ItemManufactureBinding.inflate(inflater, parent, false).apply {
            listener = iManufacturerCallback
        })
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val binding = (viewHolder as ManufactureViewHolder).binding
        val manufacture = items[position]
        binding.id = manufacture.id
        binding.listener = iManufacturerCallback
        binding.tvName.text = manufacture.name
    }


    inner class ManufactureViewHolder(val binding: ItemManufactureBinding) : RecyclerView.ViewHolder(binding.root)
}