package com.detsimov.leakchecker.data.repositories

import com.detsimov.core_domain.models.DataStatus
import com.detsimov.leakchecker.data.network.dto.LeakDTO
import com.detsimov.leakchecker.data.network.dto.LeakDTO.Response.Read.Companion.transform
import com.detsimov.leakchecker.data.network.services.LeakService
import com.detsimov.leakchecker.domain.models.LeakModel
import com.detsimov.leakchecker.domain.repositories.DeleteLeakException
import com.detsimov.leakchecker.domain.repositories.FetchParams
import com.detsimov.leakchecker.domain.repositories.ILeakRepository
import kotlinx.coroutines.flow.MutableStateFlow

internal class LeakRepository(private val leakService: LeakService) : ILeakRepository {

    override val ownDataFlow =
        MutableStateFlow<DataStatus<List<LeakModel>, Throwable>>(DataStatus.Empty)


    private fun updateOwnData(updateBlock: MutableList<LeakModel>.() -> Unit) {
        ownDataFlow.value.successData {
            // TODO: Произойдёт хуйня если на этом этапе что-то в ownDataFlow добавится
            ownDataFlow.value = DataStatus.Success(it.toMutableList().apply(updateBlock))
        }
    }

    override suspend fun refreshOwn() {
        ownDataFlow.value = DataStatus.Loading
        try {
            ownDataFlow.value = DataStatus.Success(leakService.get().map { it.transform() })
        } catch (any: Throwable) {
            ownDataFlow.value = DataStatus.Error(any)
        }
    }

    override suspend fun add(data: String, lastBreach: String?, source: String?, trackDataId: Long) {
        TODO()
    }


    override suspend fun delete(leakModel: LeakModel) {
        leakService.delete(LeakDTO.Request.Delete.from(leakModel)).apply {
            if (code() == 400) throw DeleteLeakException()
            else updateOwnData { remove(leakModel) }

        }
    }


    override suspend fun clear() {
        leakService.deleteAll(LeakDTO.Request.DeleteAll.create()).apply {
            if (isSuccessful) updateOwnData { clear() }
        }
    }

    override suspend fun fetch(fetchParams: FetchParams): List<LeakModel> {
        TODO("Not yet implemented")
    }



}


