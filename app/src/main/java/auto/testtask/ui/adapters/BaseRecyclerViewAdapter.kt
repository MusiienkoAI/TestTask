package auto.testtask.ui.adapters

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<ViewHolder : RecyclerView.ViewHolder, DataType : Any>(
    protected var items: DataType
) : RecyclerView.Adapter<ViewHolder>() {

    open fun update(data: DataType) {
        this@BaseRecyclerAdapter.items = data
        notifyDataSetChanged()
    }
}