package auto.testtask.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import auto.data.entities.common.MainType
import auto.testtask.bindings.callbacks.IMainTypesCallback
import auto.testtask.databinding.ItemMainTypeBinding

class MainTypesAdapter(private val iMainTypesCallback: IMainTypesCallback) :
        BaseRecyclerAdapter<RecyclerView.ViewHolder, List<MainType>>(listOf()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ManufactureViewHolder(ItemMainTypeBinding.inflate(inflater, parent, false).apply {
            listener = iMainTypesCallback
        })
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val binding = (viewHolder as ManufactureViewHolder).binding
        val mainType = items[position]
        binding.id = mainType.id
        binding.listener = iMainTypesCallback
        binding.tvName.text = mainType.name
    }


    inner class ManufactureViewHolder(val binding: ItemMainTypeBinding) : RecyclerView.ViewHolder(binding.root)
}