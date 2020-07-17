package com.sb.myrecords.presentation.myrecords

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sb.myrecords.data.datasource.Result
import com.sb.myrecords.data.entities.Record
import com.sb.myrecords.domain.repository.KantoRepository
import com.sb.myrecords.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Created by Sb on 14/07/2020
 * com.sb.myrecords.presentation.myrecords
 * My Records
 *
 * The ViewModel for [MyRecordsFragment].
 */
class MyRecordsViewModel @Inject constructor(kantoRepository: KantoRepository, userRepository: UserRepository) : ViewModel() {

    private var userId: Int = 1

    val records: LiveData<Result<List<Record>>> = kantoRepository.records

    val user by lazy { userRepository.getUser(userId) }

}
