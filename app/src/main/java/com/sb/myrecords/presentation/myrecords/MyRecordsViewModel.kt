package com.sb.myrecords.presentation.myrecords

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sb.myrecords.data.datasource.Result
import com.sb.myrecords.data.entities.Record
import com.sb.myrecords.domain.repository.KantoRepository

class MyRecordsViewModel : ViewModel() {
    val records: LiveData<Result<List<Record>>> = KantoRepository().records
}
