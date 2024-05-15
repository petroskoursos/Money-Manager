package com.example.moneymanager20logic.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moneymanager20logic.Database.Daos.Saving
import com.example.moneymanager20logic.Database.table.Entities


@Database(entities = [Entities::class], version = 2)

abstract class Database:RoomDatabase(){
    abstract val savingDao: Saving
}