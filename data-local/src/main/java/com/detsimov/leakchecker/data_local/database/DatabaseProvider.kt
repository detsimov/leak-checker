package com.detsimov.leakchecker.data_local.database

import android.content.Context
import com.detsimov.core_data.Provider
import com.detsimov.leakchecker.data_local.Database
import com.detsimov.leakchecker.datalocal.sqldelight.data.Leak
import com.detsimov.leakchecker.datalocal.sqldelight.data.TrackData
import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.android.AndroidSqliteDriver

class DatabaseProvider(private val context: Context) : Provider<Database> {

    private fun createDriver() = AndroidSqliteDriver(Database.Schema, context, "application.db")

    private fun createLeakAdapter() = Leak.Adapter(from_line_typeAdapter = EnumColumnAdapter())

    private fun createTrackDataAdapter() = TrackData.Adapter(
        periodAdapter = EnumColumnAdapter(),
        typeAdapter = EnumColumnAdapter(),
    )

    override fun get(): Database {
        return Database(createDriver(), createLeakAdapter(), createTrackDataAdapter())
    }
}