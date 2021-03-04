package com.detsimov.leakchecker.data_local.security

import com.detsimov.leakchecker.data_local.network.dto.SecureDTO
import com.detsimov.leakchecker.data_local.network.dto.SecureDTO.Response.Scan.Companion.transform
import com.detsimov.leakchecker.data_local.network.services.SecureService
import com.detsimov.leakchecker.domain.models.LeakModel
import com.detsimov.leakchecker.domain.models.TrackDataModel
import com.detsimov.leakchecker.domain.service.ISecure

internal class Secure(private val secureService: SecureService) : ISecure {



    override suspend fun scanLeaks(trackDataWithLeaks: Pair<List<TrackDataModel>, List<LeakModel>>): List<LeakModel.Scanned> =
        secureService.scan(SecureDTO.Request.Scan.from(trackDataWithLeaks)).transform()



}