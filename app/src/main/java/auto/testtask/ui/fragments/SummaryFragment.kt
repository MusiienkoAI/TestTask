package auto.testtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import auto.testtask.R
import auto.testtask.bindings.callbacks.ISummaryCallback
import auto.testtask.databinding.FragmentSummaryBinding
import auto.testtask.di.Injectable
import auto.testtask.utils.extensions.withViewModel
import auto.testtask.viewmodel.fragments.SummaryViewModel

class SummaryFragment : BaseFragment(), Injectable, ISummaryCallback{


    private lateinit var binding: FragmentSummaryBinding
    private lateinit var viewModel: SummaryViewModel

    val args: SummaryFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_summary, container, false)
        binding.apply {
            listener = this@SummaryFragment
            lifecycleOwner = this@SummaryFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getSummary(args.manufactureId,args.mainTypeId,args.buildDate)
    }

    override fun initViewModel() {
        viewModel = withViewModel(viewModelFactory){
            subscribeToViewModelEvents(this)
            binding.viewModel = this
        }
    }

    override fun subscribeToModel() {

    }

    override fun onBack() {
        popBackStack()
    }


}