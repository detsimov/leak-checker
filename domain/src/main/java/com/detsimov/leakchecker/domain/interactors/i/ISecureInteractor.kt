package com.detsimov.leakchecker.domain.interactors.i

import com.detsimov.leakchecker.domain.models.ScanDataModel

interface ISecureInteractor {

    val scanCount: Int

    suspend fun fullScan(force: Boolean = false) : ScanDataModel
}