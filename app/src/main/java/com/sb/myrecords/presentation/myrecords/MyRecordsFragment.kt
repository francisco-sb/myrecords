package com.sb.myrecords.presentation.myrecords

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        return inflater.inflate(R.layout.my_records_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyRecordsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
