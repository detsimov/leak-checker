package com.detsimov.leakchecker.domain.service

import com.detsimov.leakchecker.domain.models.LeakModel
import com.detsimov.leakchecker.domain.models.TrackDataModel

interface ISecure {


    suspend fun scanLeaks(trackDataWithLeaks: Pair<List<TrackDataModel>, List<LeakModel>>) : List<LeakModel.Scanned>

}