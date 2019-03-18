package auto.testtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import auto.testtask.R
import auto.testtask.bindings.callbacks.IManufacturerCallback
import auto.testtask.databinding.FragmentManufacturesBinding
import auto.testtask.di.Injectable
import auto.testtask.ui.adapters.ManufacturesAdapter
import auto.testtask.utils.extensions.pagintantionListener
import auto.testtask.utils.extensions.withViewModel
import auto.testtask.viewmodel.fragments.ManufacturesViewModel

class ManufacturesFragment : BaseFragment(), Injectable, IManufacturerCallback {


    private lateinit var binding: FragmentManufacturesBinding
    private lateinit var viewModel: ManufacturesViewModel
    private lateinit var adapter: ManufacturesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_manufactures, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    override fun initViewModel() {
        viewModel = withViewModel(viewModelFactory) {
            subscribeToViewModelEvents(this)
        }
    }

    override fun onManufacturerPick(manufactureId : String) {
        navController().navigate(ManufacturesFragmentDirections.showMainTypesFragment(manufactureId))
    }

    override fun subscribeToModel() {
        viewModel.requestManufactures()
        viewModel.manufactures.observe(this, Observer {
            adapter.update(it)
        })
    }

    private fun initRecyclerView() {
        adapter = ManufacturesAdapter(this)
        binding.rvManufactures.addOnScrollListener(
                pagintantionListener(
                        onScrolledToBottom = {
                            viewModel.requestMoreManufactures()
                        }))
        binding.rvManufactures.adapter = adapter
    }


}