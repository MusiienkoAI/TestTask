package auto.testtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import auto.testtask.R
import auto.testtask.bindings.callbacks.IMainTypesCallback
import auto.testtask.databinding.FragmentMainTypeBinding
import auto.testtask.di.Injectable
import auto.testtask.ui.adapters.MainTypesAdapter
import auto.testtask.utils.extensions.pagintantionListener
import auto.testtask.utils.extensions.withViewModel
import auto.testtask.viewmodel.fragments.MainTypesViewModel
import timber.log.Timber

class MainTypesFragment : BaseFragment(), Injectable, IMainTypesCallback{

    private lateinit var binding: FragmentMainTypeBinding
    private lateinit var viewModel: MainTypesViewModel
    private lateinit var adapter: MainTypesAdapter


    val args: MainTypesFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_type, container, false)
        binding.apply {
            listener = this@MainTypesFragment
            lifecycleOwner = this@MainTypesFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        viewModel.requestMainTypes(args.manufactureId)
        viewModel.getManufacturer(args.manufactureId)
    }

    override fun initViewModel() {
        viewModel = withViewModel(viewModelFactory) {
            subscribeToViewModelEvents(this)
            binding.viewModel = this
        }
    }


    override fun subscribeToModel() {
        viewModel.mainTypes.observe(this, Observer {
            adapter.update(it)
        })
    }

    private fun initRecyclerView() {
        adapter = MainTypesAdapter(this)
        binding.rvMainTypes.addOnScrollListener(
                pagintantionListener(
                        onScrolledToBottom = {
                            viewModel.requestMainTypes(args.manufactureId)
                        }))
        binding.rvMainTypes.adapter = adapter
    }



    override fun onBack() {
        popBackStack()
    }

    override fun onMainTypePick(mainTypeId: String) {
        navController().navigate(MainTypesFragmentDirections.showBuildDatesFragment(args.manufactureId,mainTypeId))
    }

}