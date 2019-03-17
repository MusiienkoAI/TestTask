package auto.testtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import auto.testtask.R
import auto.testtask.databinding.FragmentManufacturesBinding
import auto.testtask.di.Injectable
import auto.testtask.utils.extensions.withViewModel
import auto.testtask.viewmodel.fragments.ManufacturesViewModel

class ManufacturesFragment : BaseFragment(), Injectable {


    private lateinit var binding: FragmentManufacturesBinding
    private lateinit var viewModel: ManufacturesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_manufactures,container,false)
        return binding.root
    }

    override fun initViewModel() {
        viewModel = withViewModel(viewModelFactory){
            subscribeToViewModelEvents(this)
        }
    }

    override fun subscribeToModel() {
        viewModel.requestManufactures()
    }


}