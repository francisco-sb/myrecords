package com.sb.myrecords.presentation.myrecords

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sb.myrecords.data.datasource.Result
import com.sb.myrecords.data.entities.Record
import com.sb.myrecords.domain.repository.KantoRepository
import javax.inject.Inject

/**
 * Created by Sb on 14/07/2020
 * com.sb.myrecords.presentation.myrecords
 * My Records
 *
 * The ViewModel for [MyRecordsFragment].
 */
class MyRecordsViewModel @Inject constructor(repository: KantoRepository) : ViewModel() {

    val records: LiveData<Result<List<Record>>> = repository.records

}
