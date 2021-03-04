package com.detsimov.leakchecker.data_local.database

import android.content.Context
import com.detsimov.core_data.Provider
import com.detsimov.leakchecker.data_local.Database
import com.detsimov.leakchecker.datalocal.sqldelight.data.Leak
import com.detsimov.leakchecker.datalocal.sqldelight.data.TrackData
import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.android.AndroidSqliteDriver

class DatabaseProvider(private val context: Context) : Provider<Database> {
    override fun get(): Database =
        Database(
            AndroidSqliteDriver(Database.Schema, context, "application.db"),
            Leak.Adapter(from_line_typeAdapter = EnumColumnAdapter()),
            TrackData.Adapter(
                periodAdapter = EnumColumnAdapter(),
                typeAdapter = EnumColumnAdapter(),
            )
        )
}