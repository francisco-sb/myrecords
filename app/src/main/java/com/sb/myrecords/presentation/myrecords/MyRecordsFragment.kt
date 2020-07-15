package com.sb.myrecords.presentation.myrecords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.sb.myrecords.MainActivity
import com.sb.myrecords.R
import com.sb.myrecords.data.datasource.Result
import com.sb.myrecords.databinding.MyRecordsFragmentBinding
import com.sb.myrecords.di.Injectable
import com.sb.myrecords.di.injectViewModel
import com.sb.myrecords.ui.VerticalItemDecoration
import com.sb.myrecords.ui.hide
import com.sb.myrecords.ui.show
import javax.inject.Inject

class MyRecordsFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MyRecordsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)

        val binding = MyRecordsFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = RecordAdapter()
        binding.recyclerView.addItemDecoration(
            VerticalItemDecoration(resources.getDimension(R.dimen.default_margin).toInt(),true)
        )
        binding.recyclerView.adapter = adapter

        subscribeUI(binding, adapter)

        (activity as MainActivity?)!!.showHideToolbarTitle(getString(R.string.default_username))
        (activity as MainActivity?)!!.expandToolbar(true)

        return binding.root
    }

    //region:: PRIVATE METHODS
    private fun subscribeUI(binding: MyRecordsFragmentBinding, adapter: RecordAdapter) {
        viewModel.records.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let { adapter.submitList(it) }
                }
                Result.Status.LOADING -> binding.progressBar.show()
                Result.Status.ERROR -> {
                    binding.progressBar.hide()
                    Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
    //endregion

}
