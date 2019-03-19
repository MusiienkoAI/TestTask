package auto.testtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import auto.testtask.R
import auto.testtask.bindings.callbacks.IBuildDatesCallback
import auto.testtask.databinding.FragmentBuildDateBinding
import auto.testtask.di.Injectable
import auto.testtask.ui.adapters.BuildDatesAdapter
import auto.testtask.utils.extensions.withViewModel
import auto.testtask.viewmodel.fragments.BuildDatesViewModel

class BuildDatesFragment : BaseFragment(), Injectable, IBuildDatesCallback{


    private lateinit var binding: FragmentBuildDateBinding
    private lateinit var viewModel: BuildDatesViewModel
    private lateinit var adapter: BuildDatesAdapter


    val args: BuildDatesFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_build_date, container, false)
        binding.apply {
            listener = this@BuildDatesFragment
            lifecycleOwner = this@BuildDatesFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        viewModel.requestBuildDates(args.manufactureId,args.mainTypeId)
        viewModel.getInfo(args.manufactureId,args.mainTypeId)
    }

    override fun initViewModel() {
        viewModel = withViewModel(viewModelFactory) {
            subscribeToViewModelEvents(this)
            binding.viewModel = this
        }
    }


    override fun subscribeToModel() {
        viewModel.buildDates.observe(this, Observer {
            adapter.update(it)
        })
    }

    private fun initRecyclerView() {
        adapter = BuildDatesAdapter(this)
        binding.rvBuildYears.adapter = adapter
    }


    override fun onBuildDatePick(buildDateId: String) {
        navController().navigate(BuildDatesFragmentDirections.showSummaryFragment(args.manufactureId,args.mainTypeId,buildDateId))
    }

    override fun onBack() {
        popBackStack()
    }


}