package com.oladiniabayomi.digitalnews.articles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "instantiate")
class Instantiate(
    @ColumnInfo(name = "time")
    var time: Int
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var instantiateID: Int = 1


    @ColumnInfo(name = "instantiated")
    var instantiate: Boolean = false


}