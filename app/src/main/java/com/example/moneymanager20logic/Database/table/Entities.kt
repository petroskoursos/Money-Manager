package com.example.moneymanager20logic.Database.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saveEntity")
data class Entities(

    val title:String,
    val amount:Double,
    val dateAdded: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,

    )

