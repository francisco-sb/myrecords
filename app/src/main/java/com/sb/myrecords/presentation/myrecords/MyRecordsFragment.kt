package com.sb.myrecords.presentation.myrecords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.sb.myrecords.MainActivity

import com.sb.myrecords.R

class MyRecordsFragment : Fragment() {

    companion object {
        fun newInstance() =
            MyRecordsFragment()
    }

    private lateinit var viewModel: MyRecordsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.my_records_fragment, container, false)

        (activity as MainActivity?)!!.showHideToolbarTitle(getString(R.string.default_username))
        (activity as MainActivity?)!!.expandToolbar(true)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyRecordsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
