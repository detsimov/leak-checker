package com.detsimov.leakchecker.domain.interactors.i

import com.detsimov.leakchecker.domain.models.ScanDataModel

interface ISecureInteractor {

    suspend fun fullScan() : ScanDataModel

}